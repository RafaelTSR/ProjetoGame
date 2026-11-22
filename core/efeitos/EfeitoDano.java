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
        // O alvo vai receber o dado da carta
        int dano = dados.rolar() + usuario.getAtributos().modificador(carta.getAtributo());
        alvo.receberDano(dano , tipoDano);

        //O Alvo vai receber danos adicionais de Buff
        for (Buff b : usuario.getBuffs()){
            TipoDano tipoExtra = b.TipoDanoAdicional();
            //Pular buufs gastos e buffs q não são de dano
            if (!b.ativo() && tipoExtra == null) {
                continue;
            }
        int danoExtra = b.bonusAoCausarDano();
        alvo.receberDano(danoExtra, tipoExtra);
        }
    }

    public Dados getDados() { return dados; }
    public TipoDano getTipoDano() { return tipoDano; }
}