package dev.kairo.infrastructure.config;

import dev.kairo.application.planning.HeuristicPlanner;
import dev.kairo.application.planning.Planner;
import dev.kairo.application.port.in.ChatUseCase;
import dev.kairo.application.port.in.ExecuteTaskUseCase;
import dev.kairo.application.port.in.InvokeToolUseCase;
import dev.kairo.application.port.in.ListProvidersUseCase;
import dev.kairo.application.port.in.StoreMemoryUseCase;
import dev.kairo.application.port.in.WorkspaceUseCase;
import dev.kairo.application.port.out.ConversationRepository;
import dev.kairo.application.port.out.LlmProvider;
import dev.kairo.application.port.out.MemoryRepository;
import dev.kairo.application.port.out.ProviderRegistry;
import dev.kairo.application.port.out.TaskRepository;
import dev.kairo.application.port.out.ToolDiscoveryService;
import dev.kairo.application.port.out.ToolRegistry;
import dev.kairo.application.port.out.WorkspaceRepository;
import dev.kairo.application.service.ChatService;
import dev.kairo.application.service.MemoryService;
import dev.kairo.application.service.ProviderService;
import dev.kairo.application.service.TaskService;
import dev.kairo.application.service.ToolService;
import dev.kairo.application.service.WorkspaceService;
import dev.kairo.domain.tool.Tool;
import dev.kairo.infrastructure.provider.ConfigurableProviderRegistry;
import dev.kairo.infrastructure.provider.KairoProviderProperties;
import dev.kairo.infrastructure.provider.OllamaChatProvider;
import dev.kairo.infrastructure.provider.RemoteHttpChatProvider;
import dev.kairo.infrastructure.repository.InMemoryConversationRepository;
import dev.kairo.infrastructure.repository.InMemoryMemoryRepository;
import dev.kairo.infrastructure.repository.InMemoryTaskRepository;
import dev.kairo.infrastructure.repository.InMemoryWorkspaceRepository;
import dev.kairo.infrastructure.tool.AutoToolDiscoveryService;
import dev.kairo.infrastructure.tool.DatabaseTool;
import dev.kairo.infrastructure.tool.DirectorySearchTool;
import dev.kairo.infrastructure.tool.FileReadTool;
import dev.kairo.infrastructure.tool.FileWriteTool;
import dev.kairo.infrastructure.tool.GitTool;
import dev.kairo.infrastructure.tool.HttpTool;
import dev.kairo.infrastructure.tool.InMemoryToolRegistry;
import dev.kairo.infrastructure.tool.TerminalTool;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(KairoProviderProperties.class)
public class KairoInfrastructureConfiguration {
    @Bean
    Planner planner() { return new HeuristicPlanner(); }

    @Bean
    ConversationRepository conversationRepository() { return new InMemoryConversationRepository(); }

    @Bean
    TaskRepository taskRepository() { return new InMemoryTaskRepository(); }

    @Bean
    MemoryRepository memoryRepository() { return new InMemoryMemoryRepository(); }

    @Bean
    WorkspaceRepository workspaceRepository() { return new InMemoryWorkspaceRepository(); }

    @Bean
    ToolRegistry toolRegistry() { return new InMemoryToolRegistry(); }

    @Bean
    List<Tool> kairoTools(WebClient.Builder webClientBuilder) {
        return List.of(new FileReadTool(), new FileWriteTool(), new DirectorySearchTool(), new HttpTool(webClientBuilder), new TerminalTool(), new GitTool(), new DatabaseTool());
    }

    @Bean
    ToolDiscoveryService toolDiscoveryService(ToolRegistry registry, List<Tool> tools) {
        return new AutoToolDiscoveryService(registry, tools);
    }

    @Bean
    ApplicationRunner discoverTools(ToolDiscoveryService discoveryService) {
        return args -> discoveryService.discover();
    }

    @Bean
    ProviderRegistry providerRegistry(KairoProviderProperties properties, WebClient.Builder webClientBuilder) {
        Map<String, LlmProvider> providers = new LinkedHashMap<>();
        properties.configured().forEach((id, config) -> {
            if ("ollama".equalsIgnoreCase(config.type())) {
                providers.put(id, new OllamaChatProvider(id, config.host(), webClientBuilder));
            } else {
                providers.put(id, new RemoteHttpChatProvider(id, displayName(config.type()), config.host()));
            }
        });
        return new ConfigurableProviderRegistry(providers, properties.defaultProvider());
    }

    @Bean
    ChatUseCase chatUseCase(ProviderRegistry providerRegistry, ConversationRepository conversationRepository) {
        return new ChatService(providerRegistry, conversationRepository);
    }

    @Bean
    ExecuteTaskUseCase executeTaskUseCase(TaskRepository taskRepository, Planner planner) {
        return new TaskService(taskRepository, planner);
    }

    @Bean
    InvokeToolUseCase invokeToolUseCase(ToolRegistry toolRegistry) { return new ToolService(toolRegistry); }

    @Bean
    StoreMemoryUseCase storeMemoryUseCase(MemoryRepository memoryRepository) { return new MemoryService(memoryRepository); }

    @Bean
    ListProvidersUseCase listProvidersUseCase(ProviderRegistry providerRegistry) { return new ProviderService(providerRegistry); }

    @Bean
    WorkspaceUseCase workspaceUseCase(WorkspaceRepository repository) { return new WorkspaceService(repository); }

    private String displayName(String type) {
        return switch (type.toLowerCase()) {
            case "openai" -> "OpenAI";
            case "anthropic" -> "Anthropic";
            case "gemini" -> "Gemini";
            case "openrouter" -> "OpenRouter";
            default -> type;
        };
    }
}
