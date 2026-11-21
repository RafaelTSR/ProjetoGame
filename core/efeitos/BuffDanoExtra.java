package core.efeitos;

import core.dado.Dados;

public class BuffDanoExtra implements Buff {
    private final Dados dado;
    private int usosRestantes;

    public BuffDanoExtra(Dados dado, int usos) {
        this.dado = dado;
        this.usosRestantes = usos;
    }

    @Override
    public int bonusAoCausarDano() {
        usosRestantes--;            // gasta um uso
        return dado.rolar();
    }

    @Override
    public boolean ativo() {
        return usosRestantes > 0;
    }
}