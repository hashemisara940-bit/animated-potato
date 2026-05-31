# Kairo

Kairo is a local-first AI Agent Operating System scaffold written in Java 21 and Spring Boot 3. It is designed as a provider-agnostic runtime for LLMs, tools, workflows, memory, task planning, workspaces, and future multi-agent/MCP integrations.

## Maven architecture

```text
kairo-domain          pure domain model, no Spring dependencies
        ↑
kairo-application     use cases, ports, planning, runtime orchestration
        ↑
kairo-infrastructure  provider adapters, tools, repositories, schema, observability adapters
        ↑
kairo-api             Spring Boot WebFlux REST API
```

## Package structure

```text
dev.kairo.domain
├── agent
├── conversation
├── provider
├── task
├── workflow
├── memory
├── runtime
├── tool
├── workspace
├── knowledge
├── multiagent
├── mcp
└── exception

dev.kairo.application
├── usecase
├── service
├── port.in
├── port.out
├── mapper
└── planning

dev.kairo.infrastructure
├── config
├── provider
├── repository
├── tool
└── observability

dev.kairo.api
├── controller
└── dto
```

## Runtime architecture

```text
User Request
  ↓
REST API / Reactive input port
  ↓
Application Use Case
  ↓
Agent Runtime
  ↓
Planner / Execution Plan
  ↓
Tool Registry + Memory Retriever
  ↓
Provider Registry
  ↓
LLM Provider (Ollama local/remote, OpenAI, Anthropic, Gemini, OpenRouter)
  ↓
Response + Runtime Events + Metrics
```

## Provider framework

`LlmProvider`, `ChatProvider`, `EmbeddingProvider`, `StreamingProvider`, and `ProviderRegistry` keep Kairo independent from any vendor. Providers are selected by configuration under `kairo.providers.configured`; the default local provider is Ollama at `http://localhost:11434`, and a remote Ollama profile is available at `http://192.168.1.100:11434`.

## Tool framework

Tools implement the domain `Tool` interface and self-register through `ToolDiscoveryService` into `ToolRegistry`. Initial tools include:

- `file.read`
- `file.write`
- `directory.search`
- `http.request`
- `terminal.exec`
- `git.exec`
- `database.query` (disabled until safe read-only policy is configured)

## Database schema

The first migration creates tables for workspaces, agents, conversations, messages, tasks, workflows, memories, runtime events, tool-call logs, knowledge bases, documents, and chunks. pgvector is intentionally deferred but the `chunks` table is ready to evolve with embedding columns and vector indexes.

## REST API contracts

| Method | Path | Purpose |
| --- | --- | --- |
| `POST` | `/chat` | Non-streaming chat completion through configured provider |
| `POST` | `/chat/stream` | Server-sent streaming chat |
| `POST` | `/tasks` | Create and plan an autonomous task |
| `GET` | `/tasks/{id}` | Fetch task history/state |
| `POST` | `/workflows` | Store a workflow draft contract |
| `GET` | `/providers` | List configured providers |
| `GET` | `/tools` | List discovered tools |
| `POST` | `/tools/execute` | Execute a tool call |
| `GET` | `/workspaces` | List workspaces |
| `POST` | `/workspaces` | Create a workspace |

## Spring Boot implementation plan

1. Boot the API module and expose health, metrics, and Prometheus actuator endpoints.
2. Use in-memory adapters for phase-one local development while maintaining repository ports.
3. Enable Flyway and replace in-memory repositories with R2DBC PostgreSQL adapters.
4. Add pgvector embeddings, semantic retrieval, and knowledge ingestion adapters.
5. Replace heuristic planning with provider-assisted planning and iterative tool execution.
6. Add OpenTelemetry tracing, Kafka event streams, and MCP client/server adapters.

## Incremental roadmap

### Phase 1: Chat path

- Domain and application ports
- Ollama provider
- Chat and streaming API
- Conversation aggregate

Goal: `User -> Ollama -> Response`.

### Phase 2: Tools

- Tool registry and discovery
- File tools
- Terminal, Git, HTTP, and database extension point

Goal: agent can discover and invoke tools.

### Phase 3: Runtime and planning

- Agent runtime
- Task execution
- Planning loop with evaluation

Goal: agent can complete task objectives.

### Phase 4: Memory

- PostgreSQL persistence
- Short-term and long-term memory
- Retrieval and future pgvector semantic search

Goal: persistent knowledge.

### Phase 5: AI operating system foundation

- Workspaces
- Workflows
- Multi-agent coordination interfaces
- MCP extension points

Goal: evolve into a local-first AI operating system.

## Local development

```bash
docker compose up -d postgres redis ollama
mvn clean verify
mvn -pl kairo-api spring-boot:run
```
