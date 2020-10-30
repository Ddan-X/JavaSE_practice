package csc471_part4;


import com.github.javafaker.Faker;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import csc471_part4.util.WriteToFileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataSet {
    public static void main(String[] args) {

        List<People> peopleList = new ArrayList<>();

        for(int i =1;i<=10000;i++){
            People p = people();
            People pe = new People(i,p.getName(),p.getStreetAddress(),p.getPet());
            peopleList.add(pe);
        }
        /*for (People p:peopleList) {
            System.out.println(p);
        }*/

        List<Pet> petList = new ArrayList<>();

        for(int i=1;i<=peopleList.size();i++){
            People p = peopleList.get(i-1);
            String pet = p.getPet();
            if("cat".equals(pet)){
                Pet cat = cat();
                Pet p_cat = new Pet(i,cat.getName(),cat.getBreed(),p.getId());
               // System.out.println("cat: "+p_cat.getId()+","+p_cat.getName()+","+p.getName());
                petList.add(p_cat);
            }else {
                Pet dog = dog();
                Pet p_dog = new Pet(i,dog.getName(),dog.getBreed(),p.getId());
                petList.add(p_dog);
            }
        }
        String[] columnMapping = {"id","name","streetAddress","pet"};
        ColumnPositionMappingStrategy<People> mapper1 = new ColumnPositionMappingStrategy<>();
        mapper1.setType(People.class);
        mapper1.setColumnMapping(columnMapping);

        WriteToFileUtil  writeToFileUtil = new WriteToFileUtil();

        writeToFileUtil.wirteCSV(peopleList,mapper1,columnMapping,"./src/csc471_part4/people.csv");

        String[] columnMapping2 = {"id","name","breed","pid"};
        ColumnPositionMappingStrategy<Pet> mapper2 = new ColumnPositionMappingStrategy<>();
        mapper2.setType(Pet.class);
        mapper2.setColumnMapping(columnMapping2);
        writeToFileUtil.wirteCSV(petList,mapper2,columnMapping2,"./src/csc471_part4/pets.csv");

    }



    public static People people(){
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String streetAddress = faker.address().streetAddress();
        Random random = new Random();
        String[] pets = new String[]{"cat","dog"};
        String pet = pets[random.nextInt(pets.length)];
        People people = new People(name, streetAddress, pet);
       return people;
    }

    public static Pet cat(){
        Faker faker = new Faker();
        String cat_name = faker.cat().name();
        String cat_breed = faker.cat().breed().replaceAll(",", "");;

        Pet cat = new Pet(cat_name,cat_breed);
        return cat;
    }

    public static Pet dog(){
        Faker faker = new Faker();
        String dog_name = faker.dog().name();
        String dog_breed = faker.dog().breed().replaceAll(",", "");;
        Pet dog = new Pet(dog_name,dog_breed);
        return dog;
    }
}
