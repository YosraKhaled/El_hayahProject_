package com.yosra.el_hayahproject.pojo;

public class HomeModel {
    String name ;
    String img;
    String id ;
    String specialty ;

    public HomeModel(String name, String img, String id, String specialty) {
        this.name = name;
        this.img = img;
        this.id = id;
        this.specialty = specialty;
    }

    public HomeModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
