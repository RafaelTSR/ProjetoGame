package core.atores;

import core.carta.Deck;
import core.stats.Atributos;

public abstract class Heroi extends Ator{
    private int PA = 0;
    protected Deck deck = new Deck();

    public Heroi (String nome, int vida, Atributos atributos) {
        super(nome, vida, atributos);
    }
}
