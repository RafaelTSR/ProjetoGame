package core.efeitos;

import core.atores.Ator;
import core.dado.Dados;
import core.carta.Carta;

public class EfeitoCura implements Efeito{
    private final Dados dado;

    public EfeitoCura (Dados dado){
        this.dado = dado;
    }

    //Obs 1: Cura voce mesmo, dps pensar como curar mais pessoas.
    //Obs 2: Dps pensar em curar dado + nv.
    @Override
    public void aplicar(Ator usuario, Ator alvo, Carta carta) {
        // ALTERAÇÃO: permite cura sem atributo, como Segundo Fôlego (1d10).
        int cura = dado.rolar();
        if (carta.getAtributo() != null) {
            cura += usuario.getAtributos().modificador(carta.getAtributo());
        }
        usuario.curar(cura);
    }
}
