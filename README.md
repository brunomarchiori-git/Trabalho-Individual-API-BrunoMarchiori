# API de Músicas – Bruno Marchiori

API REST construída em Java com Spring Boot para gerenciar músicas, artistas, usuários, perfis e playlists.

## Tecnologias
- Java 17
- Spring Boot 3 (Web, Validation, Data JPA)
- PostgreSQL (H2 runtime opcional)
- Hibernate
- Maven
- Springdoc OpenAPI (Swagger UI)
- GitHub

## Como executar
1. Configure o banco no `src/main/resources/application.properties`:
   - `spring.datasource.url=jdbc:postgresql://localhost:5432/Teste1`
   - `spring.datasource.username=postgres`
   - `spring.datasource.password=123456`
   - `spring.jpa.hibernate.ddl-auto=update`
2. Rodar com Maven:
   - Windows: `mvnw.cmd spring-boot:run`
   - Linux/macOS: `./mvnw spring-boot:run`

Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## Principais recursos
- Artistas: CRUD completo
- Músicas: CRUD completo e relacionamento com artistas e playlists
- Usuários: CRUD, com `Perfil` (1:1) e `Playlists` (1:N)
- Playlists: CRUD e relacionamento N:N com músicas

## Endpoints (resumo)
Base: `http://localhost:8080`

Artistas (`/artistas`)
- GET `/artistas` – lista todos
- GET `/artistas/{id}` – busca por id
- POST `/artistas` – cria
- PUT `/artistas/{id}` – atualiza
- DELETE `/artistas/{id}` – remove

Músicas (`/musicas`)
- GET `/musicas`
- GET `/musicas/{id}`
- POST `/musicas`
- PUT `/musicas/{id}`
- DELETE `/musicas/{id}`

Usuários (`/usuarios`)
- GET `/usuarios`
- GET `/usuarios/{id}`
- POST `/usuarios`
- PUT `/usuarios/{id}`
- DELETE `/usuarios/{id}`

Obs.: Demais detalhes (modelos e exemplos) estão no Swagger UI.

## Validações e tratamento de erros
Validações com `jakarta.validation` (ex.: `@NotBlank`, `@Size`, `@NotNull`).

Handler global simples (`@ControllerAdvice`):
- 400 Bad Request: erros de validação (`@Valid`) e JSON malformado
- 404 Not Found: recurso não encontrado
- 500 Internal Server Error: erro não previsto

Formato de erro (`ErroResposta`):
```json
{
  "status": 404,
  "mensagem": "Artista com id 999 não encontrado",
  "dataHora": "2025-10-20T10:00:00",
  "erros": []
}
```

Exemplo 400 (validação):
```json
{
  "status": 400,
  "mensagem": "Existem Campos Inválidos, confira o preenchimento!",
  "dataHora": "2025-10-20T10:00:00",
  "erros": [
    "nome: O nome do artista não pode ser vazio"
  ]
}
```

## Sobre JSON e relacionamentos
Para evitar loops de serialização (recursão infinita), alguns lados dos relacionamentos usam `@JsonIgnore`. Assim, a API retorna os dados essenciais sem ciclos profundos.

## Desenvolvimento
- Build: `mvnw clean package`
- Testes: `mvnw test`

## Autor
Bruno Marchiori
