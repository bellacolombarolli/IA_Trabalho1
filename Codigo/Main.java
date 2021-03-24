import java.util.Scanner;
class Main {
    public static void main(String[] args){
        Questao1 q_1 = new Questao1();
        Questao2 q_2 = new Questao2();
        Questao3 q_3 = new Questao3();
        Questao4 q_4 = new Questao4();
        Scanner in = new Scanner(System.in);
        int N_questao = in.nextInt(); //numero da questão 
        String letra;
        switch(N_questao){
            case 1:
                q_1.q1(in);
                break;
            case 2:
                letra = in.next();
                q_2.q2(in,letra.charAt(0));
                break;
            case 3:
                q_3.q3(in);
                break;
            case 4:
                letra = in.next();
                q_4.q4(in,letra.charAt(0));
                break;
        }
    }   
}

// javac Main.java && java Main < dominio_input.txt 
// javac Main.java && java Main < pontos_input.txt 