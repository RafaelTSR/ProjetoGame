package core.efeitos;

import core.atores.Ator;
import core.dado.Dados;
import core.carta.Carta;

public class EfeitoCura implements Efeito{
    private final Dados dado;

    public EfeitoCura (Dados dado){
                this.dado = dado;
    }

    // Cura voce mesmo, dps pensar como curar mais pessoas.
    @Override
    public void aplicar(Ator usuario, Ator alvo, Carta carta) {
        int cura = dado.rolar() + usuario.getAtributos().modificador(carta.getAtributo());
        usuario.curar(cura);
    }
}
