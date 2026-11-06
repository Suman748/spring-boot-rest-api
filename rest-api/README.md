# Spring Boot REST API (Product CRUD)

Requires Java 17+ and Maven.

    mvn spring-boot:run

## Endpoints
| Method | URL | Success | Errors |
|---|---|---|---|
| GET | /api/products | 200 | |
| GET | /api/products/{id} | 200 | 404 |
| POST | /api/products | 201 + Location | 400 |
| PUT | /api/products/{id} | 200 | 400, 404 |
| DELETE | /api/products/{id} | 204 | 404 |

## Try it
    curl -i -X POST localhost:8080/api/products -H "Content-Type: application/json" \
      -d '{"name":"Keyboard","description":"Mechanical","price":49.99,"quantity":10}'
    curl -i localhost:8080/api/products
    curl -i localhost:8080/api/products/1
    curl -i -X PUT localhost:8080/api/products/1 -H "Content-Type: application/json" \
      -d '{"name":"Keyboard v2","price":59.99,"quantity":5}'
    curl -i -X DELETE localhost:8080/api/products/1
    # validation error (400):
    curl -i -X POST localhost:8080/api/products -H "Content-Type: application/json" -d '{"name":"","price":-1}'
