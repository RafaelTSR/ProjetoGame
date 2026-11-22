package core.efeitos;

import core.atores.Ator;
import core.stats.Atributo;

public class BuffAtributo implements Buff {
    private final Atributo atributo;
    private final int valor;
    private int turnosRestantes;

    public BuffAtributo(Atributo atributo, int valor, int turnos) {
        this.atributo = atributo;
        this.valor = valor;
        this.turnosRestantes = turnos;
    }

    @Override
    public void aoAplicar(Ator dono) {
        dono.getAtributos().adicionarBonus(atributo, valor);
    }

    @Override
    public void aoExpirar(Ator dono) {
        dono.getAtributos().removerBonus(atributo, valor);
    }

    @Override
    public void aoIniciarTurno(Ator dono) {
        turnosRestantes--;
    }

    @Override
    public boolean ativo() {
        return turnosRestantes > 0;
    }
}