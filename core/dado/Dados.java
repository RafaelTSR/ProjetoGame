package core.dado;

import java.util.Random;

public class Dados {
    private static final Random random = new Random();

    private final int quantidade;
    private final int lados;

    public Dados(int quantidade, int lados) {
        // ALTERAÇÃO: quantidade e lados devem ser positivos.
        if (quantidade <= 0 || lados <= 0) {
            throw new IllegalArgumentException("Quantidade e lados devem ser positivos");
        }
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
        // ALTERAÇÃO: valida também a rolagem estática.
        if (lados <= 0) throw new IllegalArgumentException("Lados devem ser positivos");
        return random.nextInt(lados) + 1;
    }
}