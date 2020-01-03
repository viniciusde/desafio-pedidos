# desafio-pedidos
Rest API 

API desenvolvida utilizando java 8, springboot, spring data, spring security, lombok, swagger, mysql e deploy no heroku.
Para autenticação, foi utilizado o flow Password do oauth2.

OAuth2 - Password flow
client id: $2a$10$f.U73hGiJNsjoD3NTJoz2e6DNdPbWRqEbdJplGQ2dmg.wXoc6qJQ6
secret id: $2a$10$bST.xIbU4lS7L9GLWETuL.4L5hUmUP.Q8FMOfAt4JvzybGKaENs0q

usuário - perfil (USER) - acessos: api/pedidos
username: usu@usuario
pass: 123

usuário - perfil (ADMIN) - acessos: api/produtos; api/categorias; api/clientes; api/pedidos.
username: usu@admin
pass: 123

Collection Postman
https://www.getpostman.com/collections/b16f8fafa3da12b20a28


Próximas features:
-Ajuste swagger com autenticação oauth2.
-melhoria na cobertura de código (testes unitários).
-implementação de controle de estoque de acordo com pedidos.


