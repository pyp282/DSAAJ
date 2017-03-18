package Chap01.question.c05;

//Chap01.question.c05.DigitCount.java
public class DigitCount {
    public static void main(String... args) {
        System.out.print(new DigitCount().digitCount(-2));
    }

    public int digitCount(int n) {
        if (n == 0) return 0;
        if (n % 2 != 0) return digitCount(n >>> 1) + 1;
        else return digitCount(n >>> 1);
    }
}//well there is a much better way. see jdk Long.java