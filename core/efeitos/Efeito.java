package core.efeitos;

import core.atores.Ator;

public interface Efeito {
    void aplicar(Ator usuario, Ator alvo);
}