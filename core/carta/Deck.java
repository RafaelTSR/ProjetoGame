package core.carta;

import java.util.ArrayList;
import java.util.List;

import core.dado.Dados;

public class Deck {
    private static final int LIMITE = 30;
    private final List<Carta> deck = new ArrayList<>();

    // Vai adicionar uma carta no deck.
    public void adicionar(Carta carta){
        if(deck.size() < LIMITE){
            deck.add(carta);
        }    
    }

    //Usado para adicionar multiplas da msm carta no deck.
    public void adicionar(Carta carta, int quantidade){
        for(int i = 0; i < quantidade; i++){
            if(deck.size() < LIMITE){
                deck.add(carta);
            } 
        }
    }

    // Compra carta e retira do deck, utilizar na mão
    public Carta comprar() {
        if (deck.isEmpty()) return null; //Dps mexer nisso aqui pra decidir oq retorna
        int i = Dados.rolar(deck.size()) - 1;
        return deck.remove(i);
    }

    public boolean estaVazio(){
        return deck.isEmpty();
    }

    public int getRestanteDeck(){ return deck.size();} // Retorna a quant de cartas restantes
    public int getDeckLivre(){ return LIMITE - deck.size();} // Retorna o espaço restante
}
