package core.carta;

import core.dado.Dados;
import core.efeitos.EfeitoDano;
import core.stats.Atributo;

public class CatalogoCartas {

    // Builder: id, nome, tipo, custo.
    public static final Carta ESPADA_LONGA = new Carta.Builder(1, "Espada Longa", TipoCarta.ARMA, 3)
            .atributo(Atributo.FORCA)
            .efeito(new EfeitoDano(new Dados(1, 8), TipoDano.CORTANTE, Atributo.FORCA))
            .build();

    public static final Carta MACHADO_GRANDE = new Carta.Builder(2, "Machado Grande", TipoCarta.ARMA, 5)
            .atributo(Atributo.FORCA)
            .efeito(new EfeitoDano(new Dados(1, 12), TipoDano.CORTANTE, Atributo.FORCA))
            .build();
    public static final Carta ALABARDA_GRANDE = new Carta.Builder(3, "Alabarda Grande",TipoCarta.ARMA, 4)
            .atributo(Atributo.FORCA)
            .efeito(new EfeitoDano(new Dados(1, 10), TipoDano.PERFURANTE, Atributo.FORCA))
            .build();
    public static final Carta ESCUDO = new Carta.Builder(4, "ESCUDO", TipoCarta.ARMA, 1)
            //.efeito(new EfeitoBuff())
            .build();
    public static final Carta CORTE_PODEROSO = new Carta.Builder (5, "Corte Poderoso", TipoCarta.TECNICA, 3)
            .atributo(Atributo.FORCA)
            .efeito(new EfeitoDano(new Dados(2, 8), TipoDano.CORTANTE, Atributo.FORCA))
            .build();
    

}