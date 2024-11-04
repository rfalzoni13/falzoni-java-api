# Projeto de Demonstração Web Api Java por Renato Falzoni

Este projeto é de simples demonstração, desenvolvido com tecnologia Spring Boot 3 em Java

O Projeto está no modelo MVC e consta com as seguintes camadas:

- Configuration: Configurações do projeto como Spring Security, Swagger, etc.
- Controllers: As Controllers da aplicação, responsável pelas rotas que receberão as requisições e farão as devidas tratativas
- Domain: Coração do projeto com as entidades do negócio
- Repositories: Repositório de dados responsável pela persistência dos mesmos.
- Services: Responsável pela aplicação das regras de negócio.

O Projeto ainda possui testes automatizados, como testes de integração e testes unitários

Atualmente, o projeto suporte os seguintes bancos de dados:
- MySQL 8.0
- H2 (Banco de dados em memória para testes).
