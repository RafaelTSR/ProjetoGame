package core.efeitos;

import core.atores.Ator;
import core.dado.Dados;
import core.carta.TipoDano;
import core.carta.Carta;

public class EfeitoDano implements Efeito {
    private final Dados dados;
    private final TipoDano tipoDano;

    public EfeitoDano(Dados dados, TipoDano tipoDano) {
        this.dados = dados;
        this.tipoDano = tipoDano;
    }

    @Override
    public void aplicar(Ator usuario, Ator alvo, Carta carta) {
        int dano = dados.rolar() + usuario.getAtributos().modificador(carta.getAtributo());
        alvo.receberDano(dano);
    }

    public Dados getDados() { return dados; }
    public TipoDano getTipoDano() { return tipoDano; }
}