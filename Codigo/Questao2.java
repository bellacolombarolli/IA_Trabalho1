import java.util.LinkedList;
import java.util.Arrays;
import java.util.Scanner;
public class Questao2 extends Questao1{
    LinkedList<Ponto> candidato = new LinkedList<Ponto>();
    Arquivo arquivo = new Arquivo();
    Ponto[] candidatoArray;

    
    /**
     * a: Gerar uma permutacao qualquer dos pontos;(Criei todas, basta escolher um aleatorio)
     * b: Aplicar a heuristica "nearest-neighbour first"
     * @param flag 'a' se for a letra A e 'b' para a letra b
     */
    public void q2(Scanner in,char flag){
        int N = in.nextInt();       
        LinkedList<Ponto> a = le_pontos(in,N);
        LinkedList<Ponto> permutacao;
        
        if(flag == 'a'){
            permutacao = criaPermutacoes(new LinkedList<Ponto>(),a);
            candidatoArray = permutacao.toArray(new Ponto[permutacao.size()]);
        }else{
            NearestNeighbourFirst(a);
            candidatoArray = candidato.toArray(new Ponto[candidato.size()]);
        }
        System.out.println(Arrays.toString(candidatoArray));
        arquivo.escreveCoordenada(candidatoArray);
    }


    /**
     * Le um arquivo com N pontos no formato X1, Y1
     * @param N quantidade de pontos
     * @return array com todos os pontos
     */
    public LinkedList<Ponto> le_pontos(Scanner in,int N){
        LinkedList<Ponto> pontos = new LinkedList<Ponto>();
        for(int i = 0 ; i < N ; i++){ 
            pontos.add(new Ponto(in.nextInt(),in.nextInt()));
        }
        return pontos;
    }
    
    /*-----A-----*/

    /**
     * Gera permutacao para um vetor
     * @param lista lista da permutacao atual.Inicialmente é vazia
     * @param vetor lista com todos os elementos ainda não add na permutacao.Inicialmente contem todos os elementos
     */
    public LinkedList<Ponto> criaPermutacoes(LinkedList<Ponto> lista, LinkedList<Ponto> vetor){
        if(vetor.size() == 1){ // quando ja usamos todos os valores do vetor menos um
            lista.add(vetor.removeFirst());
            return lista;
        }
        int n = rand(vetor.size()-1, 0); // gera uma posição aleatoria da lista vetor
        lista.add(vetor.remove(n));//add o elemento na posição n do vetor 
        return criaPermutacoes(lista,vetor);
    }
  
    /*-----B-----*/

    /**
     * Retorna o quadrado da  distancia entre o ponto (x1,y1) e (x2,y2)
     * 
     */
    public int distancia(int x1, int y1, int x2, int y2){ // check
        return (int)(Math.pow((x1-x2),2) + Math.pow((y1-y2),2));
    }

    /**
     * Calcula a distancia entre um ponto origem e todos os elementos na lista
     * @param origem ponto que estamos considerando origem
     * @param lista todos os outros pontos
     * @return o ponto mais perto
     */   
    public Ponto proximoPonto(Ponto origem, LinkedList<Ponto> lista){ //check
        int menor = 1000000000;
        int n = 0;
        for(int i = 0 ; i < lista.size() ; i++){
            int distanciaP = distancia(origem.X,origem.Y, lista.get(i).X, lista.get(i).Y);
            if(distanciaP < menor){
                menor = distanciaP;
                n = i;
            }
        }

        return lista.remove(n);
    }

    /**
     * Usa um heuristica que escolhe os pontos com base na distancia entre o ponto e o resto
     * @param origem valor atual escolhido como origem
     * @param lista lista de elementos com todos os pontos ainda n add 
     */
    private void NearestNeighbourFirst(Ponto origem, LinkedList<Ponto> lista){
        if(lista.size()  == 0 ){
            return;
        }
        Ponto px = proximoPonto(origem, lista);
        candidato.add(px);
        px.posicao = candidato.size()-1;
        NearestNeighbourFirst(px, lista);
    }

    public void NearestNeighbourFirst(LinkedList<Ponto> lista){
        int posicao = rand(lista.size()-1, 0);
        Ponto origem = lista.remove(posicao);
        candidato.add(origem);
        origem.posicao = 0;
        NearestNeighbourFirst(origem, lista);
    }
    

}
