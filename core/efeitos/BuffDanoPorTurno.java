package core.efeitos;

import core.atores.Ator;
import core.carta.TipoDano;
import core.dado.Dados;

public class BuffDanoPorTurno implements Buff {
    private final Dados dano;
    private final TipoDano tipoDano;
    private int turnosRestantes;

    public BuffDanoPorTurno(Dados dano, TipoDano tipoDano, int turnos) {
        this.dano = dano;
        this.tipoDano = tipoDano;
        this.turnosRestantes = turnos;
    }

    @Override
    public void aoIniciarTurno(Ator dono) {
        dono.receberDano(dano.rolar(), tipoDano);   // rola um dado novo a cada turno
        turnosRestantes--;
    }

    @Override
    public boolean ativo() {
        return turnosRestantes > 0;
    }
}