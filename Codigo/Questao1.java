import java.util.Scanner;
import java.util.Random;

public class Questao1 {
    /**
     * Gera valores aleatorios entre o intervalo minimo e maximo
     * @param max Valor superior do intervalo
     * @param min Valor inferior do intervalo
     * @return Valor randomizao
     */
    public int rand(int max, int min){
        Random r = new Random();
        return r.nextInt((max-min) + 1) + min;//[min,max]
    }

    /**
     * Gerar aleatoriamente N pontos no plano com coordenadas inteiras, de −M a M, para N e M dados
     */
    public void q1(Scanner in){ 
        int N = in.nextInt();
        int M = in.nextInt();
        for(int i = 0 ; i < N ; i++){
            int x = rand(M,-M);
            int y = rand(M,-M);
            System.out.printf("(%d,%d)\n",x,y); //VERIFICAR SE O PONTO JA FOI CRIADO ANTES
        }
    }
}
