package core.atores;

import core.stats.Atributo;
import core.stats.Atributos;

public abstract class Ator {
    
    protected String nome;
    protected int vida;
    protected int vidaMaxima;
    protected Atributos atributos;

    public Ator(String name, int vida){
        nome = name;
        this.vida = vida; 
        vidaMaxima = this.vida;
    }

    public boolean estaVivo (){
        return vida > 0;
    }

    public int getCA (){
        return 10 + atributos.modificador(Atributo.DESTREZA);   
    }
}

