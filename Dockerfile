# =========================================================================
# ÉTAPE 1: BUILDER - Compilation de l'application avec Maven et JDK 21
# =========================================================================
# On utilise une image officielle contenant Maven et la version de Java spécifiée dans votre pom.xml (Java 21)
FROM maven:3.9.8-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

# =========================================================================
# ÉTAPE 2: RUNTIME - Création de l'image finale et légère
# =========================================================================
# On part d'une image JRE (Java Runtime Environment) officielle, beaucoup plus légère
# que l'image JDK complète, car elle ne contient que ce qui est nécessaire pour exécuter du Java.
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/atlasdd-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar", "--server.port=${PORT}"]