package core.efeitos;

import core.atores.Ator;
import core.carta.Carta;

public interface Efeito {
    void aplicar(Ator usuario, Ator alvo, Carta carta);
}