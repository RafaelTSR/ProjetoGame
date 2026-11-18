package core.dado;

import java.util.Random;

public class Dados {
    private static final Random random = new Random();

    private final int quantidade;
    private final int lados;

    public Dados(int quantidade, int lados) {
        this.quantidade = quantidade;
        this.lados = lados;
    }

    public int getQuantidade() { return quantidade; }
    public int getLados() { return lados; }

    public int rolar() {
        int total = 0;
        for (int i = 0; i < quantidade; i++) {
            total += random.nextInt(lados) + 1;
        }
        return total;
    }

    public static int rolar(int lados) {
        return random.nextInt(lados) + 1;
    }
}