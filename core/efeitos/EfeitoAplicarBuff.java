package core.efeitos;

import core.atores.Ator;
import core.carta.Carta;

public class EfeitoAplicarBuff implements Efeito {
    private final Buff buff;

    public EfeitoAplicarBuff(Buff buff) {
        this.buff = buff;
    }

    @Override
    public void aplicar(Ator usuario, Ator alvo, Carta carta) {
        // método futuro do Ator
        //alvo.adicionarBuff(buff);
    }
}