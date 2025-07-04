# Barber Experience V2

Sistema de agendamento de barbearia multi-tenant, desenvolvido em Java com Spring Boot, Clean Architecture, MySQL e Docker.

## Primeiros Passos: Como rodar o projeto

### 1. Pré-requisitos
- Java 17 ou superior
- Maven 3.8+
- Docker e Docker Compose

### 2. Suba o ambiente de banco de dados e cache
No terminal, execute na raiz do projeto:
```sh
docker-compose up -d
```
Isso irá subir os containers do MySQL, Redis e Adminer.

### 3. (Opcional) Acesse o Adminer para visualizar o banco
Abra o navegador e acesse: [http://localhost:8081](http://localhost:8081)
- Servidor: mysql
- Usuário: barber_user
- Senha: barber_pass
- Banco: barber_experience

### 4. Rode a aplicação Spring Boot
No terminal, execute:
```sh
./mvnw spring-boot:run
```
Ou, se preferir usar o Maven instalado no sistema:
```sh
mvn spring-boot:run
```

A aplicação estará disponível em: [http://localhost:8080](http://localhost:8080)

### 5. Teste a aplicação
Você pode acessar os endpoints REST ou a documentação Swagger (se configurada) em:
- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Tecnologias principais
- Java 17+
- Spring Boot
- MySQL
- Redis
- Docker
- Clean Architecture
- Flyway

## Estrutura do projeto
Veja o código fonte em `src/main/java/com/barberexperience`.

---

> Projeto em desenvolvimento! 