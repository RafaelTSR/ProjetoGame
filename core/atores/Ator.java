package core.atores;

import stats.Atributo;
import stats.Atributos;

public abstract class Ator {
    
    protected int vida;
    protected int vidaMaxima;
    protected Atributos atributos;

    public boolean estaVivo (){
        return vida > 0;
    }

    public int getCA (){
        return 10 + atributos.modificador(Atributo.DESTREZA);   
    }
}

