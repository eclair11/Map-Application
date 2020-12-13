package com.semweb.map.jena;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.Syntax;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;

public class Record {

    private static List<Hospital> hospitals = new ArrayList<Hospital>();

    public static void load() {
        String endpoint = "https://query.wikidata.org/sparql";
        String prefix = "PREFIX schema: <http://schema.org/> " + "PREFIX wd: <http://www.wikidata.org/entity/> "
                + "PREFIX wdt: <http://www.wikidata.org/prop/direct/> "
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> ";
        String select = "SELECT DISTINCT ?item " + "(SAMPLE(?name) AS ?n) " + "(SAMPLE(?city) AS ?c) "
                + "(SAMPLE(?pic) AS ?p) " + "(SAMPLE(?bed) AS ?b) " + "(SAMPLE(?str) AS ?s) " + "(SAMPLE(?web) AS ?w) "
                + "(SAMPLE(?geo) AS ?g) " + "(SAMPLE(?wikien) AS ?we) " + "(SAMPLE(?wikifr) AS ?wf) ";
        String where = "WHERE { ?item wdt:P31/wdt:P279* wd:Q16917 ; wdt:P17 wd:Q142 ; "
                + "{ ?item rdfs:label ?name . } " + "{ ?item wdt:P131/rdfs:label ?city . } "
                + "OPTIONAL { ?item wdt:P18 ?pic . } " + "OPTIONAL { ?item wdt:P6801 ?bed . } "
                + "OPTIONAL { ?item wdt:P6375 ?str . } " + "OPTIONAL { ?item wdt:P856 ?web . } "
                + "{ ?item wdt:P625 ?geo . } "
                + "OPTIONAL { ?wikien schema:about ?item ; schema:isPartOf <https://en.wikipedia.org/> . } "
                + "OPTIONAL { ?wikifr schema:about ?item ; schema:isPartOf <https://fr.wikipedia.org/> . } "
                + "FILTER(lang(?name) = 'fr') . " + "FILTER(lang(?city) = 'fr') . " + "} ";
        String option = "GROUP BY (?item)";
        String request = prefix + select + where + option;
        Query query = QueryFactory.create(request, Syntax.syntaxARQ);
        QueryExecution exec = QueryExecutionFactory.sparqlService(endpoint, query);
        ResultSet res = exec.execSelect();
        while (res.hasNext()) {
            QuerySolution sol = res.next();
            Hospital hospital = new Hospital();
            hospital.setId(sol.getResource("item").getLocalName());
            hospital.setName(sol.getLiteral("n").getLexicalForm());
            hospital.setCity(sol.getLiteral("c").getLexicalForm());
            if (sol.getResource("p") != null) {
                hospital.setPicture(sol.getResource("p").toString());
            } else {
                hospital.setPicture("");
            }
            if (sol.getLiteral("b") != null) {
                hospital.setBed(sol.getLiteral("b").getLexicalForm());
            } else {
                hospital.setBed("");
            }
            if (sol.getLiteral("s") != null) {
                hospital.setStreet(sol.getLiteral("s").getLexicalForm());
            } else {
                hospital.setStreet("");
            }
            if (sol.getResource("w") != null) {
                hospital.setWeb(sol.getResource("w").toString());
            } else {
                hospital.setWeb("");
            }
            if (sol.getResource("we") != null) {
                hospital.setWikien(sol.getResource("we").toString());
            } else {
                hospital.setWikien("");
            }
            if (sol.getResource("wf") != null) {
                hospital.setWikifr(sol.getResource("wf").toString());
            } else {
                hospital.setWikifr("");
            }
            String[] point = sol.getLiteral("g").getLexicalForm().trim().replaceAll("[^0-9\s.]", "").split("\s");
            hospital.setLongitude(point[0]);
            hospital.setLatitude(point[1]);
            hospitals.add(hospital);
        }
        model();
    }

    private static void model() {
        Model model = ModelFactory.createDefaultModel();
        String rdf = "https://www.w3.org/1999/02/22-rdf-syntax-ns#";
        String db = "http://dbpedia.org/ontology/";
        String ns = "https://www.w3.org/2006/vcard/ns#";
        String wd = "https://www.wikidata.org/wiki/";
        String mo = "http://purl.org/ontology/mo/";
        String gn = "http://www.geonames.org/ontology/documentation.html#";
        Resource code = model.createResource(wd + "Q16917");
        Property type = model.createProperty(rdf + "type");
        Property name = model.createProperty(db + "name");
        Property city = model.createProperty(db + "city");
        Property picture = model.createProperty(db + "picture");
        Property bed = model.createProperty(db + "bedCount");
        Property street = model.createProperty(db + "address");
        Property web = model.createProperty(db + "Website");
        Property lon = model.createProperty(ns + "longitude");
        Property lat = model.createProperty(ns + "latitude");
        Property wikien = model.createProperty(mo + "wikipedia");
        Property wikifr = model.createProperty(gn + "wikipediaArticle");
        model.setNsPrefix("rdf", rdf);
        model.setNsPrefix("db", db);
        model.setNsPrefix("ns", ns);
        model.setNsPrefix("wd", wd);
        model.setNsPrefix("mo", mo);
        model.setNsPrefix("gn", gn);
        for (Hospital hospital : hospitals) {
            Resource id = model.createResource(hospital.getId());
            model.add(id, type, code);
            model.add(id, name, hospital.getName());
            model.add(id, city, hospital.getCity());
            model.add(id, picture, hospital.getPicture());
            model.add(id, bed, hospital.getBed());
            model.add(id, street, hospital.getStreet());
            model.add(id, web, hospital.getWeb());
            model.add(id, lon, hospital.getLongitude());
            model.add(id, lat, hospital.getLatitude());
            model.add(id, wikien, hospital.getWikien());
            model.add(id, wikifr, hospital.getWikifr());
        }
        String url = "http://localhost:3030/hospitals";
        String endpoint = url + "/sparql";
        String update = url + "/update";
        String graph = url + "/data";
        RDFConnection connect = RDFConnectionFactory.connect(endpoint, update, graph);
        connect.load(model);
    }

}
