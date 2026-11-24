package core.efeitos;

import core.carta.TipoDano;
import core.dado.Dados;

public class BuffDanoExtra implements Buff {
    private final Dados dado;
    private final TipoDano tipo;
    private int usosRestantes;


    public BuffDanoExtra(Dados dado, TipoDano tipo, int usos) {
        // ALTERAÇÃO: valida os dados do buff antes de utilizá-lo.
        if (usos <= 0) throw new IllegalArgumentException("Usos devem ser positivos");
        this.dado = java.util.Objects.requireNonNull(dado, "dado");
        this.tipo = java.util.Objects.requireNonNull(tipo, "tipo");
        this.usosRestantes = usos;
    }

    @Override
    public int bonusAoCausarDano() {
        // ALTERAÇÃO: um buff gasto não pode causar dano novamente.
        if (!ativo()) return 0;
        usosRestantes--;
        return dado.rolar();
    }

    @Override
    public boolean ativo() {
        return usosRestantes > 0;
    }

    // ALTERAÇÃO: sobrescreve o método que EfeitoDano realmente consulta.
    @Override
    public TipoDano tipoDanoAdicional() { return tipo; }

    public TipoDano getTipoDano(){ return tipo; }
}