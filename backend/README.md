# BACKEND

## N'oubliez pas de créer le schéma wintter dans votre base de données au préalable.

```
server.port = 8181
####################################################################################

    ####### CHOISIR SA BDD ET SON DRIVER #####

    ######## MARIADB ########
    #spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
    #spring.datasource.url=jdbc:mariadb://localhost:3306/wintter
    #spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDB103Dialect

    ######## MYSQL ########
    spring.datasource.url=jdbc:mysql://localhost:3306/wintter
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

    ######## USERNAME AND PASSWORD ########
    spring.datasource.username=VOTRE LOGIN
    spring.datasource.password=VOTRE MOT DE PASSE
    #           Mdp sousou
    #spring.datasource.password=root

    ######## BDD ########
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true

```
