package core.atores.aventureiros;
import core.atores.Ator;
import core.stats.Atributos;

public class Guerreiro extends Ator {
    
    public Guerreiro(){
        super("Gabriel, O guerreiro.", 16);
        this.atributos = new Atributos (18, 12, 16, 8, 12, 8);
    }
}
