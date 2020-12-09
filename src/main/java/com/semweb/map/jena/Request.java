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
    }

    public static String getCities() {
        String content = "";
        String endpoint = "http://localhost:3030/hospitals/sparql";
        String request = "PREFIX db: <http://dbpedia.org/ontology/> "
        + "PREFIX wd: <https://www.wikidata.org/wiki/> "
        + "CONSTRUCT { wd:Q16917 db:city ?city } "
        + "WHERE { ?hospital db:city ?city }";
        Query query = QueryFactory.create(request, Syntax.syntaxARQ);
        QueryExecution exec = QueryExecutionFactory.sparqlService(endpoint, query);
        Model model = exec.execConstruct();
        try {
            FileWriter writer = new FileWriter("./request.txt");
            model.write(writer, "JSONLD");
            content = new String(Files.readAllBytes(Paths.get("./request.txt")));
        } catch (IOException e) {
            System.err.println(e);
        }
        return content;
    }
    
}
