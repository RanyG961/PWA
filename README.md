# Wintter

## Sujet du projet

Notre sujet est grandement inspiré de Twitter et de ses fonctionnalités.
Nous voulions refaire Twitter en une version simplifié, avec les fonctionnalités de base qui ont fait le grand succès de Twitter.

---

## Instructions

Pour compiler et lancer le projet, il faut tout d'abord :

1. Créer un schéma qui s'appelle wintter dans votre BDD.
2. Changer les propriétés dans le fichier application.properties pour qu'ils correspondent à vos login de BDD [READMEBACK](backend/README.md).
3. Puis il faudra lancer le serveur Spring (dossier backend) de la façon que vous souhaitez (VSCode ou IntelliJ).
4. Il faudra vous rendre dans le dossier frontend pour lancer le serveur node [READMEVUE](frontend/README.md)
5. Et le tour est joué.

---

## Description de l'architecture

Nous avons une BDD qui interagit avec le serveur Spring grâce au JPA Repository, notre Serveur Spring est un serveur Spring Rest ce qui permet d'avoir une communication optimale entre les pages Vue et le serveur ce que nous appelons le **FrontEnd** et le **BackEnd**.
Le projet backend est découpé en plusieurs package ce qui améliore grandemnt la lisibilité.
Le projet frontend est découpé quant à lui en Views - Components avec une utilisation de VueRouter et VueX.
Notre projet est un projet MVC, Modèle - Vue - Controlleur ce qui permet une meilleure maintenance du code.
