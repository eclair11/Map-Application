package com.semweb.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semweb.map.jena.Record;
import com.semweb.map.jena.Request;
import com.semweb.map.model.Coordinate;
import com.semweb.map.model.Reponse;
import com.semweb.map.model.ReponseVille;
import com.semweb.map.model.SparqlHospitalRequestLDModel;
import com.semweb.map.model.SparqlHospitalRequestLDModel.HospitalLD;
import com.semweb.map.model.SparqlHospitalRequestLDUniqueModel;
import com.semweb.map.model.SparqlTownRequestModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    ObjectMapper objectMapper = new ObjectMapper();
    Reponse reponse = new Reponse();
    ReponseVille ReponseVille = new ReponseVille("");

    @RequestMapping("/")
    public String index(Model model, Reponse reponse) {
        model.addAttribute("reponse", reponse);
        return "index";
    }

    @RequestMapping("/add")
    public String add(Model model, Reponse reponse) {
        if (reponse.getLabel().equals("true")) {
            Record.load();
        }
        return "redirect:/map";
    }

    @RequestMapping("/map")
    public String map(Model model, ReponseVille reponseVille)
            throws JsonParseException, JsonMappingException, IOException {
        /* Get cities with hospitals */
        SparqlTownRequestModel sparqlTownRequestModel = objectMapper.readValue(Request.getCities(),
                SparqlTownRequestModel.class);
        ArrayList<String> townList = new ArrayList<>();
        for (String city : sparqlTownRequestModel.getCity()) {
            townList.add(city);
        }
        townList.sort(String.CASE_INSENSITIVE_ORDER);
        model.addAttribute("cities", townList);
        model.addAttribute("reponseVille", reponseVille);
        /* Get hospitals by city */
        String city = "Paris";
        if (reponseVille.getName() != null) {
            city = reponseVille.getName();
        }
        Map<String, String> hospitalsList = Request.getHospitalsByCity(city);
        ArrayList<HospitalLD> hospitals = new ArrayList<>();
        /* Get list with 1 hospital */
        if (Long.valueOf(hospitalsList.get("size")) == 1) {
            SparqlHospitalRequestLDUniqueModel requestHospitalUnique = objectMapper
                    .readValue(hospitalsList.get("content"), SparqlHospitalRequestLDUniqueModel.class);
            HospitalLD uniqueHospital = fillHospitalUnique(requestHospitalUnique);
            hospitals.add(uniqueHospital);
        }
        /* Get list with more than 1 hospital */
        else {
            SparqlHospitalRequestLDModel testLD = objectMapper.readValue(hospitalsList.get("content"),
                    SparqlHospitalRequestLDModel.class);
            for (HospitalLD hos : testLD.getGraph()) {
                hospitals.add(hos);
            }
        }
        hospitals.sort(Comparator.comparing(HospitalLD::getName, String.CASE_INSENSITIVE_ORDER));
        model.addAttribute("hospitals", hospitals);
        /* Add hospitals coordinate */
        ArrayList<Coordinate> coordList = new ArrayList<>();
        for (HospitalLD hos : hospitals) {
            if (!hos.getLatitude().isEmpty() || !hos.getLongitude().isEmpty()) {
                coordList.add(
                        new Coordinate(Double.parseDouble(hos.getLatitude()), Double.parseDouble(hos.getLongitude())));
            }
        }
        model.addAttribute("coords", coordList);
        return "map";
    }

    /* Filling a hospital structure when there is only one hospital in a city */
    private HospitalLD fillHospitalUnique(SparqlHospitalRequestLDUniqueModel uniqueHospital) {
        HospitalLD hospital = new HospitalLD(uniqueHospital.getId(), uniqueHospital.getWebsite(),
                uniqueHospital.getAddress(), uniqueHospital.getBedCount(), uniqueHospital.getName(),
                uniqueHospital.getPicture(), uniqueHospital.getWikipedia(), uniqueHospital.getWikipediaArticle(),
                uniqueHospital.getLatitude(), uniqueHospital.getLongitude());
        return hospital;
    }

}
