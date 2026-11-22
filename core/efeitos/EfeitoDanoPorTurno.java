package core.efeitos;

import core.atores.Ator;
import core.carta.Carta;
import core.carta.TipoDano;
import core.dado.Dados;

public class EfeitoDanoPorTurno implements Efeito {
    private final Dados dano;
    private final TipoDano tipoDano;
    private final int turnos;

    public EfeitoDanoPorTurno(Dados dano, TipoDano tipoDano, int turnos) {
        this.dano = dano;
        this.tipoDano = tipoDano;
        this.turnos = turnos;
    }

    @Override
    public void aplicar(Ator usuario, Ator alvo, Carta carta) {
        alvo.adicionarBuff(new BuffDanoPorTurno(dano, tipoDano, turnos));
    }
}