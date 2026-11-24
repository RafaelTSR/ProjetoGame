package core.atores;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import core.carta.TipoDano;
import core.efeitos.Buff;
import core.stats.Atributo;
import core.stats.Atributos;

public abstract class Ator {

    // ALTERAÇÃO: protege o estado contra alterações diretas pelas subclasses.
    private final String nome;
    private int vida;
    private int vidaMaxima;
    private final Atributos atributos;

    // Buffs vivos neste ator (estado atual, muda durante a batalha)
    private final List<Buff> buffs = new ArrayList<>();

    public Ator(String nome, int vida, Atributos atributos) {
        // ALTERAÇÃO: impede a criação de atores com estado inicial inválido.
        if (vida <= 0) throw new IllegalArgumentException("Vida inicial deve ser positiva");
        this.nome = Objects.requireNonNull(nome, "nome");
        this.vida = vida;
        this.vidaMaxima = vida;
        this.atributos = Objects.requireNonNull(atributos, "atributos");
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    // CA = 10 + mod DES + bônus de CA dos buffs ativos
    public int getCA() {
        int ca = 10 + atributos.modificador(Atributo.DESTREZA);
        for (Buff b : buffs) {
            // ALTERAÇÃO: somente buffs ativos contribuem para a CA.
            if (b.ativo()) ca += b.bonusCA();
        }
        return ca;
    }

    public void receberDano(int dano) {
        // ALTERAÇÃO: dano negativo não recupera vida.
        vida = Math.max(0, vida - Math.max(0, dano));
    }

    // Ponto único onde a afinidade (resistência/vulnerabilidade) vai morar
    public void receberDano(int dano, TipoDano tipo) {
        // TODO: aplicar afinidade do alvo ao tipo de dano
        receberDano(dano);
    }

    public void curar(int quantidade) {
        // ALTERAÇÃO: cura negativa não causa dano; evita estouro na soma.
        int cura = Math.min(Math.max(0, quantidade), vidaMaxima - vida);
        vida += cura;
    }

    // Guarda o buff e dispara o gancho de entrada (ex.: BuffAtributo soma o bônus)
    public void adicionarBuff(Buff buff) {
        // ALTERAÇÃO: não aplica buffs nulos, expirados ou a mesma instância duas vezes.
        Objects.requireNonNull(buff, "buff");
        if (!buff.ativo() || buffs.stream().anyMatch(b -> b == buff)) return;
        buffs.add(buff);
        buff.aoAplicar(this);
    }

    // A Batalha chama isto no início do turno deste ator
    public void iniciarTurno() {
        for (Buff b : buffs) {
            // ALTERAÇÃO: buffs já gastos não executam novos ganchos.
            if (b.ativo()) b.aoIniciarTurno(this);
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