package core.atores.aventureiros;

import core.atores.Heroi;
import core.carta.CatalogoCartas;
import core.stats.Atributos;

public class Guerreiro extends Heroi {

    public Guerreiro() {
        super("Gabriel, O guerreiro.",
              16,
              new Atributos(18, 12, 16, 8, 12, 8));

        deck.adicionar(CatalogoCartas.ESPADA_LONGA, 4);
        deck.adicionar(CatalogoCartas.MACHADO_GRANDE, 3);
    }
}