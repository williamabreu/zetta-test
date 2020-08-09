# Desafio Prático Agência Zetta (Java Jr.)

Sistema básico de Gerenciamento de Usuário

### Requisitos de ambiente

- Java 11
- MariaDB 10.1

### Executar o programa

#### 1. Criar mysql database

Criar o banco e o usuário usado pelo sistema:

```sql
CREATE DATABASE zetta_db;
CREATE USER 'zetta'@'%' IDENTIFIED BY 'attez';
GRANT ALL PRIVILEGES ON zetta_db.* TO 'zetta'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
```

#### 2. Configurar variáveis do mysql

Modificar nos seguintes arquivos:

```bash
src/main/resources/application.properties
src/main/java/net/williamabreu/zetta/DataConfiguration.java
```

#### 3. Executar a aplicação

Foi utilizado Itellij Idea para o desenvolvimento, porém o arquivo ```JAR``` está compilado para ser executado em 
servidor mysql localhost.

```bash
java -jar zetta-test-0.0.1-SNAPSHOT.jar
```

A aplicação roda no http://localhost:8080 .