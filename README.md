# API IContas

## Breve Descrição
Projeto backend avaliativo desenvolvido para o programa #AceleraiCarros_Tech.
A concepção do projeto foi baseada em um breve resumo das entidades que deveriam fazer parte do projeto e algumas funcionalidades CRUD. O projeto foi desenvolvido utilizando Spring Boot 2.7 com java 17.

## Tecnologias

- Maven 
- Java 17
- Spring Boot 2.7 (Spring Security 5.7) 
- Lombok
- JUnit
- Java JWT 
- Logback
- H2 (Ambiente de teste - limitação do servidor de teste)
- MySQL (Dev/Prod)
- Jacoco
- Docker
- Git
- Github / Github Actions

## Esteira CI/CD
As features devem ser desenvolvida em branches nomeadas `feature/nome_feature`. Ao fazer fazer um push para o repositório remoto, a esteira irá executar os testes unitários, com isso fazendo uma breve validação do código. Ao abrir um Pull Request para main, a esteira irá rodar novamente os testes e fazer o deploy no heroku (profile "test") para que a aplicação seja testada. Ao gerar uma release/tag a esteira irá gerar uma imagem docker de produção e subir para o repositório dockerhub - É importante ressaltar que a configuração estará utilizando o profile "prod".

<img src="/imagens/ci-cd.jpg" alt="CI/CD"/>

## Modelo de Dados e Projeto

### Modelo de dados

<img src="/imagens/modelo-dados.jpg" alt="Modelo de Dados"/>

### Diagrama de Casos de Uso
O diagrama abaixo mostra os casos de uso que foram implementados considerando três tipos de acesso, gerente, correntista e anônimo (apenas para mostrar rotas abertas).

<img src="/imagens/modelo-logico-camadas.jpg" alt="Diagrama Modelo de Dados"/>

### Modelo lógico arquitetural
A seguir temos o modelo arquitetural adotado, baseado em camadas. Essa escolha foi adotada por uma questão de recursos:
- Prazo de entrega do projeto e tempo fora o horário das aulas.
- Experiência dos desenvolvedores com arquiteturas baseadas em indireção e menor desacoplamento.

Neste modelo as requisições que partem do cliente passam pela cadeia de filtros do Spring Security e neste cenário temos a implementação de um filtro específico `TokenValidatorFilterProcess` que faz a validação de um token para as rotas que exigem autenticação.
Após a passagem da requisição pela cadeia de filtros do Spring, ela é repassada ao `@RestController` de destino da requisição (endpoint). A requisição é modelada como um `RequestDTO`. O Controller faz a chamada do `@Service` que é responsável por executar a regra de negócio estabelecida. Por fim, quando necessário, um `Repository` é chamado para fazer manipulação dos dados.

<img src="/imagens/modelo-logico-camadas.jpg" alt="Modelo lógico"/>

### Diagrama de Implantação

A implanatação em produção da aplicação é feita por um container docker que aponta um banco de dados em um container nomeado mysqldb. O container docker está hospedado no registry Dockerhub.

<img src="/imagens/implantacao-prod.jpg" alt="Diagrama de implantação"/>


## Para execução da API foi utilizado Postman com as seguintes rotas: 

### Login
Fazer login
```
POST /api/login
```
```sh
Request:
    {
        "username" : "2222",
        "senha": "123456"
    }
```
```sh
Response:
    {
    "dados": {
        "token": "string"
    },
    "timestamp": TIMESTAMP,
    "statusCode": STATUS_CODE
}
```

## ORIENTAÇÕES QUANTO AO DESENVOLVIMENTO

1. REST CONTROLLER:
1.1 As implementações de classes Rest Controllers deverão ficar no pacote ``br.com.icarros.icontas.resource``.
2. SERVICE
2.2 As implementações de classes de serviço deverão ficar no pacote: ``br.com.icarros.icontas.service``.