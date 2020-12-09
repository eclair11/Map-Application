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
            String query = "SELECT DISTINCT ?item ?name ?bed ?pic ?web ?street ?city ?lat ?lon ?wikien ?wikifr WHERE {"
            + "?item wdt:P31/wdt:P279* wd:Q16917 ;"
            +       "wdt:P17 wd:Q142 ;"
            + "OPTIONAL { ?item rdfs:label ?name. }"
            + "OPTIONAL { ?item wdt:P6801 ?bed . }"
            + "OPTIONAL { ?item wdt:P18 ?pic . }"
            + "OPTIONAL { ?item wdt:P856 ?Web. }"
            + "OPTIONAL { ?item wdt:P6375 ?street. }"
            + "OPTIONAL { ?item wdt:P131/rdfs:label ?city . }"
            + "OPTIONAL { ?wikien schema:about ?item ; schema:isPartOf <https://en.wikipedia.org/> }"
            + "OPTIONAL { ?wikifr schema:about ?item ; schema:isPartOf <https://fr.wikipedia.org/> }"
            + "OPTIONAL { ?item p:P625 ?coordinate ."
            +            "?coordinate psv:P625 ?node ."
            +            "?node wikibase:geoLatitude ?lat ."
            +            "?node wikibase:geoLongitude ?lon . }"
            + "FILTER(lang(?name) = \"fr\") ."
            + "FILTER(lang(?city) = \"fr\") ."
            + "SERVICE wikibase:label {"
            +         "bd:serviceParam wikibase:language \"en\" ."
            +         "bd:serviceParam wikibase:language \"fr\" . }"
            + "}";
            SparqlClient sc = new SparqlClient(false);
            sc.setEndpointRead(endpoint);
            SparqlResult sr = sc.query(query);
            ArrayList<HashMap<String, Object>> rows = sr.getModel().getRows();
            for (HashMap<String,Object> row : rows) {
                Hospital hospital = new Hospital();
                if (row.get("item") != null) hospital.setId(row.get("item").toString()); else hospital.setId("");
                if (row.get("name") != null) hospital.setName(row.get("name").toString()); else hospital.setName("");
                if (row.get("bed") != null) hospital.setBed(row.get("bed").toString()); else hospital.setBed("");
                if (row.get("pic") != null) hospital.setPicture(row.get("pic").toString()); else hospital.setPicture("");
                if (row.get("web") != null) hospital.setWeb(row.get("web").toString()); else hospital.setWeb("");
                if (row.get("street") != null) hospital.setStreet(row.get("street").toString()); else hospital.setStreet("");
                if (row.get("city") != null) hospital.setCity(row.get("city").toString()); else hospital.setCity("");
                if (row.get("lat") != null) hospital.setLatitude(row.get("lat").toString()); else hospital.setLatitude("");
                if (row.get("lon") != null) hospital.setLongitude(row.get("lon").toString()); else hospital.setLongitude("");
                if (row.get("wikien") != null) hospital.setWikien(row.get("wikien").toString()); else hospital.setWikien("");
                if (row.get("wikifr") != null) hospital.setWikifr(row.get("wikifr").toString()); else hospital.setWikifr("");
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
        Resource code = model.createResource(wd + "Q16917");
        Property type = model.createProperty(rdf + "type");
        Property name = model.createProperty(db + "name");
        Property bed = model.createProperty(db + "bedCount");
        Property picture = model.createProperty(db + "picture");
        Property web = model.createProperty(db + "Website");
        Property street = model.createProperty(db + "address");
        Property city = model.createProperty(db + "city");
        Property lat = model.createProperty(ns + "latitude");
        Property lon = model.createProperty(ns + "longitude");
        Property wiki = model.createProperty(mo + "wikipedia");
        model.setNsPrefix("rdf", rdf);
        model.setNsPrefix("db", db);
        model.setNsPrefix("ns", ns);
        model.setNsPrefix("wd", wd);
        model.setNsPrefix("mo", mo);
        for (Hospital hospital : hospitals) {
            Resource id = model.createResource(hospital.getId());
            model.add(id, type, code);
            model.add(id, name, hospital.getName());
            model.add(id, bed, hospital.getBed());
            model.add(id, picture, hospital.getPicture());
            model.add(id, web, hospital.getWeb());
            model.add(id, street, hospital.getStreet());
            model.add(id, city, hospital.getCity());
            model.add(id, lat, hospital.getLatitude());
            model.add(id, lon, hospital.getLongitude());
            model.add(id, wiki, hospital.getWikien());
            model.add(id, wiki, hospital.getWikifr());
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
