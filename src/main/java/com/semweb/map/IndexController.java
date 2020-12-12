package com.semweb.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semweb.map.jena.Request;
import com.semweb.map.model.Coordinate;
import com.semweb.map.model.Reponse;
import com.semweb.map.model.ReponseVille;
import com.semweb.map.model.SparqlHospitalRequestLDModel;
import com.semweb.map.model.SparqlHospitalRequestLDModel.HospitalLD;
import com.semweb.map.model.SparqlHospitalRequestLDUniqueModel;
import com.semweb.map.model.SparqlTownRequestModel;
import com.semweb.map.utils.OutilCalcul;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    Reponse reponse = new Reponse();
    ReponseVille ReponseVille = new ReponseVille("Paris");

    /**
     * Index principal de l'application
     */
    @RequestMapping("/")
    public String index(Model model, ReponseVille reponseVille)
            throws JsonParseException, JsonMappingException, IOException {

        
         /* Parsing d'un JSon-ld de la liste des villes disposant d'au moins un hopital */

        String txtRequestResultCities = Request.getCities();

        ObjectMapper objectMapper3 = new ObjectMapper();
        SparqlTownRequestModel sparqlTownRequestModel = objectMapper3.readValue(txtRequestResultCities,
                SparqlTownRequestModel.class);

        ArrayList<String> townList = new ArrayList<>();

        for (String city : sparqlTownRequestModel.getCity()) {
            townList.add(city);
        }

        townList.sort(String.CASE_INSENSITIVE_ORDER);

        model.addAttribute("cities", townList);
        model.addAttribute("reponseVille", reponseVille);

        // System.out.println(townList);
        System.out.println(reponseVille.getName());

        /* Parsing d'un JSon-ld de la liste des hopitaux pour une ville donnée */

        String ville = "Paris";

        if (reponseVille.getName() != null) {
            ville = reponseVille.getName();
        }

        Map<String, String> h = Request.getHospitalsByCity(ville);

        ArrayList<HospitalLD> hospitals = new ArrayList<>();

        if (Long.valueOf(h.get("size")) == 1) {
            ObjectMapper objectMapper5 = new ObjectMapper();
            SparqlHospitalRequestLDUniqueModel requestHospitalUnique = objectMapper5.readValue(h.get("content"),
                    SparqlHospitalRequestLDUniqueModel.class);
            HospitalLD uniqueHospital = fillHospitalUnique(requestHospitalUnique);
            hospitals.add(uniqueHospital);
        } else {
            ObjectMapper objectMapper4 = new ObjectMapper();
            SparqlHospitalRequestLDModel testLD = objectMapper4.readValue(h.get("content"),
                    SparqlHospitalRequestLDModel.class);
            for (HospitalLD hos : testLD.getGraph()) {
                hospitals.add(hos);
            }
        }

        hospitals.sort(Comparator.comparing(HospitalLD::getName, String.CASE_INSENSITIVE_ORDER));

        model.addAttribute("hospitals", hospitals);

        /* Ajout d'une liste de coordonnees pour afficher des pointeurs */

        ArrayList<Coordinate> coordList = new ArrayList<>();

        for (HospitalLD hos : hospitals) {
            if (!hos.getLatitude().isEmpty() || !hos.getLongitude().isEmpty()) {
                coordList.add(
                        new Coordinate(Double.parseDouble(hos.getLatitude()), Double.parseDouble(hos.getLongitude())));
            }
        }

        Coordinate[] coords = { new Coordinate(45.447102, 4.386077) };

        /* Remplissage du modèle */

        model.addAttribute("name",
                "A tous ceux qui detestent Ubisoft, voici les endroits où vous trouverez son plus grand fan, Altaïr");
        model.addAttribute("coords", coordList);

        return "index";
    }

    /* Filling a hospital structure when there is only one hospital in a city. */
    private HospitalLD fillHospitalUnique(SparqlHospitalRequestLDUniqueModel uniqueHospital) {

        HospitalLD hospital = new HospitalLD(uniqueHospital.getId(), uniqueHospital.getWebsite(),
                uniqueHospital.getAddress(), uniqueHospital.getBedCount(), uniqueHospital.getName(),
                uniqueHospital.getPicture(), uniqueHospital.getWikipedia(), uniqueHospital.getWikipediaArticle(),
                uniqueHospital.getLatitude(), uniqueHospital.getLongitude());

        return hospital;

    }

    /* Conversion du format de coordonnees bizarre du fichier CSV */
    private void translateCoordinate() {

        double lat = 5697636.899570074;
        double lon = 485591.92708257603;

        Coordinate testCoord = OutilCalcul.metersToLngLat(lon, lat);

        System.out.println("lon = " + testCoord.getLat() + " / lat = " + testCoord.getLon());

    }


    /**
     * 
     * @param model
     * @param reponse
     * @param reponseVille
     * @return
     */
    @RequestMapping("/ajout")
    public String ajout(Model model, Reponse reponse) {

        /* Récupération de la valeur du 1er champs du formulaire */

        model.addAttribute("reponse", reponse);
        System.out.println(reponse.getReference());

        return "ajout";
    }

}
