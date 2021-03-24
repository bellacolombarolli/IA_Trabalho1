import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
public class Questao4 extends Questao3{
    /**
     * Aplica o alg hill_Climbing
     * @param candidato Configuração original
     * @param f flag para a questão. a,b,c,d.
     */
    public void hill_Climbing(char f, Ponto[] candidato ){ 
        Ponto[] atual = new Ponto[candidato.length];
        System.arraycopy(candidato, 0, atual, 0, candidato.length);
        while(true){
            Ponto[] Vizinho = proximo(atual,f);
            System.out.println("Atual: " + Arrays.toString(atual) + " Vizinho: " + Arrays.toString(Vizinho));
            System.out.println(Qconflitos(atual) + " " + Qconflitos(Vizinho) );
            if(Qconflitos(Vizinho) >= Qconflitos(atual)){
                arquivo.escreveCoordenada(Vizinho);
                System.out.println("Fim");
                return;
            }
            System.arraycopy(Vizinho, 0, atual, 0, candidato.length);
        }
    }

    public int Qconflitos(Ponto[] atual){//so retorna quantidade d conflitos
        int N = atual.length;
        int n = 0; //local da origem
        int count = 0;
        for(int i = 0 ; i < N ; i++){
            count += quantidade_cruzamentos(n,N, atual);
            n++;
        }
        return count;
    }

    public void q4(Scanner in,char f){
        int N = in.nextInt();       
        LinkedList<Ponto> a = le_pontos(in,N);
        NearestNeighbourFirst(a); //Caminho formado
        candidatoArray = candidato.toArray(new Ponto[candidato.size()]);

        hill_Climbing(f,candidatoArray);
    }

}
