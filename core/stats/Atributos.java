package core.stats;

import java.util.EnumMap;
import java.util.Map;

public class Atributos {
    // Dicionario p/ guarda
    private final Map<Atributo, Integer> base = new EnumMap<>(Atributo.class);
    private final Map<Atributo, Integer> bonus = new EnumMap<>(Atributo.class);

    public Atributos(int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma){
        //Comandos do Map; put = substitui o valor, get = retorna o valor, merge = soma o valor
        base.put(Atributo.FORCA, forca);
        base.put(Atributo.DESTREZA, destreza);
        base.put(Atributo.CONSTITUICAO, constituicao);
        base.put(Atributo.INTELIGENCIA, inteligencia);
        base.put(Atributo.SABEDORIA, sabedoria);
        base.put(Atributo.CARISMA, carisma);

    }
    //Retorna o valor do atributo ou zero.
    public int get(Atributo a){
        return base.get(a) + bonus.getOrDefault(a,0);
    }

    //Calcula o resultado do modificador
    public int modificador(Atributo a){
        return Math.floorDiv(get(a) - 1, 2);
    }

    // função p/ adicionar buff após usar uma carta
    public void adicionarBonus (Atributo a, int valor){
        bonus.merge(a, valor, Integer::sum);
    }

    // função pra remover o buff
    public void removerBonus (Atributo a, int valor){
        bonus.merge(a, -valor, Integer::sum);
    }
}
