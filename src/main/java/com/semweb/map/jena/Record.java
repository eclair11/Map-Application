package com.semweb.map.jena;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;

public class Record {
    private List<Hospital> hospitals = new ArrayList<Hospital>();

    public void read(String path) {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(path));
            String[] line;
            while ((line = reader.readNext()) != null) {
                Hospital hospital = new Hospital();
                hospital.setId(line[1]);
                hospital.setName(line[3]);
                hospital.setOperator(line[8]);
                hospital.setType(line[9]);
                hospital.setEmergency(line[10]);
                hospital.setCapacity(line[11]);
                if (line[17].length() > 0) {
                    hospital.setContact(line[17]);
                }
                else {
                    hospital.setContact(line[18]);
                }
                if (line[19].length() > 0) {
                    hospital.setPhone(line[19]);
                }
                else {
                    hospital.setPhone(line[20]);
                }
                if (line[21].length() > 0) {
                    hospital.setFax(line[21]);
                }
                else {
                    hospital.setFax(line[22]);
                }
                if (line[23].length() > 0) {
                    hospital.setEmail(line[23]);
                }
                else {
                    hospital.setEmail(line[24]);
                }
                hospital.setNumber(line[26]);
                hospital.setStreet(line[27]);
                hospital.setCity(line[28]);
                hospital.setZipcode(line[29]);
                hospital.setSpeciality(line[39]);
                String[] point = line[40].replaceAll("[^0-9\\s\\.]", "").trim().split("\\s");
                if (point.length == 2) {
                    hospital.setLatitude(point[0]);
                    hospital.setLongitude(point[1]);
                }
                else {
                    hospital.setLatitude("0");
                    hospital.setLongitude("0");
                }
                hospitals.add(hospital);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void model() {
        Model model = ModelFactory.createDefaultModel();
        Property prop_name = model.createProperty("name");
        Property prop_operator = model.createProperty("operator");
        Property prop_type = model.createProperty("type");
        Property prop_emergency = model.createProperty("emergency");
        Property prop_capacity = model.createProperty("capacity");
        Property prop_contact = model.createProperty("contact");
        Property prop_phone = model.createProperty("phone");
        Property prop_fax = model.createProperty("fax");
        Property prop_email = model.createProperty("email");
        Property prop_number = model.createProperty("number");
        Property prop_street = model.createProperty("street");
        Property prop_city = model.createProperty("city");
        Property prop_zipcode = model.createProperty("zipcode");
        Property prop_speciality = model.createProperty("speciality");
        Property prop_latitude = model.createProperty("latitude");
        Property prop_longitude = model.createProperty("longitude");
        for (Hospital hospital : hospitals) {
            Resource id = model.createResource(hospital.getId());
            model.add(id, prop_name, hospital.getName());
            model.add(id, prop_operator, hospital.getOperator());
            model.add(id, prop_type, hospital.getType());
            model.add(id, prop_emergency, hospital.getEmergency());
            model.add(id, prop_capacity, hospital.getCapacity());
            model.add(id, prop_contact, hospital.getContact());
            model.add(id, prop_phone, hospital.getPhone());
            model.add(id, prop_fax, hospital.getFax());
            model.add(id, prop_email, hospital.getEmail());
            model.add(id, prop_number, hospital.getNumber());
            model.add(id, prop_street, hospital.getStreet());
            model.add(id, prop_city, hospital.getCity());
            model.add(id, prop_zipcode, hospital.getZipcode());
            model.add(id, prop_speciality, hospital.getSpeciality());
            model.add(id, prop_latitude, hospital.getLatitude());
            model.add(id, prop_longitude, hospital.getLongitude());
        }
        try {
            FileWriter writer = new FileWriter("./output.ttl");
            model.write(writer, "TURTLE");
        } catch (IOException e) {
            System.err.println(e);
        }
        String datasetURL = "http://localhost:3030/test";
        String sparqlEndpoint = datasetURL + "/sparql";
        String sparqlUpdate = datasetURL + "/update";
        String graphStore = datasetURL + "/data";
        RDFConnection connect = RDFConnectionFactory.connect(sparqlEndpoint, sparqlUpdate, graphStore);
        connect.load(model);
    }
}
