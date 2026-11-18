package core.carta;

import core.dado.Dados;
import core.efeitos.EfeitoDano;
import core.stats.Atributo;

public class CatalogoCartas {

    // Builder: id, nome, tipo, custo, cópias
    public static final Carta ESPADA_LONGA = new Carta.Builder(1, "Espada Longa", TipoCarta.ARMA, 3, 4)
            .atributo(Atributo.FORCA)
            .efeito(new EfeitoDano(new Dados(1, 8), TipoDano.CORTANTE))
            .build();

    public static final Carta MACHADO_GRANDE = new Carta.Builder(2, "Machado Grande", TipoCarta.ARMA, 5, 3)
            .atributo(Atributo.FORCA)
            .efeito(new EfeitoDano(new Dados(1, 12), TipoDano.CORTANTE))
            .build();
}