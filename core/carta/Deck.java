package core.carta;

import java.util.ArrayList;
import java.util.List;

import core.dado.Dados;

public class Deck {
    private static final int LIMITE = 30;
    private final List<Carta> deck = new ArrayList<>();

    // Vai adicionar uma carta no deck.
    public void adicionar(Carta carta){
        // ALTERAÇÃO: impede cartas nulas no deck.
        java.util.Objects.requireNonNull(carta, "carta");
        if(deck.size() < LIMITE){
            deck.add(carta);
        }    
    }

    //Usado para adicionar multiplas da msm carta no deck.
    public void adicionar(Carta carta, int quantidade){
        // ALTERAÇÃO: reutiliza adicionar e para quando o deck estiver cheio.
        java.util.Objects.requireNonNull(carta, "carta");
        if (quantidade < 0) throw new IllegalArgumentException("Quantidade negativa");
        for(int i = 0; i < quantidade && deck.size() < LIMITE; i++){
            adicionar(carta);
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
