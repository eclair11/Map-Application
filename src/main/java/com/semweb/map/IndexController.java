package com.semweb.map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semweb.map.jena.Request;
import com.semweb.map.model.Coordinate;
import com.semweb.map.model.Person;
import com.semweb.map.model.Reponse;
import com.semweb.map.model.ReponseVille;
import com.semweb.map.model.SparqlHospitalRequestLDModel;
import com.semweb.map.model.SparqlHospitalRequestModel;
import com.semweb.map.model.SparqlTownRequestModel;
import com.semweb.map.model.TestLD;
import com.semweb.map.model.SparqlHospitalRequestLDModel.HospitalLD;
import com.semweb.map.utils.OutilCalcul;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    Reponse reponse = new Reponse();
    ReponseVille ReponseVille = new ReponseVille("");
    

    @RequestMapping("/")
    public String index(Model model, Reponse reponse, ReponseVille reponseVille) throws JsonParseException, JsonMappingException, IOException {


        /* Parsing d'un JSon d'une requete sparql des hopitaux  */
        
        ObjectMapper objectMapper = new ObjectMapper();
        SparqlHospitalRequestModel[] sparqlHospitalRequestModel = objectMapper.readValue(new File("query.json"), SparqlHospitalRequestModel [].class);

        for (SparqlHospitalRequestModel sparqlHospitalRequestModel2 : sparqlHospitalRequestModel) {
            //System.out.println(sparqlHospitalRequestModel2);
        }

        /* Parsing d'un JSon-ld d'une structure Person, juste pour tester  */
        
        ObjectMapper objectMapper2 = new ObjectMapper();
        Person person = objectMapper2.readValue(new File("person.jsonld"), Person.class);
        //System.out.println(person);


        /* Parsing d'un JSon-ld de la liste des villes disposant d'au moins un hopital */

        String c = Request.getCities();

        ObjectMapper objectMapper3 = new ObjectMapper();
        SparqlTownRequestModel sparqlTownRequestModel = objectMapper3.readValue(c, SparqlTownRequestModel.class);

        ArrayList<String> townList = new ArrayList<>();

        for (String city : sparqlTownRequestModel.getCity()) {
            townList.add(city);
        }
        
        
        model.addAttribute("cities", townList);
        model.addAttribute("reponseVille", reponseVille);

        //System.out.println(townList);
        System.out.println(reponseVille.getName());


        /* Parsing d'un JSon-ld de la liste des hopitaux pour une ville donnée*/

        String ville = "Paris";
        if (reponseVille.getName() != null) {
            ville = reponseVille.getName();
        }
        String h = Request.getHospitalsByCity(ville);

        ObjectMapper objectMapper4 = new ObjectMapper();
        SparqlHospitalRequestLDModel testLD = objectMapper4.readValue(h, SparqlHospitalRequestLDModel.class);
        
        ArrayList<HospitalLD> hospitals = new ArrayList<>();

        for (HospitalLD hos : testLD.getGraph()) {
            hospitals.add(hos);
        }
        
        for (HospitalLD hos : hospitals) {
            //System.out.println(hos);
        }

        hospitals.sort(Comparator.comparing(HospitalLD::getName, String.CASE_INSENSITIVE_ORDER));

        model.addAttribute("hospitals", hospitals);


        /* Ajout d'une liste de coordonnees pour afficher des pointeurs */

        ArrayList<Coordinate> coordList = new ArrayList<>();

        /*
        coordList.add(new Coordinate(45.447102, 4.386077));
        coordList.add(new Coordinate(45.448178, 4.386066));
        coordList.add(new Coordinate(45.448758, 4.384296));
        coordList.add(new Coordinate(45.446974, 4.384382));
        coordList.add(new Coordinate(45.448486, 4.385906));
        */

        for (HospitalLD hos : hospitals) {
            if(!hos.getLatitude().isEmpty() || !hos.getLongitude().isEmpty()){
                coordList.add(new Coordinate(Double.parseDouble(hos.getLatitude()), Double.parseDouble(hos.getLongitude())));
            }
        }

        Coordinate[] coords = { new Coordinate(45.447102, 4.386077) };

        /* Remplissage du modèle */

        model.addAttribute("name", "A tous ceux qui detestent Ubisoft, voici les endroits où vous trouverez son plus grand fan, Altaïr");
        model.addAttribute("coords", coordList);

        /* Récupération de la valeur du 1er champs du formulaire */

        model.addAttribute("reponse", reponse);
        //System.out.println(reponse.getReference());

        /* Conversion du format de coordonnees bizarre du fichier CSV */

        double lat = 5697636.899570074;
        double lon = 485591.92708257603;

        Coordinate testCoord = OutilCalcul.metersToLngLat(lon, lat);

        //System.out.println("lon = " + testCoord.getLat() + " / lat = " + testCoord.getLon());

        return "index";
    }



}
