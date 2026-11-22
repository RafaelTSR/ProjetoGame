package core.carta;

import core.dado.Dados;
import core.efeitos.*;
import core.stats.Atributo;

public class CatalogoCartas {
        // Builder: id, nome, tipo, custo.

        //====================================
        //             ARMAS
        //====================================

        //>>>>>>>>>>>>>>>> GUERREIRO <<<<<<<<<<<<<<<<<<
        public static final Carta ESPADA_LONGA = new Carta.Builder(1, "Espada Longa", TipoCarta.ARMA, 3)
            .atributo(Atributo.FORCA)
            .efeito(new EfeitoDano(new Dados(1, 8), TipoDano.CORTANTE))
            .build();
        public static final Carta MACHADO_GRANDE = new Carta.Builder(2, "Machado Grande", TipoCarta.ARMA, 5)
            .atributo(Atributo.FORCA)
            .efeito(new EfeitoDano(new Dados(1, 10), TipoDano.CORTANTE))
            .build();
        public static final Carta ALABARDA_GRANDE = new Carta.Builder(3, "Alabarda Grande",TipoCarta.ARMA, 4)
            .atributo(Atributo.FORCA)
            .efeito(new EfeitoDano(new Dados(1, 10), TipoDano.PERFURANTE))
            .build();

        //>>>>>>>>>>>>>>>>>>>>> MAGO <<<<<<<<<<<<<<<<<<<<<<<
        public static final Carta CAJADO = new Carta.Builder(4, "CAJADO", TipoCarta.ARMA, 2)
            .atributo(Atributo.FORCA)
            .efeito(new EfeitoDano(new Dados(1, 6), TipoDano.CONTUNDENTE))
            .build();
        public static final Carta ADAGA = new Carta.Builder(5, "ADAGA", TipoCarta.ARMA, 1)
            .atributo(Atributo.DESTREZA)
            .efeito(new EfeitoDano(new Dados(1, 4), TipoDano.CORTANTE))
            .build();
        
        //>>>>>>>>>>>>>>>>>> CLERIGO <<<<<<<<<<<<<<<<<<<<
        public static final Carta MACA = new Carta.Builder(6, "MAÇA", TipoCarta.ARMA, 2)
            .atributo(Atributo.FORCA)
            .efeito(new EfeitoDano(new Dados(1, 6), TipoDano.CONTUNDENTE))
            .build();
        public static final Carta SIMBOLO_SAGRADO = new Carta.Builder (7, "Simbolo Sagrado", TipoCarta.ARMA, 3)
            .atributo(Atributo.SABEDORIA)
            .efeito(new EfeitoDano(new Dados(1, 8), TipoDano.RADIANTE))
            .build();

        //======================================
        //               TECNICAS
        //======================================

        //>>>>>>>>>>>>>>>> GUERREIRO <<<<<<<<<<<<<<<<<<
        public static final Carta CORTE_PODEROSO = new Carta.Builder(8, "Corte Poderoso", TipoCarta.TECNICA, 3)
            .atributo(Atributo.FORCA)
            .efeito(new EfeitoDano(new Dados(2, 8), TipoDano.CORTANTE))
            .build();
        public static final Carta ATAQUE_DUPLO = new Carta.Builder(9, "Ataque Duplo", TipoCarta.TECNICA, 4)
            .atributo(Atributo.FORCA)
            .efeito(new EfeitoDano(new Dados(2, 6), TipoDano.CONTUNDENTE))
            .build();
        public static final Carta POSTURA_DEFENSIVA = new Carta.Builder(10, "Postura Defensiva", TipoCarta.TECNICA, 2)
            .efeito(new EfeitoBuffCA(2, 2))
            .build();
        public static final Carta INVESTIDA = new Carta.Builder(11, "Investida", TipoCarta.TECNICA, 4)
        //Ideia Futura: Vantagem no proximo Ataque.
            .efeito(new EfeitoDano(new Dados(1, 8), TipoDano.CONTUNDENTE))
            .build();
        public static final Carta GOLPE_ATORDOANTE = new Carta.Builder(12, "Golpe Atordoante", TipoCarta.TECNICA, 3)
        //Ideia Futura: Colocar Efeito Atordoante.
            .efeito(new EfeitoDano(new Dados(1, 6), TipoDano.CONTUNDENTE))
            .build();
        public static final Carta SEGUNDA_FOLEGO = new Carta.Builder(13, "Segunda Folego", TipoCarta.TECNICA, 1)
            .efeito(new EfeitoCura(new Dados(1, 10)))
            .build();
        
        //>>>>>>>>>>>>>>>>>>>>> Mago <<<<<<<<<<<<<<<<<<<<<<<<
        public static final Carta CONCENTRACAO = new Carta.Builder(14, "Concentração", TipoCarta.TECNICA, 2)
        .efeito(new EfeitoAplicarBuff(new BuffDanoExtra(new Dados(1, 4), TipoDano.ENERGIA, 1)))
        .build();
        
    

}