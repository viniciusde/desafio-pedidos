# desafio-pedidos
Rest API 

API desenvolvida utilizando java 8, springboot, spring data, spring security, lombok, swagger, flyway, mysql e deploy no heroku.
Para autenticação, foi utilizado o flow Password do oauth2.

OAuth2 - Password flow<br>
token url: https://desafio-pedidos.herokuapp.com/api/oauth/token<br>
client id: $2a$10$f.U73hGiJNsjoD3NTJoz2e6DNdPbWRqEbdJplGQ2dmg.wXoc6qJQ6<br>
secret id: $2a$10$bST.xIbU4lS7L9GLWETuL.4L5hUmUP.Q8FMOfAt4JvzybGKaENs0q<br><br>

usuário - perfil (USER) - acessos: api/pedidos<br>
username: usu@usuario<br>
pass: 123<br><br>

usuário - perfil (ADMIN) - acessos: api/produtos; api/categorias; api/clientes; api/pedidos.<br>
username: usu@admin<br>
pass: 123<br><br>

Collection Postman<br>
https://www.getpostman.com/collections/b16f8fafa3da12b20a28<br><br>

Próximas features:<br>
-melhorias na documentação da api (swagger) com autenticação oauth2.<br>
-melhoria na cobertura de código (testes unitários).<br>
-implementação de controle de estoque de acordo com pedidos.<br><br>

#Repositórios utilizando tecnologia similares:<br>
https://github.com/viniciusde/expense-service (api de despesas)<br>
https://github.com/viniciusde/expense-webclient (client com thymeleaf, spring 5 reativo).

