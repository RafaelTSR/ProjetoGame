import java.util.Random;

public class Dados {
    public static int d4() {
        Random random = new Random();
        int dado = random.nextInt(4);
        return dado;
    }
    public static int d6() {
        Random random = new Random();
        int dado = random.nextInt(6);
        return dado;
    }
    public static int d8() {
        Random random = new Random();
        int dado = random.nextInt(8);
        return dado;
    }
    public static int d10() {
        Random random = new Random();
        int dado = random.nextInt(10);
        return dado;
    }
    public static int d12() {
        Random random = new Random();
        int dado = random.nextInt(12);
        return dado;
    }
    public static int d20() {
        Random random = new Random();
        int dado = random.nextInt(20);
        return dado;
    }
}
