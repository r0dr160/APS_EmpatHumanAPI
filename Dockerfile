# Usar uma imagem base leve com suporte ao Java 17
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o JAR da aplicação para o container
COPY EmpatHumanAPI.jar /app/EmpatHumanAPI.jar

# Expor a porta utilizada pela aplicação
EXPOSE 9090

# Comando para iniciar a aplicação
CMD ["java", "-jar", "/app/EmpatHumanAPI.jar"]
