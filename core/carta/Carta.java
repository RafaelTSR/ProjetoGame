package core.carta;

import core.efeitos.Efeito;
import core.stats.Atributo;

import java.util.ArrayList;
import java.util.List;

public class Carta {
    private final int id;
    private final String nome;
    private final TipoCarta tipoCarta;
    private final int custoPA;
    private final int copias;
    private final Atributo atributo;   // pode ser null
    private final int bonusCarta;
    private final List<Efeito> efeitos;

    private Carta(Builder b) {
        this.id = b.id;
        this.nome = b.nome;
        this.tipoCarta = b.tipoCarta;
        this.custoPA = b.custoPA;
        this.copias = b.copias;
        this.atributo = b.atributo;
        this.bonusCarta = b.bonusCarta;
        this.efeitos = List.copyOf(b.efeitos);   // cópia imutável
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public TipoCarta getTipoCarta() { return tipoCarta; }
    public int getCustoPA() { return custoPA; }
    public int getCopias() { return copias; }
    public Atributo getAtributo() { return atributo; }
    public int getBonusCarta() { return bonusCarta; }
    public List<Efeito> getEfeitos() { return efeitos; }

    // Prancheta de montagem da carta
    public static class Builder {
        private final int id;
        private final String nome;
        private final TipoCarta tipoCarta;
        private final int custoPA;
        private final int copias;
        private Atributo atributo;
        private int bonusCarta = 0;
        private final List<Efeito> efeitos = new ArrayList<>();

        public Builder(int id, String nome, TipoCarta tipoCarta, int custoPA, int copias) {
            this.id = id;
            this.nome = nome;
            this.tipoCarta = tipoCarta;
            this.custoPA = custoPA;
            this.copias = copias;
        }

        public Builder atributo(Atributo a) { this.atributo = a; return this; }
        public Builder bonus(int b) { this.bonusCarta = b; return this; }
        public Builder efeito(Efeito e) { this.efeitos.add(e); return this; }

        public Carta build() { return new Carta(this); }
    }
}