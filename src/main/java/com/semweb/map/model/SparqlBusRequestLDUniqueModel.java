package com.semweb.map.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SparqlBusRequestLDUniqueModel {

    /***********************************/
    /** SparqlBusRequestLDUniqueModel **/
    /***********************************/

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("@id")
    private String id;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("description")
    private Description description;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("wikibase:geoLatitude")
    private Double latitude;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("wikibase:geoLongitude")
    private Double longitude;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("label")
    private Label label;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("@context")
    private Context context;

    public SparqlBusRequestLDUniqueModel() {
    }

    public SparqlBusRequestLDUniqueModel(String id, Description description, Double latitude, Double longitude,
            Label label, Context context) {
        this.id = id;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.label = label;
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "SparqlBusRequestLDUniqueModel [context=" + context + ", description=" + description + ", id=" + id
                + ", label=" + label + ", latitude=" + latitude + ", longitude=" + longitude + "]";
    }

    /*****************/
    /** Description **/
    /*****************/

    public static class Description {

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("@language")
        private String language;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("@value")
        private String value;

        public Description() {
        }

        public Description(String language, String value) {
            this.language = language;
            this.value = value;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Description [language=" + language + ", value=" + value + "]";
        }

    }

    /***********/
    /** Label **/
    /***********/

    public static class Label {

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("@language")
        private String language;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("@value")
        private String value;

        public Label() {
        }

        public Label(String language, String value) {
            this.language = language;
            this.value = value;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Label [language=" + language + ", value=" + value + "]";
        }

    }

    /*************/
    /** Context **/
    /*************/

    public static class Context {

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("geoLatitude")
        private GeoLatitude geoLatitude;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("geoLongitude")
        private GeoLongitude geoLongitude;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("description")
        private Description description;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("label")
        private Label label;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("schema")
        private String schema;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("pq")
        private String pq;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("bd")
        private String bd;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("pr")
        private String pr;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("ps")
        private String ps;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("geof")
        private String geof;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("owl")
        private String owl;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("wdt")
        private String wdt;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("mwapi")
        private String mwapi;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("wds")
        private String wds;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("xsd")
        private String xsd;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("fn")
        private String fn;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("wdv")
        private String wdv;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("skos")
        private String skos;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("rdfs")
        private String rdfs;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("psn")
        private String psn;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("pqn")
        private String pqn;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("wd")
        private String wd;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("geo")
        private String geo;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("psv")
        private String psv;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("pqv")
        private String pqv;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("dct")
        private String dct;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("gas")
        private String gas;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("wdata")
        private String wdata;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("wdref")
        private String wdref;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("ontolex")
        private String ontolex;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("prov")
        private String prov;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("foaf")
        private String foaf;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("wikibase")
        private String wikibase;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("prn")
        private String prn;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("p")
        private String p;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("bds")
        private String bds;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("wdtn")
        private String wdtn;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("mediawiki")
        private String mediawiki;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("rdf")
        private String rdf;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("prv")
        private String prv;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("hint")
        private String hint;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("wdno")
        private String wdno;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("sesame")
        private String sesame;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("dc")
        private String dc;

        public Context() {
        }

        public Context(GeoLatitude geoLatitude, GeoLongitude geoLongitude, Description description, Label label,
                String schema, String pq, String bd, String pr, String ps, String geof, String owl, String wdt,
                String mwapi, String wds, String xsd, String fn, String wdv, String skos, String rdfs, String psn,
                String pqn, String wd, String geo, String psv, String pqv, String dct, String gas, String wdata,
                String wdref, String ontolex, String prov, String foaf, String wikibase, String prn, String p,
                String bds, String wdtn, String mediawiki, String rdf, String prv, String hint, String wdno,
                String sesame, String dc) {
            this.geoLatitude = geoLatitude;
            this.geoLongitude = geoLongitude;
            this.description = description;
            this.label = label;
            this.schema = schema;
            this.pq = pq;
            this.bd = bd;
            this.pr = pr;
            this.ps = ps;
            this.geof = geof;
            this.owl = owl;
            this.wdt = wdt;
            this.mwapi = mwapi;
            this.wds = wds;
            this.xsd = xsd;
            this.fn = fn;
            this.wdv = wdv;
            this.skos = skos;
            this.rdfs = rdfs;
            this.psn = psn;
            this.pqn = pqn;
            this.wd = wd;
            this.geo = geo;
            this.psv = psv;
            this.pqv = pqv;
            this.dct = dct;
            this.gas = gas;
            this.wdata = wdata;
            this.wdref = wdref;
            this.ontolex = ontolex;
            this.prov = prov;
            this.foaf = foaf;
            this.wikibase = wikibase;
            this.prn = prn;
            this.p = p;
            this.bds = bds;
            this.wdtn = wdtn;
            this.mediawiki = mediawiki;
            this.rdf = rdf;
            this.prv = prv;
            this.hint = hint;
            this.wdno = wdno;
            this.sesame = sesame;
            this.dc = dc;
        }

        public GeoLatitude getGeoLatitude() {
            return geoLatitude;
        }

        public void setGeoLatitude(GeoLatitude geoLatitude) {
            this.geoLatitude = geoLatitude;
        }

        public GeoLongitude getGeoLongitude() {
            return geoLongitude;
        }

        public void setGeoLongitude(GeoLongitude geoLongitude) {
            this.geoLongitude = geoLongitude;
        }

        public Description getDescription() {
            return description;
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Label getLabel() {
            return label;
        }

        public void setLabel(Label label) {
            this.label = label;
        }

        public String getSchema() {
            return schema;
        }

        public void setSchema(String schema) {
            this.schema = schema;
        }

        public String getPq() {
            return pq;
        }

        public void setPq(String pq) {
            this.pq = pq;
        }

        public String getBd() {
            return bd;
        }

        public void setBd(String bd) {
            this.bd = bd;
        }

        public String getPr() {
            return pr;
        }

        public void setPr(String pr) {
            this.pr = pr;
        }

        public String getPs() {
            return ps;
        }

        public void setPs(String ps) {
            this.ps = ps;
        }

        public String getGeof() {
            return geof;
        }

        public void setGeof(String geof) {
            this.geof = geof;
        }

        public String getOwl() {
            return owl;
        }

        public void setOwl(String owl) {
            this.owl = owl;
        }

        public String getWdt() {
            return wdt;
        }

        public void setWdt(String wdt) {
            this.wdt = wdt;
        }

        public String getMwapi() {
            return mwapi;
        }

        public void setMwapi(String mwapi) {
            this.mwapi = mwapi;
        }

        public String getWds() {
            return wds;
        }

        public void setWds(String wds) {
            this.wds = wds;
        }

        public String getXsd() {
            return xsd;
        }

        public void setXsd(String xsd) {
            this.xsd = xsd;
        }

        public String getFn() {
            return fn;
        }

        public void setFn(String fn) {
            this.fn = fn;
        }

        public String getWdv() {
            return wdv;
        }

        public void setWdv(String wdv) {
            this.wdv = wdv;
        }

        public String getSkos() {
            return skos;
        }

        public void setSkos(String skos) {
            this.skos = skos;
        }

        public String getRdfs() {
            return rdfs;
        }

        public void setRdfs(String rdfs) {
            this.rdfs = rdfs;
        }

        public String getPsn() {
            return psn;
        }

        public void setPsn(String psn) {
            this.psn = psn;
        }

        public String getPqn() {
            return pqn;
        }

        public void setPqn(String pqn) {
            this.pqn = pqn;
        }

        public String getWd() {
            return wd;
        }

        public void setWd(String wd) {
            this.wd = wd;
        }

        public String getGeo() {
            return geo;
        }

        public void setGeo(String geo) {
            this.geo = geo;
        }

        public String getPsv() {
            return psv;
        }

        public void setPsv(String psv) {
            this.psv = psv;
        }

        public String getPqv() {
            return pqv;
        }

        public void setPqv(String pqv) {
            this.pqv = pqv;
        }

        public String getDct() {
            return dct;
        }

        public void setDct(String dct) {
            this.dct = dct;
        }

        public String getGas() {
            return gas;
        }

        public void setGas(String gas) {
            this.gas = gas;
        }

        public String getWdata() {
            return wdata;
        }

        public void setWdata(String wdata) {
            this.wdata = wdata;
        }

        public String getWdref() {
            return wdref;
        }

        public void setWdref(String wdref) {
            this.wdref = wdref;
        }

        public String getOntolex() {
            return ontolex;
        }

        public void setOntolex(String ontolex) {
            this.ontolex = ontolex;
        }

        public String getProv() {
            return prov;
        }

        public void setProv(String prov) {
            this.prov = prov;
        }

        public String getFoaf() {
            return foaf;
        }

        public void setFoaf(String foaf) {
            this.foaf = foaf;
        }

        public String getWikibase() {
            return wikibase;
        }

        public void setWikibase(String wikibase) {
            this.wikibase = wikibase;
        }

        public String getPrn() {
            return prn;
        }

        public void setPrn(String prn) {
            this.prn = prn;
        }

        public String getP() {
            return p;
        }

        public void setP(String p) {
            this.p = p;
        }

        public String getBds() {
            return bds;
        }

        public void setBds(String bds) {
            this.bds = bds;
        }

        public String getWdtn() {
            return wdtn;
        }

        public void setWdtn(String wdtn) {
            this.wdtn = wdtn;
        }

        public String getMediawiki() {
            return mediawiki;
        }

        public void setMediawiki(String mediawiki) {
            this.mediawiki = mediawiki;
        }

        public String getRdf() {
            return rdf;
        }

        public void setRdf(String rdf) {
            this.rdf = rdf;
        }

        public String getPrv() {
            return prv;
        }

        public void setPrv(String prv) {
            this.prv = prv;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getWdno() {
            return wdno;
        }

        public void setWdno(String wdno) {
            this.wdno = wdno;
        }

        public String getSesame() {
            return sesame;
        }

        public void setSesame(String sesame) {
            this.sesame = sesame;
        }

        public String getDc() {
            return dc;
        }

        public void setDc(String dc) {
            this.dc = dc;
        }

        @Override
        public String toString() {
            return "Context [bd=" + bd + ", bds=" + bds + ", dc=" + dc + ", dct=" + dct + ", description=" + description
                    + ", fn=" + fn + ", foaf=" + foaf + ", gas=" + gas + ", geo=" + geo + ", geoLatitude=" + geoLatitude
                    + ", geoLongitude=" + geoLongitude + ", geof=" + geof + ", hint=" + hint + ", label=" + label
                    + ", mediawiki=" + mediawiki + ", mwapi=" + mwapi + ", ontolex=" + ontolex + ", owl=" + owl + ", p="
                    + p + ", pq=" + pq + ", pqn=" + pqn + ", pqv=" + pqv + ", pr=" + pr + ", prn=" + prn + ", prov="
                    + prov + ", prv=" + prv + ", ps=" + ps + ", psn=" + psn + ", psv=" + psv + ", rdf=" + rdf
                    + ", rdfs=" + rdfs + ", schema=" + schema + ", sesame=" + sesame + ", skos=" + skos + ", wd=" + wd
                    + ", wdata=" + wdata + ", wdno=" + wdno + ", wdref=" + wdref + ", wds=" + wds + ", wdt=" + wdt
                    + ", wdtn=" + wdtn + ", wdv=" + wdv + ", wikibase=" + wikibase + ", xsd=" + xsd + "]";
        }

        /*****************/
        /** GeoLatitude **/
        /*****************/

        public static class GeoLatitude {

            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonProperty("@id")
            private String id;

            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonProperty("@type")
            private String type;

            public GeoLatitude() {
            }

            public GeoLatitude(String id, String type) {
                this.id = id;
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            @Override
            public String toString() {
                return "GeoLatitude [id=" + id + ", type=" + type + "]";
            }

        }

        /******************/
        /** GeoLongitude **/
        /******************/

        public static class GeoLongitude {

            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonProperty("@id")
            private String id;

            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonProperty("@type")
            private String type;

            public GeoLongitude() {
            }

            public GeoLongitude(String id, String type) {
                this.id = id;
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            @Override
            public String toString() {
                return "GeoLongitude [id=" + id + ", type=" + type + "]";
            }

        }

        /******************/
        /** Description **/
        /*****************/

        public static class Description {

            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonProperty("@id")
            private String id;

            public Description() {
            }

            public Description(String id) {
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
                return "Description [id=" + id + "]";
            }

        }

        /***********/
        /** Label **/
        /***********/

        public static class Label {

            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonProperty("@id")
            private String id;

            public Label() {
            }

            public Label(String id) {
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
                return "Label [id=" + id + "]";
            }

        }

    }

}