package com.example.carsproject;


public class Car {
    String Id, Name, Imageurl, Description ;

    public Car(String id, String name, String imageurl, String description){
        Id = id;
        Name = name;
        Imageurl = imageurl;
        Description = description;
    }

    public String getId(){return Id;}
    public String getName(){return Name;}
    public String getImageurl(){return Imageurl;}
    public String getDescription(){return Description;}
}
