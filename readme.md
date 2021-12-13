### Sobre o projeto
API REST para sistemas de gestão de contas, tem como objetivo gerenciar transações, saques e depósitos de contas.

### Build do projeto

#### Pré-requisitos

#### 1. Criar um banco de dados Mysql na máquina host

```
CREATE DATABASE sistema_bancario;
```

#### 2. Criar arquivo .env baseado no arquivo .env.example

```
Copiar e ajustar variáveis de usuário e senha conforme as utilizadas na máquina local
```
```
Para ambiente de desenvolvimento, manter o valor da chave SPRING_PROFILES_ACTIVE como vazio
```

#### 3. Subir docker-compose
Nessa etapa será realizado o build da imagem do container e a aplicação irá se comunicar com o banco de dados da m
```
docker-compose up --build
```

### Documentação da API

Quando o servidor estiver rodando, acessar a url para visualizar o swagger UI com a documentaç
```
http:localhost:8080/api/v1/swagger-ui.html
```
