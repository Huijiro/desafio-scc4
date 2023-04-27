# Pure JS

## Instalação e execução

1. Faça um clone desse repositório;

   ```bash
   git clone https://github.com/scc4/pure-js && cd pure-js && git checkout gabriel-rodrigues
   ```

2. Entre na pasta;

   ```bash
   cd pure-js
   ```

3. Suba o container;

   Requerimentos:

   - Docker
   - Docker Compose

   ```bash
   docker-compose up -d
   ```

4. (Alternativa) Rode o frontend e o backend localmente;

   Requerimentos:

   - NodeJS
   - NPM
   - Java LTS
   - Maven

   ```bash
   cd frontend && npm install && npm run dev

   cd backend && mvn package && java -jar target/pure-js-0.0.1-SNAPSHOT.jar
   ```

5. Accesse `http://localhost:5173` para ver o resultado do frontend.

6. Accesse `http://localhost:8080` para ver o resultado do backend.

## Tecnologias

Backend:

- [Java](https://www.java.com/pt-BR/)

- [Maven](https://maven.apache.org/)

- [Spring Boot](https://spring.io/projects/spring-boot)

Frontend:

- [NodeJS](https://nodejs.org/en/)

- [NPM](https://www.npmjs.com/)

- [Vite](https://vitejs.dev/)
