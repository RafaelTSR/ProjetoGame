package core.carta;
import core.dado.Dados;
import core.stats.Atributo;

public class Carta {
    private final int id;
    private final String nome;
    private final TipoCarta tipoCarta;
    private final int custoPA;

    private final Dados danoDados;  //Pode ficar vazio.
    private final Atributo atributo; //Pode ficar vazio.
    private final int bonusCarta; // Pode ficar vazio.
    private final TipoDano tipoDano; //Pode ficar vazio.
    private final int copias;

    private Carta (Builder b){
        this.id = b.id;
        this.nome = b.nome;
        this.tipoCarta = b.tipoCarta;
        this.custoPA = b.custoPA;
        this.atributo = b.atributo;
        this.danoDados = b.danoDados;
        this.tipoDano = b.tipoDano;
        this.bonusCarta = b.bonusCarta;
        this.copias = b.copias;
    }

    public int getId(){ return id;}
    public String getNome(){ return nome;}
    public TipoCarta getTipoCarta(){ return tipoCarta;}
    public int getCustoPA(){ return custoPA;}
    public Dados getDados(){ return danoDados;}
    public Atributo getAtributo(){ return atributo;}
    public int getBonusCarta(){ return bonusCarta;}
    public TipoDano getTipoDano(){ return tipoDano;}
    public int getCopias(){ return copias;}

    // classe builder, aprendi q ela cria meio q uma prancheta, é melhor para uasr em cartas q n requer tudo.
    public static class Builder{
        private final int id;
        private final String nome;
        private final TipoCarta tipoCarta;
        private final int custoPA;
        private Dados danoDados;
        private Atributo atributo;
        private int bonusCarta = 0;
        private TipoDano tipoDano;
        private int copias = 1;
        
        // construct dela
        public Builder (int id, String nome, TipoCarta tipoCarta, int custoPA, int copias){
            this.id = id;
            this.nome = nome;
            this.tipoCarta = tipoCarta;
            this.custoPA = custoPA;
            this.copias = copias;
        }

        public Builder dano(Dados d, TipoDano t){ this.danoDados = d; this.tipoDano = t; return this;}
        public Builder atributo(Atributo a){ this.atributo = a; return this;}
        public Builder bonus(int b){ this.bonusCarta = b; return this;}
        public Carta build(){ return new Carta (this);}

        // Função pra usar quando utilizar a carta, se for carta de dano ele ataca.
        public boolean causaDano(){
            return danoDados != null;
        }
    }
}
