package csc471_part4;

import com.opencsv.bean.CsvBindByName;

public class People {
    @CsvBindByName(column = "id")
    private int id;
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "streetAddress")
    private String streetAddress;
    @CsvBindByName(column = "pet")
    private String pet;

    public People(String name, String streetAddress, String pet) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.pet = pet;
    }

    public People(int id, String name, String streetAddress, String pet) {
        this.id = id;
        this.name = name;
        this.streetAddress = streetAddress;
        this.pet = pet;
    }

    public People() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", pet='" + pet + '\'' +
                '}';
    }
}
