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
    private final Atributo atributo;   // pode ser null
    // ALTERAÇÃO: removido bonusCarta; os valores ficam nos efeitos.
    private final List<Efeito> efeitos;

    private Carta(Builder b) {
        this.id = b.id;
        this.nome = b.nome;
        this.tipoCarta = b.tipoCarta;
        this.custoPA = b.custoPA;
        this.atributo = b.atributo;
        this.efeitos = List.copyOf(b.efeitos);   // cópia imutável
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public TipoCarta getTipoCarta() { return tipoCarta; }
    public int getCustoPA() { return custoPA; }
    public Atributo getAtributo() { return atributo; }
    public List<Efeito> getEfeitos() { return efeitos; }

    // Prancheta de montagem da carta
    public static class Builder {
        private final int id;
        private final String nome;
        private final TipoCarta tipoCarta;
        private final int custoPA;
        private Atributo atributo;
        private final List<Efeito> efeitos = new ArrayList<>();

        public Builder(int id, String nome, TipoCarta tipoCarta, int custoPA) {
            // ALTERAÇÃO: impede custo negativo e dados obrigatórios nulos.
            if (custoPA < 0) throw new IllegalArgumentException("Custo PA negativo");
            java.util.Objects.requireNonNull(nome, "nome");
            java.util.Objects.requireNonNull(tipoCarta, "tipoCarta");
            this.id = id;
            this.nome = nome;
            this.tipoCarta = tipoCarta;
            this.custoPA = custoPA;
        }

        public Builder atributo(Atributo a) { this.atributo = a; return this; }
        public Builder efeito(Efeito e) { this.efeitos.add(java.util.Objects.requireNonNull(e, "efeito")); return this; }

        public Carta build() { return new Carta(this); }
    }
}