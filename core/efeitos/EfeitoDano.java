package core.efeitos;

import core.atores.Ator;
import core.stats.Atributo;
import core.dado.Dados;
import core.carta.TipoDano;
import core.carta.Carta;

public class EfeitoDano implements Efeito {
    private final Dados dados;
    private final TipoDano tipoDano;
    private final Atributo atributo;

    public EfeitoDano(Dados dados, TipoDano tipoDano, Atributo atributo) {
        this.dados = dados;
        this.tipoDano = tipoDano;
        this.atributo = atributo;
    }

    @Override
    public void aplicar(Ator usuario, Ator alvo, Carta carta) {
        int dano = dados.rolar() + usuario.getAtributos().modificador(atributo);
        alvo.receberDano(dano);
    }

    public Dados getDados() { return dados; }
    public TipoDano getTipoDano() { return tipoDano; }
}