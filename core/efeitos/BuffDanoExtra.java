package core.efeitos;

import core.carta.TipoDano;
import core.dado.Dados;

public class BuffDanoExtra implements Buff {
    private final Dados dado;
    private final TipoDano tipo;
    private int usosRestantes;


    public BuffDanoExtra(Dados dado, TipoDano tipo, int usos) {
        this.dado = dado;
        this.tipo = tipo;
        this.usosRestantes = usos;
    }

    @Override
    public int bonusAoCausarDano() {
        usosRestantes--;
        return dado.rolar();
    }

    @Override
    public boolean ativo() {
        return usosRestantes > 0;
    }

    public TipoDano getTipoDano(){ return tipo; }
}