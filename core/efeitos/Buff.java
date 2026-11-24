package core.efeitos;

import core.atores.Ator;
import core.carta.TipoDano;

public interface Buff {
    boolean ativo();                                  // obrigatório

    default void aoAplicar(Ator dono) {}              // quando entra no ator
    default void aoExpirar(Ator dono) {}              // quando sai do ator
    default void aoIniciarTurno(Ator dono) {}         // todo início de turno do dono
    default int bonusAoCausarDano() { return 0; }     // dado extra por ataque (futuro)
    // ALTERAÇÃO: nome padronizado para permitir a sobrescrita correta.
    default TipoDano tipoDanoAdicional() { return null; }
    default int bonusCA() { return 0; }               // somado no getCA()
}