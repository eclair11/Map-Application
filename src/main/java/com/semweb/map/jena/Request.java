package com.semweb.map.jena;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.Syntax;
import org.apache.jena.rdf.model.Model;

public class Request {

    public static void main(String[] args) {
        getCities();
        getHospitalsByCity("Paris");
    }

    public static String getCities() {
        String content = "";
        String endpoint = "http://localhost:3030/hospitals/sparql";
        String prefix = "PREFIX db: <http://dbpedia.org/ontology/> " + "PREFIX wd: <https://www.wikidata.org/wiki/> ";
        String construct = "CONSTRUCT { wd:Q16917 db:city ?city . } ";
        String where = "WHERE { ?hospital db:city ?city . }";
        String request = prefix + construct + where;
        Query query = QueryFactory.create(request, Syntax.syntaxARQ);
        QueryExecution exec = QueryExecutionFactory.sparqlService(endpoint, query);
        Model model = exec.execConstruct();
        try {
            FileWriter writer = new FileWriter("./requestCities.txt");
            model.write(writer, "JSONLD");
            content = new String(Files.readAllBytes(Paths.get("./request.txt")));
        } catch (IOException e) {
            System.err.println(e);
        }
        return content;
    }

    public static String getHospitalsByCity(String city) {
        String content = "";
        String endpoint = "http://localhost:3030/hospitals/sparql";
        String prefix = "PREFIX rdf: <https://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                + "PREFIX db: <http://dbpedia.org/ontology/> " + "PREFIX ns: <https://www.w3.org/2006/vcard/ns#> "
                + "PREFIX wd: <https://www.wikidata.org/wiki/> " + "PREFIX mo: <http://purl.org/ontology/mo/> "
                + "PREFIX gn: <http://www.geonames.org/ontology/documentation.html#>";
        String construct = "CONSTRUCT { ?hospital db:name ?name ; " + "db:bedCount ?bed ; " + "db:picture ?pic ; "
                + "db:Website ?web ; " + "db:address ?street ; " + "ns:latitude ?lat ; " + "ns:longitude ?lon ; "
                + "mo:wikipedia ?wikien ; " + "gn:wikipediaArticle ?wikifr . } ";
        String where = "WHERE { ?hospital rdf:type wd:Q16917 ; " + "db:city '" + city + "' ; " + "db:name ?name ; "
                + "db:bedCount ?bed ; " + "db:picture ?pic ; " + "db:Website ?web ; " + "db:address ?street ; "
                + "ns:latitude ?lat ; " + "ns:longitude ?lon ; " + "mo:wikipedia ?wikien ; "
                + "gn:wikipediaArticle ?wikifr . }";
        String request = prefix + construct + where;
        Query query = QueryFactory.create(request, Syntax.syntaxARQ);
        QueryExecution exec = QueryExecutionFactory.sparqlService(endpoint, query);
        Model model = exec.execConstruct();
        model.size();
        try {
            FileWriter writer = new FileWriter("./requestHospitals.txt");
            model.write(writer, "JSONLD");
            content = new String(Files.readAllBytes(Paths.get("./requestHospitals.txt")));
        } catch (IOException e) {
            System.err.println(e);
        }
        return content;
    }

}
