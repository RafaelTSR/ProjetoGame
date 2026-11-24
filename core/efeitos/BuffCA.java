package core.efeitos;

import core.atores.Ator;

public class BuffCA implements Buff {
    private final int valor;
    private int turnosRestantes;

    public BuffCA(int valor, int turnos) {
        this.valor = valor;
        // ALTERAÇÃO: duração inicial precisa ser positiva.
        if (turnos <= 0) throw new IllegalArgumentException("Turnos devem ser positivos");
        this.turnosRestantes = turnos;
    }

    @Override
    public int bonusCA() {
        return valor;
    }

    @Override
    public void aoIniciarTurno(Ator dono) {
        turnosRestantes--;
    }

    @Override
    public boolean ativo() {
        return turnosRestantes > 0;
    }
}