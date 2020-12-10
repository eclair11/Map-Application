package com.semweb.map.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SparqlTownRequestModel {

    /****************************/
    /** SparqlTownRequestModel **/
    /****************************/

    @JsonProperty("@id")
    private String type;

    @JsonProperty("city")
    private String[] city;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("@context")
    private Context context;

    public SparqlTownRequestModel() {

    }

    public SparqlTownRequestModel(String type, String[] city, Context context) {
        this.type = type;
        this.city = city;
        this.context = context;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getCity() {
        return city;
    }

    public void setCity(String[] city) {
        this.city = city;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "SparqlTownRequestModel [city=" + Arrays.toString(city) + ", context=" + context + ", type=" + type
                + "]";
    }


    /*************/
    /** Context **/
    /*************/

    public static class Context {
        @JsonProperty("city")
        private City city;

        @JsonProperty("wd")
        private String wd;

        @JsonProperty("db")
        private String db;

        public Context() {

        }

        public Context(City city, String wd, String db) {
            this.city = city;
            this.wd = wd;
            this.db = db;
        }

        public City getCity() {
            return city;
        }

        public void setCity(City city) {
            this.city = city;
        }

        public String getWd() {
            return wd;
        }

        public void setWd(String wd) {
            this.wd = wd;
        }

        public String getDb() {
            return db;
        }

        public void setDb(String db) {
            this.db = db;
        }

        @Override
        public String toString() {
            return "Context [city=" + city + ", db=" + db + ", wd=" + wd + "]";
        }

    }


    /**********/
    /** City **/
    /**********/

    public static class City {
        @JsonProperty("@id")
        private String id;

        public City() {

        }

        public City(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "City [id=" + id + "]";
        }

    }

    

}
