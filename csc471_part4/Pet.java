package csc471_part4;

import com.opencsv.bean.CsvBindByName;

public class Pet {
    @CsvBindByName(column = "id")
    private int id;
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "breed")
    private String breed;
    @CsvBindByName(column = "pid")
    private int pid;

    public Pet() {
    }

    public Pet(int id, String name, String breed, int pid) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.pid = pid;
    }

    public Pet(String name, String breed, int pid) {
        this.name = name;
        this.breed = breed;
        this.pid = pid;
    }

    public Pet(String name, String breed) {
        this.name = name;
        this.breed = breed;
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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", pid=" + pid +
                '}';
    }
}
