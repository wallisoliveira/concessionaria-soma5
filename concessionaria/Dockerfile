# Estágio 1: Compilação (Build)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
# O comando abaixo compila o projeto e gera o arquivo .jar
RUN mvn clean package -DskipTests

# Estágio 2: Execução (Runtime)
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copia o arquivo .jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# Comando para rodar a aplicação usando a porta que o Render fornece
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]