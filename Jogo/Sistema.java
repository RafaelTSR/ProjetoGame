public class Sistema {
    public static boolean acerto(){
        int resultado = Dados.d20();
        //DEPOIS INSERIR CA INIMIGA E O ATRIBUTO
        int CA = 10;
        int bonus = 2;
        if (resultado == 20) {
            System.out.println("D20: " + resultado + " + " + bonus);
            System.out.println("Resultado: " + (resultado + bonus));
            System.out.println("Sucesso Crítico");
            return true;
        }
        else if (resultado == 1) {
            System.out.println("D20: " + resultado + " + " + bonus);
            System.out.println("Resultado: " + (resultado + bonus));
            System.out.println("Falha Crítica");
            return false;
        }
        else if (resultado + bonus >= CA) {
            System.out.println("D20: " + resultado + " + " + bonus);
            System.out.println("Resultado: " + (resultado + bonus));
            System.out.println("Sucesso");
            return true;
        }
        else {
            System.out.println("D20: " + resultado + " + " + bonus);
            System.out.println("Resultado: " + (resultado + bonus));
            System.out.println("Falha");
            return false;
        }
    }
}
