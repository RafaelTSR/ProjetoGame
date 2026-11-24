package core.efeitos;

import core.atores.Ator;
import core.carta.Carta;
import core.carta.TipoDano;
import core.dado.Dados;
import java.util.Objects;

// ALTERAÇÃO: nova classe; guarda somente a receita do buff de dano extra.
public class EfeitoDanoExtra implements Efeito {
    private final Dados dado;
    private final TipoDano tipo;
    private final int usos;

    public EfeitoDanoExtra(Dados dado, TipoDano tipo, int usos) {
        this.dado = Objects.requireNonNull(dado, "dado");
        this.tipo = Objects.requireNonNull(tipo, "tipo");
        if (usos <= 0) throw new IllegalArgumentException("Usos devem ser positivos");
        this.usos = usos;
    }

    @Override
    public void aplicar(Ator usuario, Ator alvo, Carta carta) {
        // Cada aplicação tem seu próprio contador de usos.
        alvo.adicionarBuff(new BuffDanoExtra(dado, tipo, usos));
    }
}
