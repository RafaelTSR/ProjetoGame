package core.carta;

import java.util.ArrayList;
import java.util.List;

import core.dado.Dados;

public class Deck {
    private final List<Carta> cartas = new ArrayList<>();

    // Vai adicionar a quantidade de cartas no deck beseado em quantos tem disponiveis.
    public void adicionar(Carta carta){
        for(int i = 0; i < carta.getCopias(); i++){
            cartas.add(carta);
        }
    }

    // Compra carta e retira do deck, utilizar na mão
    public Carta comprar() {
        if (cartas.isEmpty()) return null; //Dps mexer nisso aqui pra decidir oq retorna
        int i = Dados.rolar(cartas.size()) - 1;
        return cartas.remove(i);
    }

    public boolean estaVazio(){
        return cartas.isEmpty();
    }
}
