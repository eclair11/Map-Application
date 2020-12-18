package com.semweb.map.jena;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.Syntax;
import org.apache.jena.rdf.model.Model;

public class Request {

    public static String getCities() {
        String content = "";
        String endpoint = "http://localhost:3030/hospitals/sparql";
        String prefix = "PREFIX db: <http://dbpedia.org/ontology/> " + "PREFIX wd: <http://www.wikidata.org/wiki/> ";
        String construct = "CONSTRUCT { wd:Q16917 db:city ?city . } ";
        String where = "WHERE { ?hospital db:city ?city . }";
        String request = prefix + construct + where;
        Query query = QueryFactory.create(request, Syntax.syntaxARQ);
        QueryExecution exec = QueryExecutionFactory.sparqlService(endpoint, query);
        Model model = exec.execConstruct();
        try {
            FileWriter writer = new FileWriter("./output.txt");
            model.write(writer, "JSONLD");
            content = new String(Files.readAllBytes(Paths.get("./output.txt")));
        } catch (IOException e) {
            System.err.println(e);
        }
        return content;
    }

    public static Map<String, String> getHospitalsByCity(String city) {
        Map<String, String> result = new HashMap<>();
        String content = "";
        String endpoint = "http://localhost:3030/hospitals/sparql";
        String prefix = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                + "PREFIX db: <http://dbpedia.org/ontology/> " + "PREFIX ns: <http://www.w3.org/2006/vcard/ns#> "
                + "PREFIX wd: <http://www.wikidata.org/wiki/> " + "PREFIX mo: <http://purl.org/ontology/mo/> "
                + "PREFIX gn: <http://www.geonames.org/ontology/documentation.html#>";
        String construct = "CONSTRUCT { ?hospital db:name ?name ; " + "db:bedCount ?bed ; " + "db:picture ?pic ; "
                + "db:Website ?web ; " + "db:address ?street ; " + "ns:latitude ?lat ; " + "ns:longitude ?lon ; "
                + "mo:wikipedia ?wikien ; " + "gn:wikipediaArticle ?wikifr . } ";
        String where = "WHERE { ?hospital rdf:type wd:Q16917 ; " + "db:city \"" + city + "\" ; " + "db:name ?name ; "
                + "db:bedCount ?bed ; " + "db:picture ?pic ; " + "db:Website ?web ; " + "db:address ?street ; "
                + "ns:latitude ?lat ; " + "ns:longitude ?lon ; " + "mo:wikipedia ?wikien ; "
                + "gn:wikipediaArticle ?wikifr . }";
        String request = prefix + construct + where;
        Query query = QueryFactory.create(request, Syntax.syntaxARQ);
        QueryExecution exec = QueryExecutionFactory.sparqlService(endpoint, query);
        Model model = exec.execConstruct();
        String size = String.valueOf(model.size() / construct.split(";").length);
        try {
            FileWriter writer = new FileWriter("./output.txt");
            model.write(writer, "JSONLD");
            content = new String(Files.readAllBytes(Paths.get("./output.txt")));
        } catch (IOException e) {
            System.err.println(e);
        }
        result.put("content", content);
        result.put("size", size);
        return result;
    }

    public static Map<String, String> getNearbyStations(Double lon, Double lat) {
        Map<String, String> result = new HashMap<>();
        String content = "";
        String endpoint = "https://query.wikidata.org/sparql";
        String prefix = "PREFIX wd: <http://www.wikidata.org/entity/> "
                + "PREFIX wdt: <http://www.wikidata.org/prop/direct/> " + "PREFIX p: <http://www.wikidata.org/prop/> "
                + "PREFIX psv: <http://www.wikidata.org/prop/statement/value/> "
                + "PREFIX geof: <http://www.opengis.net/def/geosparql/function/> "
                + "PREFIX wikibase: <http://wikiba.se/ontology#> " + "PREFIX bd: <http://www.bigdata.com/rdf#> ";
        String construct = "CONSTRUCT { ?item wikibase:label ?itemLabel ; " + "wikibase:description ?itemDescription ; "
                + "wikibase:geoLongitude ?lon ; " + "wikibase:geoLatitude ?lat . } ";
        String where = "WHERE { ?item wdt:P31/wdt:P279* wd:Q548662 ; " + "wdt:P17 wd:Q142 ; " + "wdt:P625 ?geo1 ; "
                + "p:P625 ?coord . " + "?coord psv:P625 ?node . " + "?node wikibase:geoLongitude ?lon ; "
                + "wikibase:geoLatitude ?lat . "
                + "FILTER(geof:distance(?geo1, \"POINT(" + lon + " " + lat + ")\") <= 3) . "
                + "SERVICE wikibase:label { bd:serviceParam wikibase:language \"fr, en\" } }";
        String request = prefix + construct + where;
        Query query = QueryFactory.create(request, Syntax.syntaxARQ);
        QueryExecution exec = QueryExecutionFactory.sparqlService(endpoint, query);
        Model model = exec.execConstruct();
        String size = String.valueOf(model.size() / construct.split(";").length);
        try {
            FileWriter writer = new FileWriter("./output.txt");
            model.write(writer, "JSONLD");
            content = new String(Files.readAllBytes(Paths.get("./output.txt")));
        } catch (IOException e) {
            System.err.println(e);
        }
        result.put("content", content);
        result.put("size", size);
        return result;
    }

    public static void main(String[] args) {
        getNearbyStations(4.36361, 45.48139);
    }

}
