# Register API #

Aplicação visa conceder o cadastro, update, deleção e seleção de clientes, além de no ato do cadastro pegar a geolocalização do usuário e com isso conseguir a temperatura mínima e máxima do dia da solicitação do cadastro.

## Ambiente de Desenvolvimento ##

### Pré-Requisitos ###

- S.O (Preferencialmente Linux) 
- JDK8
- Gradle 5.4.1
- Docker 18.09.07

### Pré-Requisitos para Testes ###

- Preferencialmente (SpringToolSuite4 - Eclipse)
- Postman ou Chrome

## Testando aplicação em MODO DEBUG ##

Temos a possibilidade de executar um comando gradle para subir as dependencias da aplicação localmente em containers (docker) para fazer todos os testes.
- Acesse a pasta raiz da aplicação e execute o comando abaixo...
```
$ sudo ./gradlew runDockerComposeDebug
```

Esse comando dispara o docker-compose onde faz subir os containers com uma base postgresql local e um pgAdmin para acompanhar suas alterações...
```
- PostgreSql estará rodando no endereço -> http://localhost:5432/register
    user: root
    senha: 123465
- PgAdmin estará na porta -> http://localhost:5050 (acesse pelo url do chrome)
    user: admin
    senha: admin
```

## Testando a aplicação totalmente em Containers ##

Temos a possibilidade de executar um comando gradle para subir toda a aplicação localmente em docker para fazer todos os testes locais.
- Acesse a pasta raiz da aplicação e execute o comando abaixo...
```
$ sudo ./gradlew runDockerCompose
```

Esse comando dispara o docker-compose onde faz subir os containers com uma base postgresql local e um pgAdmin para acompanhar suas alterações e executa um jar da aplicação deixando disponível na porta 8080...
```
- PostgreSql estará rodando no endereço -> http://localhost:5432/register
    user: root
    senha: 123465
- PgAdmin estará na porta -> http://localhost:5050 (acesse pelo url do chrome)
    user: admin
    senha: admin
- Aplicação estará na porta -> http://localhost:8080
```
```
- Acesso ao Swagger - http://localhost:8080/swagger-ui.html (acesse pelo url do chrome)
```
# Testes Unitários #

Projeto acompanha os testes unitários com JUnit garantindo sempre a persistência e consistência das regras de negócio.

# Teste Automatizado #

Na pasta  `/postman`, temos uma collection de requisições e um arquivo de variável de ambiente contendo um id para executar os testes automatizados via postman.

- Importe a collection no postman
- Importe a variável de ambiente no postman
- Na Aba Collections, com o mouse em cima da collection Register-Api, você verá um sinal de Play >, clique em RUN (Botão Azul), dê scroll down você verá um botão Run Register-Api, Ao clicar será executado uma sequência de requisições testando a response das mesmas com os valores corretos, Atenção, você deve estar com a api exposta no http://localhost:8080, seja pelo eclipse ou pelo docker. 

# Tecnologias utilizadas #

- PostgreSql banco relacional -> Foi utilizado como base de dados, pois é um banco com suporte a index fazendo com que o select por exemplo seja mais eficaz (óbvio dependendo de como é montado a query), utilizamos constraints para garantir a integridade dos dados relacionados como por exemplo o registro de geolocalidade e o registro do cliente, onde o cliente sendo excluído é feito uma cascata de remoções, possui várias funcionalidades, Triggers e procs que não foram necessárias no projeto.
- Docker -> Utilizado no projeto para ajudar no desenvolvimento local, nos dando suporte para uma base de dados e um pgAdmin que ao limpar os containers e subir novamente os dados serão excluídos da memória, ao subir os containers é feito a criação de todo o schema, tabelas, index, contrainsts e inserts necessários para os testes locais com pelo menos 1 cliente no banco. (Pode ser utilizado tbm para o ambiente de produção, porém se faz necessário uma pipeline e inclusão de variável de ambiente para que não fique exposto os valores e secrets)
- Gradle -> Escolhido pelo fato de ser mais clean, facilitando a visão das tasks e compile de dependências, além de ter o gradlew wrapper, permitindo executar scripts.
- DevTools -> Ferramenta para agilizar o desenvolvimento, restartando a aplicação a cada mudança significativa salva.
- Teste Unitário -> Para garantir que sempre as regras de negócios sejam atendidas, utilizado JUnit.
- Teste Automatizado (PostMan) -> Para sempre ter os cenários de testes com mais praticidade e com um melhor visual dos erros, foi feito uma sequencia de requisições tratadas tipo de resposta esperada do controller.
- Annotation @Transactional -> Para garantir o fluxo de persistência e concorrência da requisição até o commit final, possui muitas utilidades como recuperar um id Pk de tabela que ainda não foi criado.
- Async -> Foi utilizado chamada assíncrona nos métodos que não precisavam aguardar para dar a resposta ao usuário da requisição, fazendo a chamada ficar mais performática.
- Application.Properties -> Utilizado a dependencia Hikari para criar um pool de conexão personalizado com o banco para reduzir o tempo de resposta, sempre atento a limitar um número menor do que o pool que o banco suporta.
- Não se fez necessário aplicar Kubernetes nesse projeto, porém se tratando de um alto nível de requisições o Kubernetes pode nos ajudar a escalar horizontalmente a aplicação atendendo a um número alto de chamadas.
- WebConfig -> Aplicado configuração para liberar cors.
- SwaggerUi -> Utilizado o SwaggerUi para fornecer uma documentação que ajuda visualmente quem vai integrar com a solução, podendo também montar uma documentação no Swaggerhub e exportar para o ApiGateway, onde pensando em um cenário de altas requisições, o Apigateway trabalha muito bem com as cargas e distribuição das mesmas.


# Deploy #

Para a documentação não ficar grande, resolvi não fazer o deploy pois, como não tem pipeline e não haverá troca de variável de ambiente no processo, e a instalação da aplicação por ec2Intance seria demasiadamente cara para manter nessa poc.

