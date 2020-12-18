package com.semweb.map.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SparqlHospitalRequestLDModel {

    /************/
    /** Graph **/
    /***********/

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("@graph")
    private HospitalLD[] graph;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("@context")
    private Context context;

    public SparqlHospitalRequestLDModel() {

    }

    public SparqlHospitalRequestLDModel(HospitalLD[] graph) {
        this.graph = graph;
    }

    public HospitalLD[] getGraph() {
        return graph;
    }

    public void setGraph(HospitalLD[] graph) {
        this.graph = graph;
    }

    @Override
    public String toString() {
        return "SparqlHospitalRequestLDModel [graph=" + Arrays.toString(graph) + "]";
    }

    /**************/
    /** Hospital **/
    /**************/

    public static class HospitalLD {

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("@id")
        private String id;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("Website")
        private String website;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("address")
        private String address;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("bedCount")
        private String bedCount;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("name")
        private String name;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("picture")
        private String picture;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("wikipedia")
        private String wikipedia;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("wikipediaArticle")
        private String wikipediaArticle;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("latitude")
        private String latitude;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("longitude")
        private String longitude;

        public HospitalLD() {

        }

        public HospitalLD(String id, String website, String address, String bedCount, String name, String picture,
                String wikipedia, String wikipediaArticle, String latitude, String longitude) {
            this.id = id;
            this.website = website;
            this.address = address;
            this.bedCount = bedCount;
            this.name = name;
            this.picture = picture;
            this.wikipedia = wikipedia;
            this.wikipediaArticle = wikipediaArticle;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBedCount() {
            return bedCount;
        }

        public void setBedCount(String bedCount) {
            this.bedCount = bedCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getWikipedia() {
            return wikipedia;
        }

        public void setWikipedia(String wikipedia) {
            this.wikipedia = wikipedia;
        }

        public String getWikipediaArticle() {
            return wikipediaArticle;
        }

        public void setWikipediaArticle(String wikipediaArticle) {
            this.wikipediaArticle = wikipediaArticle;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "HospitalLD [address=" + address + ", bedCount=" + bedCount + ", id=" + id + ", latitude=" + latitude
                    + ", longitude=" + longitude + ", name=" + name + ", picture=" + picture + ", website=" + website
                    + ", wikipedia=" + wikipedia + ", wikipediaArticle=" + wikipediaArticle + "]";
        }

    }

    /*************/
    /** Context **/
    /*************/

    public static class Context {

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("longitude")
        private Longitude longitude;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("latitude")
        private Latitude latitude;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("wikipediaArticle")
        private Wikipedia wikipediaArticle;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("wikipedia")
        private Wikipedia wikipedia;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("picture")
        private Picture picture;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("name")
        private Name name;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("bedCount")
        private BedCount bedCount;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("address")
        private Address address;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("Website")
        private Website website;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("mo")
        private String mo;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("rdf")
        private String rdf;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("ns")
        private String ns;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("gn")
        private String gn;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("wd")
        private String wd;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("db")
        private String db;

        public Context() {
        }

        public Context(Longitude longitude, Latitude latitude, Wikipedia wikipediaArticle, Wikipedia wikipedia,
                Picture picture, Name name, BedCount bedCount, Address address, Website website, String mo, String rdf,
                String ns, String gn, String wd, String db) {
            this.longitude = longitude;
            this.latitude = latitude;
            this.wikipediaArticle = wikipediaArticle;
            this.wikipedia = wikipedia;
            this.picture = picture;
            this.name = name;
            this.bedCount = bedCount;
            this.address = address;
            this.website = website;
            this.mo = mo;
            this.rdf = rdf;
            this.ns = ns;
            this.gn = gn;
            this.wd = wd;
            this.db = db;
        }

        public Longitude getLongitude() {
            return longitude;
        }

        public void setLongitude(Longitude longitude) {
            this.longitude = longitude;
        }

        public Latitude getLatitude() {
            return latitude;
        }

        public void setLatitude(Latitude latitude) {
            this.latitude = latitude;
        }

        public Wikipedia getWikipediaArticle() {
            return wikipediaArticle;
        }

        public void setWikipediaArticle(Wikipedia wikipediaArticle) {
            this.wikipediaArticle = wikipediaArticle;
        }

        public Wikipedia getWikipedia() {
            return wikipedia;
        }

        public void setWikipedia(Wikipedia wikipedia) {
            this.wikipedia = wikipedia;
        }

        public Picture getPicture() {
            return picture;
        }

        public void setPicture(Picture picture) {
            this.picture = picture;
        }

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public BedCount getBedCount() {
            return bedCount;
        }

        public void setBedCount(BedCount bedCount) {
            this.bedCount = bedCount;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Website getWebsite() {
            return website;
        }

        public void setWebsite(Website website) {
            this.website = website;
        }

        public String getMo() {
            return mo;
        }

        public void setMo(String mo) {
            this.mo = mo;
        }

        public String getRdf() {
            return rdf;
        }

        public void setRdf(String rdf) {
            this.rdf = rdf;
        }

        public String getNs() {
            return ns;
        }

        public void setNs(String ns) {
            this.ns = ns;
        }

        public String getGn() {
            return gn;
        }

        public void setGn(String gn) {
            this.gn = gn;
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
            return "Context [address=" + address + ", bedCount=" + bedCount + ", db=" + db + ", gn=" + gn
                    + ", latitude=" + latitude + ", longitude=" + longitude + ", mo=" + mo + ", name=" + name + ", ns="
                    + ns + ", picture=" + picture + ", rdf=" + rdf + ", wd=" + wd + ", website=" + website
                    + ", wikipedia=" + wikipedia + ", wikipediaArticle=" + wikipediaArticle + "]";
        }

        /***********************/
        /** Context/Longitude **/
        /***********************/

        public static class Longitude {

            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonProperty("@id")
            private String id;

            public Longitude() {
            }

            public Longitude(String id) {
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
                return "Longitude [id=" + id + "]";
            }

        }

        /***********************/
        /** Context/Latitude **/
        /***********************/

        public static class Latitude {

            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonProperty("@id")
            private String id;

            public Latitude() {
            }

            public Latitude(String id) {
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
                return "Latitude [id=" + id + "]";
            }

        }

        /******************************/
        /** Context/WikipediaArticle **/
        /******************************/

        public static class WikipediaArticle {

            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonProperty("@id")
            private String id;

            public WikipediaArticle() {
            }

            public WikipediaArticle(String id) {
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
                return "WikipediaArticle [id=" + id + "]";
            }

        }

        /***********************/
        /** Context/Wikipedia **/
        /***********************/

        public static class Wikipedia {

            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonProperty("@id")
            private String id;

            public Wikipedia() {
            }

            public Wikipedia(String id) {
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
                return "Wikipedia [id=" + id + "]";
            }

        }

        /*********************/
        /** Context/Picture **/
        /*********************/

        public static class Picture {

            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonProperty("@id")
            private String id;

            public Picture() {
            }

            public Picture(String id) {
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
                return "Picture [id=" + id + "]";
            }

        }

        /******************/
        /** Context/Name **/
        /******************/

        public static class Name {

            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonProperty("@id")
            private String id;

            public Name() {
            }

            public Name(String id) {
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
                return "Name [id=" + id + "]";
            }

        }

        /**********************/
        /** Context/BedCount **/
        /**********************/

        public static class BedCount {

            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonProperty("@id")
            private String id;

            public BedCount() {
            }

            public BedCount(String id) {
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
                return "BedCount [id=" + id + "]";
            }

        }

        /*********************/
        /** Context/Address **/
        /*********************/

        public static class Address {

            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonProperty("@id")
            private String id;

            public Address() {
            }

            public Address(String id) {
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
                return "Address [id=" + id + "]";
            }

        }

        /*********************/
        /** Context/Website **/
        /*********************/

        public static class Website {

            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonProperty("@id")
            private String id;

            public Website() {
            }

            public Website(String id) {
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
                return "Website [id=" + id + "]";
            }

        }

    }

}