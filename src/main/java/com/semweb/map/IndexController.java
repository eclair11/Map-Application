package com.semweb.map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semweb.map.model.Coordinate;
import com.semweb.map.model.Person;
import com.semweb.map.model.Reponse;
import com.semweb.map.model.SparqlHospitalRequestModel;
import com.semweb.map.model.SparqlTownRequestModel;
import com.semweb.map.utils.OutilCalcul;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model, Reponse reponse) throws JsonParseException, JsonMappingException, IOException {

        
        ObjectMapper objectMapper = new ObjectMapper();
        SparqlHospitalRequestModel[] sparqlHospitalRequestModel = objectMapper.readValue(new File("query.json"), SparqlHospitalRequestModel [].class);

        for (SparqlHospitalRequestModel sparqlHospitalRequestModel2 : sparqlHospitalRequestModel) {
            System.out.println(sparqlHospitalRequestModel2);
        }

        
        ObjectMapper objectMapper2 = new ObjectMapper();
        Person person = objectMapper2.readValue(new File("person.jsonld"), Person.class);
        System.out.println(person);

        ObjectMapper objectMapper3 = new ObjectMapper();
        SparqlTownRequestModel sparqlTownRequestModel = objectMapper3.readValue(new File("request.txt"), SparqlTownRequestModel.class);
        System.out.println(sparqlTownRequestModel);


        ArrayList<Coordinate> coordList = new ArrayList<>();

        coordList.add(new Coordinate(45.447102, 4.386077));
        coordList.add(new Coordinate(45.448178, 4.386066));
        coordList.add(new Coordinate(45.448758, 4.384296));
        coordList.add(new Coordinate(45.446974, 4.384382));
        coordList.add(new Coordinate(45.448486, 4.385906));

        Coordinate[] coords = { new Coordinate(45.447102, 4.386077) };

        model.addAttribute("name", "A tous ceux qui detestent Ubisoft, voici les endroits où vous trouverez son plus grand fan, Altaïr");
        model.addAttribute("coords", coordList);

        model.addAttribute("reponse", new Reponse());
        System.out.println(reponse.getReference());

        double lat = 5697636.899570074;
        double lon = 485591.92708257603;

        Coordinate testCoord = OutilCalcul.metersToLngLat(lon, lat);

        System.out.println("lon = " + testCoord.getLat() + " / lat = " + testCoord.getLon());

        return "index";
    }



}
