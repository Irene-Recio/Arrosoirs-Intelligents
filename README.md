# Arrosoirs-Intelligents

**Type**
Projet IOT

**But**

Le but de ce code est d'activer les arrosoirs lorsque la temperature reçue par le capteur du jardin est trés élevées (>=20).

  **Prérécquis**
Avoir docker-compose installé sur sa machine

  **Exécution**
  Cloner le projet sur sa machine avec git clone https://github.com/Irene-Recio/Arrosoirs-Intelligents.git [git clone](https://github.com/Irene-Recio/Arrosoirs-Intelligents.git)
docker-compose build

docker-compose up -d

java -cp .:amqp-client-5.16.0.jar:slf4j-api-1.7.36.jar:slf4j-simple-1.7.36.jar Capteurs

java -cp .:amqp-client-5.16.0.jar:slf4j-api-1.7.36.jar:slf4j-simple-1.7.36.jar Declencheur

java -cp .:amqp-client-5.16.0.jar:slf4j-api-1.7.36.jar:slf4j-simple-1.7.36.jar Arrosoirs

Si Windows:

java -cp ".;amqp-client-5.16.0.jar;slf4j-api-1.7.36.jar;slf4j-simple-1.7.36.jar" Capteurs

java -cp ".;amqp-client-5.16.0.jar;slf4j-api-1.7.36.jar;slf4j-simple-1.7.36.jar" Declencheur

java -cp ".;amqp-client-5.16.0.jar;slf4j-api-1.7.36.jar;slf4j-simple-1.7.36.jar" Arrosoirs
