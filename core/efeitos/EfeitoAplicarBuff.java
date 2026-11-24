package core.efeitos;

import core.atores.Ator;
import core.carta.Carta;
import java.util.Objects;
import java.util.function.Supplier;

public class EfeitoAplicarBuff implements Efeito {
    // ALTERAÇÃO: guarda uma fábrica, não uma instância mutável.
    // A fábrica deve retornar um NOVO buff em cada chamada.
    private final Supplier<? extends Buff> fabrica;

    public EfeitoAplicarBuff(Supplier<? extends Buff> fabrica) {
        this.fabrica = Objects.requireNonNull(fabrica, "fabrica");
    }

    @Override
    public void aplicar(Ator usuario, Ator alvo, Carta carta) {
        // ALTERAÇÃO: cria um buff independente a cada aplicação.
        alvo.adicionarBuff(fabrica.get());
    }
}