package com.semweb.map;

import com.bordercloud.sparql.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

public class Spa3 {

    public static void main(String[] args) {
        try {
            URI endpoint = new URI("https://query.wikidata.org/sparql");
            String querySelect  = "SELECT * WHERE {\n" +
            "  \n" +
            "  ?item wdt:P31/wdt:P279* wd:Q16917;\n" +
            "        wdt:P17 wd:Q142;\n" +
            "        \n" +
            "        OPTIONAL { ?item wdt:P6801 ?nbLits. }\n" +
            "        OPTIONAL { ?item wdt:P18 ?image. }\n" +
            "        OPTIONAL { ?item wdt:P856 ?siteWeb. }\n" +
            "        OPTIONAL { ?item wdt:P131/rdfs:label ?ville. }\n" +
            "        OPTIONAL { ?sitelinkEn schema:about ?item ; schema:isPartOf <https://en.wikipedia.org/> }\n" +
            "        OPTIONAL { ?sitelinkFr schema:about ?item ; schema:isPartOf <https://fr.wikipedia.org/> }\n" +
            "  \n" +
            "        OPTIONAL {  \n" +
            "         ?item wdt:P625 ?coord .\n" +
            "         ?item p:P625 ?coordinate .\n" +
            "         ?coordinate psv:P625 ?coordinate_node .\n" +
            "         ?coordinate_node wikibase:geoLatitude ?lat .\n" +
            "         ?coordinate_node wikibase:geoLongitude ?lon .\n" +
            "        }\n" +
            "  \n" +
            "  filter(lang(?ville) = \"fr\").\n" +
            "  \n" +
            "  SERVICE wikibase:label {\n" +
            "    bd:serviceParam wikibase:language \"en\" .\n" +
            "    bd:serviceParam wikibase:language \"fr\" .\n" +
            "  }\n" +
            "\n" +
            "}";
            SparqlClient sc = new SparqlClient(false);
            sc.setEndpointRead(endpoint);
            SparqlResult sr = sc.query(querySelect);
            //sc.printLastQueryAndResult();

            printResult(sr.getModel(),30);
        } catch (URISyntaxException | SparqlClientException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void printResult(SparqlResultModel rs , int size) {

        for (String variable : rs.getVariables()) {
            System.out.print(String.format("%-"+size+"."+size+"s", variable ) + " | ");
        }
        System.out.print("\n");
        for (HashMap<String, Object> row : rs.getRows()) {
            for (String variable : rs.getVariables()) {
                System.out.print(String.format("%-"+size+"."+size+"s", row.get(variable)) + " | ");
            }
            System.out.print("\n");
        }
    }
}