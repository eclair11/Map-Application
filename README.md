# Semantic Web Project

## Members:
- ROMDAN Elias
- TROTTA Nicolas

## Subject:
- This project consist on displaying hospitals' coordinates and different informations, that are stored in the triplestore Apache Jena Fuseki, into a webpage using RDF graphs.
- The user can navigate between cities using a dropdown list, to only show hospitals that are specific to the selected city.
- By clicking on a hospital marker, nearby stations coordinates, received from the website Wikidata, will popup on the map as markers.
- The application goal is to help users, in case of an emergency, to detect public transport stops that are near hospitals.

## Technology:
- Front side: HTML + Thymeleaf
- Server side: Spring Boot 2.4.1
- Dataset server: Apache Jena Fuseki 3.16.0

## Architecture:
- Front side source code is stored in:
```
src/main/resources/
```
- static folder: Contain JS, CSS files and pictures used to design webpages.
- templates folder: Contain the application webpages.

- Server side source code is stored in:
```
src/main/java/com/semweb/map/
```
- jena folder: Contain class used to request Wikidata and to store/extract data in/from the triplestore Apache Jena Fuseki using SPARQL queries.
- model folder: Contain class models used to transport data between the server and the front.

## Launching the application: