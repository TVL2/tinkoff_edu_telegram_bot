FROM openjdk:17
WORKDIR /app/bot
COPY bot/target/bot-1.0-SNAPSHOT.jar bot.jar
ENTRYPOINT ["java", "-jar", "bot.jar"]
