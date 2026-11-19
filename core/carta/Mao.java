package core.carta;

import java.util.ArrayList;
import java.util.List;

public class Mao {
    private static final int LIMITE = 5; // Limite de cartas na mão
    private final List<Carta> mao = new ArrayList<>(); 

    // função para completar a mão (adicionar no unicio de carda turno)
    public void completar(Deck deck){
        while (mao.size() < LIMITE && !deck.estaVazio()){
            mao.add(deck.comprar());
        }
    }

    //Remove da mão
    public boolean usar(Carta carta){
        return mao.remove(carta);
    }

    //Verifica se ta vazia
    public boolean estaVazia(){
        return mao.isEmpty();
    }

    public Carta get(int indice){
        return mao.get(indice);
    }

    public int tamanho() { return mao.size(); } // Retorna Tamano da mão

    public List<Carta> getCartas() {
        return List.copyOf(mao);   // cópia: ninguém mexe na mão por fora
    }
}
