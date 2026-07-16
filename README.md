# API de preço atual

Microsserviço para consultar o preço atualizado mais recente de um ativo.

## Endpoint

```text
GET /v1/precos/preco-atual?codigo_ativo=ITAU3
```

## Configuração

Crie um arquivo `.env` na raiz:

```properties
APP_PORT=8081
DB_URL=jdbc:postgresql://localhost:5432/precos
DB_USERNAME=postgres
DB_PASSWORD=change-me
```

## Execução

Na primeira execução, gere o Gradle Wrapper usando o wrapper do serviço de investidores:

```powershell
..\cadastro-investidor-api\gradlew.bat wrapper --gradle-version 9.5.1
```

```powershell
.\gradlew.bat clean bootRun
```

## Dado para teste

```sql
INSERT INTO precos.precificacao (codigo_ativo, preco)
VALUES ('ITAU3', 100.01);
```

```powershell
curl.exe "http://localhost:8081/v1/precos/preco-atual?codigo_ativo=ITAU3"
```

## GitHub Secrets

Configure no repositório:

- `EC2_HOST`
- `EC2_USER`
- `EC2_SSH_KEY`
- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

## Release e deploy

1. Execute `Create Release` em GitHub Actions.
2. Copie a versão criada.
3. Execute `Deploy API` informando essa versão.

O serviço será publicado na porta interna `8081` e conectado à rede Docker externa `app-network`.
