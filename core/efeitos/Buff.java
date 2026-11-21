package core.efeitos;

import core.atores.Ator;

public interface Buff {
    default void aoIniciarTurno(Ator dono){}
    default int bonusAoCausarDano() { return 0;}
    boolean ativo();
}
