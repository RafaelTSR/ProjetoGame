package core.efeitos;

import core.atores.Ator;
import core.stats.Atributo;
import core.dado.Dados;
import core.carta.Carta;

public class EfeitoCura implements Efeito{
    private final Dados dado;
    private final Atributo atributo;

    public EfeitoCura (Dados dado, Atributo atributo){
                this.dado = dado;
                this.atributo = atributo;
    }

    // Cura voce mesmo, dps pensar como curar mais pessoas.
    @Override
    public void aplicar(Ator usuario, Ator alvo, Carta carta) {
        int cura = dado.rolar() + usuario.getAtributos().modificador(atributo);
        usuario.curar(cura);
    }
}
