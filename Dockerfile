# =========================================================================
# ÉTAPE 1: BUILDER - Compilation de l'application avec Maven et JDK 21
# =========================================================================
# On utilise une image officielle contenant Maven et la version de Java spécifiée dans votre pom.xml (Java 21)
FROM maven:3.9.8-eclipse-temurin-21 AS builder

# On définit le répertoire de travail dans le conteneur
WORKDIR /app

# On copie d'abord le pom.xml pour profiter du cache de Docker.
# Si les dépendances ne changent pas, Docker n'aura pas à les retélécharger à chaque build.
COPY pom.xml .

# On télécharge les dépendances
RUN mvn dependency:go-offline

# On copie le reste du code source de l'application
COPY src ./src

# On compile l'application, on crée le package .jar et on saute les tests
# car ils ne sont pas nécessaires pour la création de l'image.
# Le résultat sera dans le répertoire /app/target/
RUN mvn clean package -DskipTests

# =========================================================================
# ÉTAPE 2: RUNTIME - Création de l'image finale et légère
# =========================================================================
# On part d'une image JRE (Java Runtime Environment) officielle, beaucoup plus légère
# que l'image JDK complète, car elle ne contient que ce qui est nécessaire pour exécuter du Java.
FROM eclipse-temurin:21-jre

# On définit le répertoire de travail
WORKDIR /app

# On copie UNIQUEMENT le fichier .jar compilé depuis l'étape 'builder'.
# Le nom du jar est déduit de votre pom.xml (artifactId-version.jar).
# L'utilisation d'un wildcard `*.jar` le rend plus robuste aux changements de version.
COPY --from=builder /app/target/atlasdd-0.0.1-SNAPSHOT.jar app.jar

# On expose le port sur lequel l'application va tourner.
# Heroku fournira dynamiquement la variable d'environnement $PORT.
# Spring Boot démarrera par défaut sur 8080 si $PORT n'est pas défini.
EXPOSE 8080

# Commande pour lancer l'application.
# On passe le port fourni par l'environnement (par Heroku) à Spring Boot.
# Si la variable ${PORT} n'est pas définie, Spring Boot utilisera le port par défaut (8080).
CMD ["java", "-jar", "app.jar", "--server.port=${PORT}"]