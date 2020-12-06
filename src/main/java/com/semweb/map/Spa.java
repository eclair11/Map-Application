package com.semweb.map;

import com.bordercloud.sparql.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Spa {



    public static void main(String[] args) {
        try {
            URI endpoint = new URI("https://query.wikidata.org/sparql");
            String querySelect = "#Carte des hôpitaux\n" + "#added 2017-08\n" + "#defaultView:Map\n"
                    + "SELECT * WHERE {\n" + "  ?item wdt:P31/wdt:P279* wd:Q16917;\n" + "        wdt:P625 ?geo .\n"
                    + " \n" + "}\n" + " LIMIT 500";
            SparqlClient sc = new SparqlClient(false);
            sc.setEndpointRead(endpoint);
            SparqlResult sr = sc.query(querySelect);
            sc.printLastQueryAndResult();

            SparqlResultModel rows_queryPopulationInFrance = sr.getModel();
            if (rows_queryPopulationInFrance.getRowCount() > 0) {
                System.out.print("Result population in France: "
                        + rows_queryPopulationInFrance.getRows().get(0));
            }

           
     

            HashMap<String, Object> rep = rows_queryPopulationInFrance.getResultHashMap();
            Set toto = rep.entrySet();

            Iterator totoIt = toto.iterator();

            int nb = rows_queryPopulationInFrance.getRows().size();

            for (Object object : rows_queryPopulationInFrance.getRows()) {
                System.out.println("Point = " + object);
            }

            System.out.print("Taille = " + nb);
           


        } catch (URISyntaxException | SparqlClientException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}