package core.efeitos;

import core.atores.Ator;
import core.carta.Carta;

public class EfeitoBuffCA implements Efeito {
    private final int valor;
    private final int turnos;

    public EfeitoBuffCA(int valor, int turnos) {
        this.valor = valor;
        this.turnos = turnos;
    }

    @Override
    public void aplicar(Ator usuario, Ator alvo, Carta carta) {
        //É alvo enquanto pq teria q criar uma classe nova pra buff proprio
        alvo.adicionarBuff(new BuffCA(valor, turnos));
    }
}