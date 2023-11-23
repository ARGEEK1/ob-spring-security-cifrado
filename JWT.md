
## JWT

https://jwt.io/introduction

Es un estándar abierto que permite transmitir información entre dos partes:

JSON Web Token.

### Funcionamiento Session

1. Cliente envía una petición a un servidor (/api/login)
2. Servidor valída el username y password, si no son válidos devolverá una respuesta 401 unauthorized
3. Servidor valída el username y password, si sí son válidos, entonces se almacena el usuario en session
4. Se genera una cookie en el cliente.
5. En próximas peticiones se comprueba que el cliente está en session

Desventajas:

* La información de la session se almacena en el servidor, lo cual consume recursos.

### Funcionamiento

1. Cliente envía una petición a un servidor (/api/login)
2. Servidor valída el username y password, si no son válidos devolverá una respuesta 401 unauthorized
3. Servidor valída el username y password, si sí son válidos, entonces genera un token JWT utilizando una secret key
4. Servidor devuelve el token JWT generado al cliente
5. Cliente envía peticiones a los endpoint REST del servidor utilizando el token JWT en las cabeceras (authorization) de las peticiones.
6. Servidor valída el token JWT en cada petición y si es correcto, permite el acceso a los datos

Ventajas: 

* El token se almacena en el cliente, de manera que consuma menos recursos en el servidor, lo cual petite mejor escalabilidad.

Desventajas: 

* El token está en el navegador, no podríamos invalidarlo antes de la fecha de expiración asignada cuando se creó
  * Lo que se realiza es dar la opción de logout, lo cual simplemente borra el token 

### Estructura del token JWT

3 Partes separadas por un punto (.) y codificadas en base 64 cada una.

1. Header

```json
{
  "alg": "HS512",
  "typ": "JWT"
}
```

2. Payload (información, datos del usuario, no sensibles)

```json
{
  "name": "",
  "admin": true,
  "iat": 1516239022
}
```
3. Signatura

```
HMACKSHA256(
    base64UrlEncode(header) + "." base64UrlEncode(payload), secret
)
```

Ejemplo del Token generado:
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

El User-Agent envía el token JWT en las cabeceras:
```
Authorization: Bearer <token>
```

### Configuración de Spring

Crear proyecto Spring boot con:

* Spring Security
* Spring Web
* Spring Boot Devtools
* Spring Data JPA
* PostgreSQL
* Dependencia JWT (se añade manualmente en el pom.xml)

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>
```