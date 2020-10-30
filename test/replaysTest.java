package test;

import com.github.javafaker.Faker;
import csc471_part4.Pet;

public class replaysTest {
    public static void main(String[] args) {
        String a ="Russian White, Black and Tabby";
        String s = a.replaceAll(",", "");
        System.out.println(s);
        Faker faker = new Faker();
        for(int i=0;i<10;i++) {

            String cat_name = faker.cat().name();
            String cat_breed = faker.cat().breed();
            System.out.println(cat_breed);
            String dog_name = faker.dog().name();
            String dog_breed = faker.dog().breed();
            System.out.println(dog_breed);
        }


    }
}
