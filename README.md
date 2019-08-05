# Register API #
A Documentação da api se encontra no link: 

Intuito da aplicação é conceder cadastro, update, deleção e get de clientes, além de acontecer no cadastro um registro do ip do usuário solicitador da request, registra também sua geolocalidade e temperatura min e max do dia do acontecimento.


## Ambiente de Desenvolvimento ##

### Pré-Requisitos ###

- S.O (Preferencialmente Linux) 
- JDK8
- Gradle 5.4.1
- Docker 18.09.07

### Pré-Requisitos para Testes ###

- Preferencialmente (SpringToolSuite4 - Eclipse)
- Postman ou Chrome

## Subir a aplicação em modo Debug ##

Temos a possibilidade de executar um comando gradlew para subir as dependencias da aplicação localmente em docker para fazer todos os testes locais.
- Acesse a pasta raiz da aplicação e execute o comando abaixo...
```
$ sudo ./gradlew runDockerComposeDebug
```

Esse comando executará os dockers para que você tenha acesso a uma base postgresql local e um pgAdmin para acompanhar suas alterações...

- PostgreSql subirá na porta, localhost:5432/register
    user: root
    senha: 123465
- PgAdmin subirá na porta localhost:5050
    user: admin
    senha: admin


## Subir toda a aplicação em Containers ##

Temos a possibilidade de executar um comando gradlew para subir toda a aplicação localmente em docker para fazer todos os testes locais.
- Acesse a pasta raiz da aplicação e execute o comando abaixo...
```
$ sudo ./gradlew runDockerCompose
```

Esse comando executará os dockers para que você tenha acesso a uma base postgresql local e um pgAdmin para acompanhar suas alterações, além de executar o jar de sua aplicação expondo na porta 8080.

- PostgreSql subirá na porta, localhost:5432/register
    user: root
    senha: 123465
- PgAdmin subirá na porta localhost:5050
    user: admin
    senha: admin
- Aplicação subirá na porta: localhost:8080

- Acesso ao Swagger - localhost:8080/swagger-ui.html

# Testes Unitários #

Projeto acompanha os testes unitários garantindo sempre a consistencia da informação.

Temos tbm teste de requisição na pasta  `/postman`, temos uma collection de requisições e um arquivo de variavel de ambiente contendo um id para executar os testes automatizados via postman.


