package com.example.carsproject;

public class carModel {
    String Id, Carid, Carmodel, Carimageurl ;

    public carModel(String id, String carid, String carmodel, String carimageurl){
        Id = id;
        Carid = carid;
        Carmodel = carmodel;
        Carimageurl = carimageurl;
    }

    public String getId(){return Id;}
    public String getCarid(){return Carid;}
    public String getCarmodel(){return Carmodel;}
    public String getCarimageurl(){return Carimageurl;}


}
