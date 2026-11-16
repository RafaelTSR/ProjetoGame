package core.carta;
import core.stats.Atributo;
import core.dado.Dados;

public class CatalogoCartas {

    // Nos paramentos em cima(obrigatorio): id, nome, tipo, custo, quantidade.
    public static final Carta Espada_Longa = new Carta.Builder (1, "Espada Longa", TipoCarta.ARMA, 3, 4)
        .atributo(Atributo.FORCA)
        .dano(new Dados(1, 8), TipoDano.CORTANTE) 
        .build();

    public static final Carta Machado_Grande = new Carta.Builder(2, "Machado Grande", TipoCarta.ARMA, 5, 3)
        .atributo(Atributo.FORCA)
        .dano(new Dados(1, 12), TipoDano.CORTANTE)
        .build();

}       
