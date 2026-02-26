# 🚀 API REST - Foro Hub

Proyecto final del curso de Especialización Back-End en Java con Spring Boot.

Este proyecto consiste en el desarrollo de una API REST para un foro académico, donde los usuarios pueden crear, consultar, actualizar y eliminar tópicos.

---

## 📚 Sobre el proyecto

Foro Hub permite:

- Crear nuevos tópicos
- Listar todos los tópicos
- Consultar un tópico por ID
- Actualizar un tópico existente
- Eliminar tópicos
- Autenticación mediante JWT
- Protección de endpoints con Spring Security
- Validación de datos
- Control de reglas de negocio (no permitir tópicos duplicados)

Este proyecto integra conceptos vistos a lo largo del curso como:

- Spring Boot
- JPA / Hibernate
- Flyway
- Spring Security
- JWT
- Validaciones con Bean Validation
- Manejo global de errores

---

## 🛠 Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Security
- JWT (Auth0)
- JPA / Hibernate
- Flyway
- Maven
- MySQL

---

## 🔐 Autenticación

La API utiliza autenticación basada en JWT.

1. Se realiza login en:

POST /login


2. Se recibe un token JWT.
3. Se envía el token en los demás endpoints usando:


Authorization: Bearer {token}


---

## 📌 Endpoints principales

### 🔹 Crear tópico

POST /topicos


### 🔹 Listar tópicos

GET /topicos


### 🔹 Detallar tópico

GET /topicos/{id}


### 🔹 Actualizar tópico

PUT /topicos/{id}


### 🔹 Eliminar tópico

DELETE /topicos/{id}


---

## ✅ Reglas de negocio implementadas

- No se permiten tópicos duplicados (mismo título y mensaje).
- Todos los campos son obligatorios.
- Los endpoints están protegidos con autenticación.
- Se manejan correctamente errores 400, 403 y 404.

---

## 🎯 Objetivo del proyecto

Este proyecto representa la consolidación de los conocimientos adquiridos durante el curso, integrando:

- Seguridad
- Persistencia
- Arquitectura REST
- Validaciones
- Buenas prácticas

Es el cierre del proceso de formación en Back-End con Java y Spring Boot.

---

## 👩‍💻 Autora

Desarrollado por Kenia Jiménez  
Proyecto académico - Especialización Back-End
