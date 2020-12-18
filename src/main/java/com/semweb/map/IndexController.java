package com.semweb.map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semweb.map.jena.Record;
import com.semweb.map.jena.Request;
import com.semweb.map.model.Bus;
import com.semweb.map.model.ChoosenCoord;
import com.semweb.map.model.Coordinate;
import com.semweb.map.model.Reponse;
import com.semweb.map.model.ReponseVille;
import com.semweb.map.model.SparqlBusRequestLDModel;
import com.semweb.map.model.SparqlBusRequestLDUniqueModel;
import com.semweb.map.model.SparqlHospitalRequestLDModel;
import com.semweb.map.model.SparqlHospitalRequestLDModel.HospitalLD;
import com.semweb.map.model.SparqlHospitalRequestLDUniqueModel;
import com.semweb.map.model.SparqlTownRequestModel;
import com.semweb.map.model.SparqlBusRequestLDModel.BusLD;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    /* Gloabl objects used by several Rest Resources */
    ObjectMapper objectMapper = new ObjectMapper();
    Reponse reponse = new Reponse();
    ReponseVille ReponseVille = new ReponseVille("", "no");

    String choosenLat = "noValue";
    String choosenLon = "noValue";
    Double baseLat = 45.448007;
    Double baseLon = 4.38609;
    Double currentLat = 0.0;
    Double currentLon = 0.0;
    int currentZoom = 5;

    /*****************/
    /* Rest Resource */
    /*****************/

    /* Index Rest Resource */
    @RequestMapping("/")
    public String index(Model model, Reponse reponse) {
        model.addAttribute("reponse", reponse);
        Record.load();
        return "redirect:/map";
    }

    /* Data addition on Jena Fuseki Rest Resource */
    @RequestMapping("/add")
    public String add(Model model, Reponse reponse) {
        if (reponse.getLabel().equals("true")) {
            Record.load();
        }
        return "redirect:/map";
    }

    /* Hospital Map Rest Resource */
    @RequestMapping("/map")
    public String map(Model model, ReponseVille reponseVille)
            throws JsonParseException, JsonMappingException, IOException {

        model = buildCitiesWithHospitalModel(model, reponseVille);

        ArrayList<HospitalLD> hospitals = new ArrayList<>();
        model = buildHospitalsByCityModel(model, reponseVille, hospitals);

        model = buildHospitalsCoordinatesModel(model, hospitals);

        return "map";

    }

    /* Hospital Map Rest Resource */
    @RequestMapping("/bus")
    public String bus(Model model, ReponseVille reponseVille)
            throws JsonParseException, JsonMappingException, IOException {

        Double currentCoordLat = 0.0;
        Double currentCoordLon = 0.0;

        /*
         * Double currentCoordLat = 0.0; Double currentCoordLon = 0.0;
         * 
         * if(!choosenLat.equals("noValue") || !choosenLon.equals("noValue")){
         * currentCoordLat = Double.parseDouble(choosenLat); currentCoordLon =
         * Double.parseDouble(choosenLon); currentZoom = 12; } else{ currentCoordLat =
         * baseLat; currentCoordLon = baseLon; currentZoom = 5; }
         * 
         * 
         * currentCoordLat = baseLat; currentCoordLon = baseLon; currentZoom = 5;
         * model.addAttribute("currentCoordLat", currentCoordLat);
         * model.addAttribute("currentCoordLon", currentCoordLon);
         * 
         * model.addAttribute("currentZoom", currentZoom);
         */

        currentCoordLat = baseLat;
        currentCoordLon = baseLon;
        currentZoom = 5;
        model.addAttribute("currentCoordLat", currentCoordLat);
        model.addAttribute("currentCoordLon", currentCoordLon);

        model.addAttribute("currentZoom", currentZoom);

        model = buildCitiesWithHospitalModel(model, reponseVille);

        ArrayList<HospitalLD> hospitals = new ArrayList<>();
        model = buildHospitalsByCityModel(model, reponseVille, hospitals);

        model = buildHospitalsCoordinatesModel(model, hospitals);

        // CA PLANTE ICI
        model = buildNearbyStopsModel(model, reponseVille);

        /*
        ObjectMapper objectMapper2 = new ObjectMapper();
        SparqlBusRequestLDUniqueModel sparqlBusRequestLDModel = objectMapper2.readValue(new File("outPutUnique.txt"), SparqlBusRequestLDUniqueModel.class);
        */
        
        /* 
        ObjectMapper objectMapper3 = new ObjectMapper();
        SparqlBusRequestLDModel sparqlBusRequestLDModel = objectMapper3.readValue(new File("outPutUnique.txt"), SparqlBusRequestLDModel.class); 
        */
        
        //System.err.println(sparqlBusRequestLDModel);

        return "bus";

    }

    /* Data addition on Jena Fuseki Rest Resource */

    @RequestMapping(value = "/getTest", method = RequestMethod.POST)
    public ResponseEntity<String> getTest(Model model, @RequestBody ChoosenCoord choosenCoord) {

        choosenLat = choosenCoord.getLat();
        choosenLon = choosenCoord.getLon();

        System.err.println(choosenCoord);
        return ResponseEntity.ok(choosenLat);
    }

    /******************/
    /* Model Building */
    /******************/

    /**
     * get the cities with hospital from Jena Fuseki and fill the model to feed the
     * View
     */
    private Model buildCitiesWithHospitalModel(Model model, ReponseVille reponseVille)
            throws JsonMappingException, JsonProcessingException {

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

        return model;

    }

    /**
     * Retrieves the list of hospitals in the city chosen by the user, sorts it,
     * fills in the template and inserts it in the view.
     */
    private Model buildHospitalsByCityModel(Model model, ReponseVille reponseVille, ArrayList<HospitalLD> hospitals)
            throws JsonMappingException, JsonProcessingException {

        /* Get hospitals by city */
        String city = "Paris";

        if (reponseVille.getName() != null) {
            city = reponseVille.getName();
        }

        Map<String, String> hospitalsList = Request.getHospitalsByCity(city);

        /* Get list with 1 hospital */
        if (Long.valueOf(hospitalsList.get("size")) == 1) {
            SparqlHospitalRequestLDUniqueModel requestHospitalUnique = objectMapper
                    .readValue(hospitalsList.get("content"), SparqlHospitalRequestLDUniqueModel.class);
            HospitalLD uniqueHospital = fillHospitalUnique(requestHospitalUnique);
            hospitals.add(uniqueHospital);
        }
        /* Get list with more than 1 hospital */
        else {
            SparqlHospitalRequestLDModel requestHospitalMultiple = objectMapper.readValue(hospitalsList.get("content"),
                    SparqlHospitalRequestLDModel.class);
            for (HospitalLD hos : requestHospitalMultiple.getGraph()) {
                hospitals.add(hos);
            }
        }

        hospitals.sort(Comparator.comparing(HospitalLD::getName, String.CASE_INSENSITIVE_ORDER));
        model.addAttribute("hospitals", hospitals);

        return model;

    }

    /**
     * Retrieves the list of stops near the hospital chosen by the user, sorts it,
     * fills in the template and inserts it in the view.
     */
    private Model buildNearbyStopsModel(Model model, ReponseVille reponseVille)
            throws JsonMappingException, JsonProcessingException {

        Map<String, String> nearbyList = Request.getNearbyStations(0.0, 0.0);

        System.err.println("$$$currentLat = " + currentLat);
        System.err.println("$$$currentLon = " + currentLon);

        System.err.println("Vérif de la map");
        nearbyList.forEach((key, value)-> System.err.println("Key: " + key + " - Value : " + value));

        ArrayList<Bus> busList = new ArrayList<>();

        /* Get list with 1 stop */
        if (Long.valueOf(nearbyList.get("size")) == 1) {
            SparqlBusRequestLDUniqueModel requestBusUnique = objectMapper.readValue(nearbyList.get("content"), SparqlBusRequestLDUniqueModel.class);
                    Bus bustmp = fillBusUnique(requestBusUnique);
                    busList.add(bustmp);
        }
        /* Get list with more than 1 stop */
        else {
            SparqlBusRequestLDModel requestBusMultiple = objectMapper.readValue(nearbyList.get("content"), SparqlBusRequestLDModel.class);

                System.err.println(requestBusMultiple);


            for (BusLD bus : requestBusMultiple.getGraph()) {
                Bus bustmp = fillBusMulti(bus);
                //stops.add(bus);
                busList.add(bustmp);
            }
        }

        model.addAttribute("busList", busList);

        return model;

    }

    /**
     * removes from the list of hospitals all those who do not have contact
     * information and fills in the template with the contact information
     */
    private Model buildHospitalsCoordinatesModel(Model model, ArrayList<HospitalLD> hospitals) {

        /* Add hospitals coordinate */
        ArrayList<Coordinate> coordList = new ArrayList<>();
        for (HospitalLD hos : hospitals) {
            if (!hos.getLatitude().isEmpty() || !hos.getLongitude().isEmpty()) {
                coordList.add(
                        new Coordinate(Double.parseDouble(hos.getLatitude()), Double.parseDouble(hos.getLongitude())));
            }
        }

        model.addAttribute("coords", coordList);

        return model;
    }

    /*****************/
    /* Utils Methods */
    /*****************/

    /**
     * Filling a hospital structure when there is only one hospital in a city called
     * by {@link #buildHospitalsByCityModel()} in the model building methods
     */
    private HospitalLD fillHospitalUnique(SparqlHospitalRequestLDUniqueModel uniqueHospital) {
        HospitalLD hospital = new HospitalLD(uniqueHospital.getId(), uniqueHospital.getWebsite(),
                uniqueHospital.getAddress(), uniqueHospital.getBedCount(), uniqueHospital.getName(),
                uniqueHospital.getPicture(), uniqueHospital.getWikipedia(), uniqueHospital.getWikipediaArticle(),
                uniqueHospital.getLatitude(), uniqueHospital.getLongitude());
        return hospital;
    }


    /**
     * Filling a bus structure when there is only one hospital in a city called
     * by {@link #buildNearbyStopsModel()} in the model building methods
     */
    private Bus fillBusUnique(SparqlBusRequestLDUniqueModel uniqueBus) {
        Bus bus = new Bus(uniqueBus.getId(), uniqueBus.getDescription().getValue(), uniqueBus.getLabel().getValue(), uniqueBus.getLatitude(), uniqueBus.getLongitude());
        return bus;
    }

    /**
     * Filling a bus structure when there is only one hospital in a city called
     * by {@link #buildNearbyStopsModel()} in the model building methods
     */
    private Bus fillBusMulti(BusLD multiBus) {
        Bus bus = new Bus(multiBus.getId(), multiBus.getDescription().getValue(), multiBus.getLabel().getValue(), multiBus.getLatitude(), multiBus.getLongitude());
        return bus;
    }

}
