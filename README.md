#### Api para registro/consulta de reservas

- API possui documentação utilizando Swagger, a qual pode ser acessada via /swagger-ui/

- Abaixo temos um breve detalhamento dos endpoints  que compõem o serviço.


------------

###### Gestão de reservas

`GET - /api/v1/cheapest` <br>
Retorna o hotel mais barato com base no tipo de cliente e dias repassados, conforme exemplificado abaixo:

```
http://localhost:8080/api/v1/cheapest?dates=2009-03-01&tipoCliente=REGULAR
```

`GET - /api/v1/reservas` <br>
Lista de todas as reservas cadastradas.

`GET - /api/v1/reservas/hotel/{hotel}` <br>
Lista de todas as reservas cadastradas de acordo com o hotel passado na requisição.

`GET - /api/v1/reservas/numeroReserva/{numeroReserva}` <br>
Detalha uma reserva de acordo com o número passado como parâmetro.

`POST - /api/v1/reservas` <br>
Cria uma nova reserva de acordo com os dados informados no corpo da requisição, os quais estão exemplificados abaixo.

```json
{
  "email": "string",
  "fim": "2021-03-01",
  "hotel": "BRIDGEWOOD",
  "inicio": "2021-03-01",
  "nome": "string",
  "numero": "string",
  "observacoes": "string",
  "telefone": "string"
}
```
### Run 

./mvnw spring-boot:run 

Por defautl a api pode ser acessada em http://localhost:8080/api/v1/reservas

### Documentação

Swagger : http://localhost:8080/swagger-ui/index.html
