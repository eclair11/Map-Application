package com.semweb.map.jena;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bordercloud.sparql.SparqlClient;
import com.bordercloud.sparql.SparqlClientException;
import com.bordercloud.sparql.SparqlResult;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;

public class Record {

    private static List<Hospital> hospitals = new ArrayList<Hospital>();

    public static void main(String[] args) {
        load();
        model();
    }

    public static void load() {
        try {
            URI endpoint = new URI("https://query.wikidata.org/sparql");
            String select = "SELECT DISTINCT ?item " + "(SAMPLE(?name) AS ?n) " + "(SAMPLE(?bed) AS ?b) "
                    + "(SAMPLE(?pic) AS ?p) " + "(SAMPLE(?web) AS ?w) " + "(SAMPLE(?street) AS ?s) "
                    + "(SAMPLE(?city) AS ?c) " + "(SAMPLE(?geo) AS ?g) " + "(SAMPLE(?wikien) AS ?we) "
                    + "(SAMPLE(?wikifr) AS ?wf) ";
            String where = "WHERE { ?item wdt:P31/wdt:P279* wd:Q16917 ; wdt:P17 wd:Q142 ; "
                    + "OPTIONAL { ?item rdfs:label ?name . } " + "OPTIONAL { ?item wdt:P6801 ?bed . } "
                    + "OPTIONAL { ?item wdt:P18 ?pic . } " + "OPTIONAL { ?item wdt:P856 ?Web. } "
                    + "OPTIONAL { ?item wdt:P6375 ?street. } " + "OPTIONAL { ?item wdt:P131/rdfs:label ?city . } "
                    + "OPTIONAL { ?item wdt:P625 ?geo . } "
                    + "OPTIONAL { ?wikien schema:about ?item ; schema:isPartOf <https://en.wikipedia.org/> . } "
                    + "OPTIONAL { ?wikifr schema:about ?item ; schema:isPartOf <https://fr.wikipedia.org/> . } "
                    + "FILTER(lang(?name) = 'fr') . " + "FILTER(lang(?city) = 'fr') . " + "} ";
            String option = "GROUP BY (?item)";
            String query = select + where + option;
            SparqlClient sc = new SparqlClient(false);
            sc.setEndpointRead(endpoint);
            SparqlResult sr = sc.query(query);
            ArrayList<HashMap<String, Object>> rows = sr.getModel().getRows();
            for (HashMap<String, Object> row : rows) {
                Hospital hospital = new Hospital();
                hospital.setId(row.get("item").toString());
                if (row.get("n") != null)
                    hospital.setName(row.get("n").toString());
                else
                    hospital.setName("");
                if (row.get("b") != null)
                    hospital.setBed(row.get("b").toString());
                else
                    hospital.setBed("");
                if (row.get("p") != null)
                    hospital.setPicture(row.get("p").toString());
                else
                    hospital.setPicture("");
                if (row.get("w") != null)
                    hospital.setWeb(row.get("w").toString());
                else
                    hospital.setWeb("");
                if (row.get("s") != null)
                    hospital.setStreet(row.get("s").toString());
                else
                    hospital.setStreet("");
                if (row.get("c") != null)
                    hospital.setCity(row.get("c").toString());
                else
                    hospital.setCity("");
                if (row.get("g") != null) {
                    String[] point = row.get("g").toString().trim().replaceAll("[^0-9\s.]", "").split("\s");
                    hospital.setLongitude(point[0]);
                    hospital.setLatitude(point[1]);
                } else {
                    hospital.setLongitude("");
                    hospital.setLatitude("");
                }
                if (row.get("we") != null)
                    hospital.setWikien(row.get("we").toString());
                else
                    hospital.setWikien("");
                if (row.get("wf") != null)
                    hospital.setWikifr(row.get("wf").toString());
                else
                    hospital.setWikifr("");
                hospitals.add(hospital);
            }
        } catch (URISyntaxException | SparqlClientException e) {
            e.printStackTrace();
        }
    }

    public static void model() {
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
        Property bed = model.createProperty(db + "bedCount");
        Property picture = model.createProperty(db + "picture");
        Property web = model.createProperty(db + "Website");
        Property street = model.createProperty(db + "address");
        Property city = model.createProperty(db + "city");
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
            model.add(id, bed, hospital.getBed());
            model.add(id, picture, hospital.getPicture());
            model.add(id, web, hospital.getWeb());
            model.add(id, street, hospital.getStreet());
            model.add(id, city, hospital.getCity());
            model.add(id, lon, hospital.getLongitude());
            model.add(id, lat, hospital.getLatitude());
            model.add(id, wikien, hospital.getWikien());
            model.add(id, wikifr, hospital.getWikifr());
        }
        try {
            FileWriter writer = new FileWriter("./output.ttl");
            model.write(writer, "TURTLE");
        } catch (IOException e) {
            System.err.println(e);
        }
        String datasetURL = "http://localhost:3030/hospitals";
        String sparqlEndpoint = datasetURL + "/sparql";
        String sparqlUpdate = datasetURL + "/update";
        String graphStore = datasetURL + "/data";
        RDFConnection connect = RDFConnectionFactory.connect(sparqlEndpoint, sparqlUpdate, graphStore);
        connect.load(model);
    }

}
