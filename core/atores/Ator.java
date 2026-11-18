package core.atores;

import core.stats.Atributo;
import core.stats.Atributos;

public abstract class Ator {

    protected String nome;
    protected int vida;
    protected int vidaMaxima;
    protected Atributos atributos;

    public Ator(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public int getCA() {
        return 10 + atributos.modificador(Atributo.DESTREZA);
    }

    public void receberDano(int dano) {
        vida = Math.max(0, vida - dano);
    }

    public void curar(int quantidade) {
        vida = Math.min(vidaMaxima, vida + quantidade);
    }

    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public Atributos getAtributos() { return atributos; }
}