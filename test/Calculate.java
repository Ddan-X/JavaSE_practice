package test;

public class Calculate {
    public static void main(String[] args) {
        int x = 7;
        int y= 4;
        x %= y+1;
        System.out.println(x);
        int z= 4;
        int c = --z*5;
        System.out.println(c);
        int i = 5;
        i *= i-1;
        System.out.println(i);
    }
}
