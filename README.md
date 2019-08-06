# Register API #

Aplica��o visa conceder o cadastro, update, dele��o e sele��o de clientes, al�m de no ato do cadastro pegar a geolocaliza��o do usu�rio e com isso conseguir a temperatura m�nima e m�xima do dia da solicita��o do cadastro.

## Ambiente de Desenvolvimento ##

### Pr�-Requisitos ###

- S.O (Preferencialmente Linux) 
- JDK8
- Gradle 5.4.1
- Docker 18.09.07

### Pr�-Requisitos para Testes ###

- Preferencialmente (SpringToolSuite4 - Eclipse)
- Postman ou Chrome

## Testando aplica��o em MODO DEBUG ##

Temos a possibilidade de executar um comando gradle para subir as dependencias da aplica��o localmente em containers (docker) para fazer todos os testes.
- Acesse a pasta raiz da aplica��o e execute o comando abaixo...
```
$ sudo ./gradlew runDockerComposeDebug
```

Esse comando dispara o docker-compose onde faz subir os containers com uma base postgresql local e um pgAdmin para acompanhar suas altera��es...
```
- PostgreSql estar� rodando no endere�o -> http://localhost:5432/register
    user: root
    senha: 123465
- PgAdmin estar� na porta -> http://localhost:5050 (acesse pelo url do chrome)
    user: admin
    senha: admin
```

## Testando a aplica��o totalmente em Containers ##

Temos a possibilidade de executar um comando gradle para subir toda a aplica��o localmente em docker para fazer todos os testes locais.
- Acesse a pasta raiz da aplica��o e execute o comando abaixo...
```
$ sudo ./gradlew runDockerCompose
```

Esse comando dispara o docker-compose onde faz subir os containers com uma base postgresql local e um pgAdmin para acompanhar suas altera��es e executa um jar da aplica��o deixando dispon�vel na porta 8080...
```
- PostgreSql estar� rodando no endere�o -> http://localhost:5432/register
    user: root
    senha: 123465
- PgAdmin estar� na porta -> http://localhost:5050 (acesse pelo url do chrome)
    user: admin
    senha: admin
- Aplica��o estar� na porta -> http://localhost:8080
```
```
- Acesso ao Swagger - http://localhost:8080/swagger-ui.html (acesse pelo url do chrome)
```
# Testes Unit�rios #

Projeto acompanha os testes unit�rios com JUnit garantindo sempre a persist�ncia e consist�ncia das regras de neg�cio.

# Teste Automatizado #

Na pasta  `/postman`, temos uma collection de requisi��es e um arquivo de vari�vel de ambiente contendo um id para executar os testes automatizados via postman.

- Importe a collection no postman
- Importe a vari�vel de ambiente no postman
- Na Aba Collections, com o mouse em cima da collection Register-Api, voc� ver� um sinal de Play >, clique em RUN (Bot�o Azul), d� scroll down voc� ver� um bot�o Run Register-Api, Ao clicar ser� executado uma sequ�ncia de requisi��es testando a response das mesmas com os valores corretos, Aten��o, voc� deve estar com a api exposta no http://localhost:8080, seja pelo eclipse ou pelo docker. 

# Tecnologias utilizadas #

- PostgreSql banco relacional -> Foi utilizado como base de dados, pois � um banco com suporte a index fazendo com que o select por exemplo seja mais eficaz (�bvio dependendo de como � montado a query), utilizamos constraints para garantir a integridade dos dados relacionados como por exemplo o registro de geolocalidade e o registro do cliente, onde o cliente sendo exclu�do � feito uma cascata de remo��es, possui v�rias funcionalidades, Triggers e procs que n�o foram necess�rias no projeto.
- Docker -> Utilizado no projeto para ajudar no desenvolvimento local, nos dando suporte para uma base de dados e um pgAdmin que ao limpar os containers e subir novamente os dados ser�o exclu�dos da mem�ria, ao subir os containers � feito a cria��o de todo o schema, tabelas, index, contrainsts e inserts necess�rios para os testes locais com pelo menos 1 cliente no banco. (Pode ser utilizado tbm para o ambiente de produ��o, por�m se faz necess�rio uma pipeline e inclus�o de vari�vel de ambiente para que n�o fique exposto os valores e secrets)
- Gradle -> Escolhido pelo fato de ser mais clean, facilitando a vis�o das tasks e compile de depend�ncias, al�m de ter o gradlew wrapper, permitindo executar scripts.
- DevTools -> Ferramenta para agilizar o desenvolvimento, restartando a aplica��o a cada mudan�a significativa salva.
- Teste Unit�rio -> Para garantir que sempre as regras de neg�cios sejam atendidas, utilizado JUnit.
- Teste Automatizado (PostMan) -> Para sempre ter os cen�rios de testes com mais praticidade e com um melhor visual dos erros, foi feito uma sequencia de requisi��es tratadas tipo de resposta esperada do controller.
- Annotation @Transactional -> Para garantir o fluxo de persist�ncia e concorr�ncia da requisi��o at� o commit final, possui muitas utilidades como recuperar um id Pk de tabela que ainda n�o foi criado.
- Async -> Foi utilizado chamada ass�ncrona nos m�todos que n�o precisavam aguardar para dar a resposta ao usu�rio da requisi��o, fazendo a chamada ficar mais perform�tica.
- Application.Properties -> Utilizado a dependencia Hikari para criar um pool de conex�o personalizado com o banco para reduzir o tempo de resposta, sempre atento a limitar um n�mero menor do que o pool que o banco suporta.
- N�o se fez necess�rio aplicar Kubernetes nesse projeto, por�m se tratando de um alto n�vel de requisi��es o Kubernetes pode nos ajudar a escalar horizontalmente a aplica��o atendendo a um n�mero alto de chamadas.
- WebConfig -> Aplicado configura��o para liberar cors.
- SwaggerUi -> Utilizado o SwaggerUi para fornecer uma documenta��o que ajuda visualmente quem vai integrar com a solu��o, podendo tamb�m montar uma documenta��o no Swaggerhub e exportar para o ApiGateway, onde pensando em um cen�rio de altas requisi��es, o Apigateway trabalha muito bem com as cargas e distribui��o das mesmas.


# Deploy #

Para a documenta��o n�o ficar grande, resolvi n�o fazer o deploy pois, como n�o tem pipeline e n�o haver� troca de vari�vel de ambiente no processo, e a instala��o da aplica��o por ec2Intance seria demasiadamente cara para manter nessa poc.

