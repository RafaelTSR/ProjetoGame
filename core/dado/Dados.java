import java.util.Random;

public class Dados {
    private static final Random random = new Random();

    public static int rolar(int lados) {
        return random.nextInt(lados) + 1;
    }

    public static int rolar(int quantidade, int lados) {
        int total = 0;
        for (int i = 0; i < quantidade; i++) {
            total += rolar(lados);
        }
        return total;
    }
}