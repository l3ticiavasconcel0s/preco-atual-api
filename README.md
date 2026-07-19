# API de preço atual

Microsserviço para consultar a precificação ativa de um ativo.

## Endpoint

```text
GET /preco-atual/v1/precos/ITAU3
```

Base URL pública:

```text
http://34.204.232.188/preco-atual/v1/precos
```

Exemplo de resposta:

```json
{
  "id": 1,
  "codigo_ativo": "PETR4",
  "preco": 35.50,
  "data_hora_atualizacao": "2026-07-17T10:30:00",
  "atualizado": true
}
```

## Configuração

Crie um arquivo `.env` na raiz:

```properties
APP_PORT=8080
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
INSERT INTO precos.precificacao (codigo_ativo, preco, atualizado)
VALUES ('ITAU3', 100.01, TRUE);
```

A consulta considera somente o registro com `atualizado = TRUE`. Quando não
existe um registro ativo, a API retorna `404`.

```powershell
curl.exe "http://localhost:8080/preco-atual/v1/precos/ITAU3"
```

## Nginx

```nginx
location /preco-atual/ {
    proxy_pass http://preco-atual-api:8080;
    proxy_set_header Host $host;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_set_header X-Forwarded-Proto $scheme;
}
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

O serviço será publicado na porta interna `8080` e conectado à rede Docker externa `app-network`.
