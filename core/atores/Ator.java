package core.atores;

import java.util.ArrayList;
import java.util.List;

import core.carta.TipoDano;
import core.efeitos.Buff;
import core.stats.Atributo;
import core.stats.Atributos;

public abstract class Ator {

    protected String nome;
    protected int vida;
    protected int vidaMaxima;
    protected Atributos atributos;

    // Buffs vivos neste ator (estado atual, muda durante a batalha)
    protected final List<Buff> buffs = new ArrayList<>();

    public Ator(String nome, int vida, Atributos atributos) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.atributos = atributos;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    // CA = 10 + mod DES + bônus de CA dos buffs ativos
    public int getCA() {
        int ca = 10 + atributos.modificador(Atributo.DESTREZA);
        for (Buff b : buffs) {
            ca += b.bonusCA();
        }
        return ca;
    }

    public void receberDano(int dano) {
        // Devolve o maior
        vida = Math.max(0, vida - dano);
    }

    // Ponto único onde a afinidade (resistência/vulnerabilidade) vai morar
    public void receberDano(int dano, TipoDano tipo) {
        // TODO: aplicar afinidade do alvo ao tipo de dano
        receberDano(dano);
    }

    public void curar(int quantidade) {
        // Devolve o menor
        vida = Math.min(vidaMaxima, vida + quantidade);
    }

    // Guarda o buff e dispara o gancho de entrada (ex.: BuffAtributo soma o bônus)
    public void adicionarBuff(Buff buff) {
        buffs.add(buff);
        buff.aoAplicar(this);
    }

    // A Batalha chama isto no início do turno deste ator
    public void iniciarTurno() {
        for (Buff b : buffs) {
            b.aoIniciarTurno(this);
        }
        buffs.removeIf(b -> {
            if (!b.ativo()) {
                b.aoExpirar(this);   // desfaz o efeito (ex.: remove bônus de atributo)
                return true;
            }
            return false;
        });
    }

    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public Atributos getAtributos() { return atributos; }
    public List<Buff> getBuffs() { return List.copyOf(buffs); }
}