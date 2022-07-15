# Meli Challenge - Encontrando Mutantes

## Antes de Iniciar

La aplicación esta desplegada en la plataforma Heroku, esta para optimizar recursos pone las aplicaciones en estado de suspensión después de un tipo de no tener actividad, por lo tanto la primera carga puede tardar mas de lo normal mientras se reactiva.

link: https://meli-challenge-api-0722.herokuapp.com/

## Como ejecutar en local

Ejecutar en terminal:

    - gradlew clean build jacocoMergedReport
    - java -jar applications/app-service/build/libs/meliChallenge.jar

##Servicios expuestos

###Validar ADN mutante
POST → /api/mutant/

body example:

    {
        “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
    }

response example:

    ---------Caso Mutante---------
    {
        “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"],
        "mutant": true
    }

    ---------Caso Humano---------
    {
        “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"],
        "mutant": true
    }

###Estadisticas
GET → /api/stats/

response example:

    {
        "count_human_dna": "8",
        "count_mutant_dna": "4",
        "ratio": "0.5"
    }

##Base de Datos

Los ADN's analizados se guardan en una base de datos no relacional MongoDB.

## Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

