import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.LinkedList;
import java.util.TreeSet;
class Main {
    static Scanner in = new Scanner(System.in);
    static LinkedList<Ponto> candidato = new LinkedList<Ponto>();
    static Ponto[] candidatoArray;
    /*-------------------Questão1--------------------*/

    /**
     * Gera valores aleatorios entre o intervalo minimo e maximo
     * @param max Valor superior do intervalo
     * @param min Valor inferior do intervalo
     * @return Valor randomizao
     */
    public static int rand(int max, int min){
        Random r = new Random();
        return r.nextInt((max-min) + 1) + min;//[min,max]
    }

    /**
     * Gerar aleatoriamente N pontos no plano com coordenadas inteiras, de −M a M, para N e M dados
     */
    public static void q1(){ 
        int N = in.nextInt();
        int M = in.nextInt();
        for(int i = 0 ; i < N ; i++){
            int x = rand(M,-M);
            int y = rand(M,-M);
            System.out.printf("(%d,%d)\n",x,y); //VERIFICAR SE O PONTO JA FOI CRIADO ANTES
        }
    }
    
    /*-------------------Questão2--------------------*/

    /**
     * Le um arquivo com N pontos no formato X1, Y1
     * @param N quantidade de pontos
     * @return array com todos os pontos
     */
    public static LinkedList<Ponto> le_pontos(int N){
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
    public static LinkedList<Ponto> criaPermutacoes(LinkedList<Ponto> lista, LinkedList<Ponto> vetor){
        if(vetor.size() == 1){ // quando ja usamos todos os valores do vetor menos um
            lista.add(vetor.removeFirst());
            return lista;
        }
        int n = rand(vetor.size()-1, 0); // gera uma posição aleatoria da lista vetor
        lista.add(vetor.remove(n));//add o elemento na posição n do vetor 
        return criaPermutacoes(lista,vetor);
    }

    /**
     * Imprime a permutacao criada
     * @param p lista com a permutacao
     */
    public static void imprimePermutacao(LinkedList<Ponto> p){
        for(Ponto a : p){
            System.out.print(a.toString());
        }
        System.out.println();
    }
    
    /*-----B-----*/

    /**
     * Retorna o quadrado da  distancia entre o ponto (x1,y1) e (x2,y2)
     * 
     */
    public static int distancia(int x1, int y1, int x2, int y2){ // check
        return (int)(Math.pow((x1-x2),2) + Math.pow((y1-y2),2));
    }

    /**
     * Calcula a distancia entre um ponto origem e todos os elementos na lista
     * @param origem ponto que estamos considerando origem
     * @param lista todos os outros pontos
     * @return o ponto mais perto
     */
    
     public static Ponto proximoPonto(Ponto origem, LinkedList<Ponto> lista){ //check
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
    
     private static void NearestNeighbourFirst(Ponto origem, LinkedList<Ponto> lista){
        if(lista.size()  == 0 ){
            return;
        }
        Ponto px = proximoPonto(origem, lista);
        candidato.add(px);
        px.posicao = candidato.size()-1;
        NearestNeighbourFirst(px, lista);
    }

    public static void NearestNeighbourFirst(LinkedList<Ponto> lista){
        int posicao = rand(lista.size()-1, 0);
        Ponto origem = lista.remove(posicao);
        candidato.add(origem);
        origem.posicao = 0;
        NearestNeighbourFirst(origem, lista);
    }
    
 
    /*---------- */

    /**
     * a: Gerar uma permutacao qualquer dos pontos;(Criei todas, basta escolher um aleatorio)
     * b: Aplicar a heuristica "nearest-neighbour first"
     * @param flag 'a' se for a letra A e 'b' para a letra b
     */
    public static void q2(String flag){
        int N = in.nextInt();       
        LinkedList<Ponto> a = le_pontos(N);
        LinkedList<Ponto> permutacao;
        
        if(flag.equals("a")){
            permutacao = criaPermutacoes(new LinkedList<Ponto>(),a);
            imprimePermutacao(permutacao);
        }else{
            NearestNeighbourFirst(a);
            System.out.println(candidato.size());
            candidatoArray = candidato.toArray(new Ponto[candidato.size()]);
        }
    }
    
    /*-------------------Questão3--------------------*/
   
    /**
     * Produto interno entre os pontos
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    static public int prod_int(int x1,int y1, int x2, int y2){
        return (x1*y2 - x2*y1);
    }
    
    /**
     * Função retirada do slide compartilhado pela professora para verificar se  2 retas se cruzam
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return
     */
    static public boolean SegmentsInt(Ponto p1,Ponto p2,Ponto p3,Ponto p4){
        int d123 = prod_int((p3.X-p1.X),(p3.Y-p1.Y), (p2.X-p1.X), (p2.Y-p1.Y));
        int d124 = prod_int((p4.X-p1.X),(p4.Y-p1.Y), (p2.X-p1.X), (p2.Y-p1.Y));
        int d341 = prod_int((p1.X-p3.X),(p1.Y-p3.Y), (p4.X-p3.X), (p4.Y-p3.Y));
        int d342 = prod_int((p2.X-p3.X),(p2.Y-p3.Y), (p4.X-p3.X), (p4.Y-p3.Y));
        
        if((d123 * d124 < 0) && (d341 * d342 < 0)) return true;
        else if( (d123 == 0) && InBox(p1,p2,p3)) return true;
        else if( (d124 == 0) && InBox(p1,p2,p4)) return true;
        else if( (d341 == 0) && InBox(p3,p4,p1)) return true;
        else if( (d342 == 0) && InBox(p3,p4,p2)) return true;
        else return false;
    }
    
    static public boolean InBox (Ponto p1,Ponto p2,Ponto p3) {
        return ((Math.min(p1.X, p2.X)<= p3.X) && (p3.X<= Math.max(p1.X, p2.X))) && ((Math.min(p1.Y, p2.Y) <= p3.Y) && (p3.Y <= Math.max(p1.Y, p2.Y)));
    }

    static public void exchange(int p1, int p2, Ponto[] candidato){
        Ponto aux = new Ponto(candidato[p2].X,candidato[p2].Y);
        aux.conflitos = candidato[p2].conflitos;
        aux.posicao = p1;
        aux.lista_de_conflitos = new TreeSet<Ponto>(candidato[p2].lista_de_conflitos);
        
        candidato[p2] = candidato[p1];
        candidato[p2].posicao = p2;

        candidato[p1] = aux;
        
    }

    static public int  localiza_cruzamentos(int P ,int N, Ponto[] candidato){
        int count = 0;
        int vizinhoO;
        int vizinhoA;
        int analise;

        if(P == N-1){
            analise = 1;
            vizinhoO = 0;
        }else if(P == N-2){
            analise =  0;
            vizinhoO = P+1;
        }else{
            analise = P+2;
            vizinhoO = P+1;
        }

        for(int i = 0 ; i < N-3 ; i++){
            if(analise == N-1) vizinhoA = 0;
            else vizinhoA = analise+1;
            if(SegmentsInt(candidato[P],candidato[vizinhoO],candidato[analise],candidato[vizinhoA])){
                count++;
                candidato[P].lista_de_conflitos.add(candidato[analise]);
            }
            analise++;
            if(analise >= N) analise = 0;
        }
        candidato[P].conflitos = count;
        return count;
    }

    static public int  quantidade_cruzamentos(int P ,int N, Ponto[] candidato){
        int count = 0;
        int vizinhoO;
        int vizinhoA;
        int analise;

        if(P == N-1){
            analise = 1;
            vizinhoO = 0;
        }else if(P == N-2){
            analise =  0;
            vizinhoO = P+1;
        }else{
            analise = P+2;
            vizinhoO = P+1;
        }

        for(int i = 0 ; i < N-3 ; i++){
            if(analise == N-1) vizinhoA = 0;
            else vizinhoA = analise+1;
            if(SegmentsInt(candidato[P],candidato[vizinhoO],candidato[analise],candidato[vizinhoA])){
                count++;
            }
            analise++;
            if(analise >= N) analise = 0;
        }
        candidato[P].conflitos = count;
        return count;
    }

    public static void q3() {
        int N = in.nextInt();       
        LinkedList<Ponto> a = le_pontos(N);
        NearestNeighbourFirst(a); //Caminho formado
        candidatoArray = candidato.toArray(new Ponto[candidato.size()]);
        System.out.println(Arrays.toString(candidatoArray));

        Ponto[] px = proximo(candidatoArray, 'c');
        System.out.println(Arrays.toString(px));
        
    }
 
    /*-------------------Questão4--------------------*/
    /**
     * 
     * @param f flag para a questão. a,b,c,d.
     */
     public static void hill_Climbing(char f, Ponto[] candidato ){ // Recebe como 
        Ponto[] atual = new Ponto[candidato.length];
        System.arraycopy(candidato, 0, atual, 0, candidato.length);
        while(true){
            Ponto[] Vizinho = proximo(atual,f);
            System.out.println("Atual: " + Arrays.toString(atual) + " Vizinho: " + Arrays.toString(Vizinho));
            System.out.println(Qconflitos(atual) + " " + Qconflitos(Vizinho) );
            if(Qconflitos(Vizinho) >= Qconflitos(atual)){
                System.out.println("Fim");
                return;
            }
            System.arraycopy(Vizinho, 0, atual, 0, candidato.length);

        }
    }

    public static int Qconflitos(Ponto[] atual){//so retorna quantidade d conflitos
        int N = atual.length;
        int n = 0; //local da origem
        int count = 0;
        for(int i = 0 ; i < N ; i++){
            count += quantidade_cruzamentos(n,N, atual);
            n++;
        }
        return count;
    }


    public static Ponto[] proximo(Ponto[] atual, char flag){
        Ponto[] atualCopia = new Ponto[atual.length];
        System.arraycopy(atual, 0, atualCopia, 0, atual.length);
        int menor = 1000000;
        int menorPonto = -1;
        int N = atualCopia.length;
        int n = 0; //local da origem
        for(int i = 0 ; i < N ; i++){
            int count = localiza_cruzamentos(n,N, atualCopia);
            if(flag == 'b' && count > 0){
                menor = count;
                menorPonto = n;
                break;
            }
            if(count < menor && count > 0){
                menor = count;
                menorPonto = n;
            }
            n++;
        }
        
        if(menorPonto == -1){
            System.out.println("igual");
            return atualCopia;
        }
        //System.out.println(menorPonto+1 + " " + atualCopia[menorPonto].lista_de_conflitos.first().posicao);
        if(menorPonto == N-1){
            exchange(0, atualCopia[menorPonto].lista_de_conflitos.pollFirst().posicao, atualCopia);
        }else exchange(menorPonto+1, atualCopia[menorPonto].lista_de_conflitos.pollFirst().posicao, atualCopia);
        return atualCopia;
        
    }

    public static void q4(char f){
        int N = in.nextInt();       
        LinkedList<Ponto> a = le_pontos(N);
        NearestNeighbourFirst(a); //Caminho formado
        candidatoArray = candidato.toArray(new Ponto[candidato.size()]);

        hill_Climbing(f,candidatoArray);
    }

    public static void main(String[] args){
        //q1();
        //q2("b"); // flag a = questão 2a, flag b = questão 2b
        //q3();
        q4('b');
    }
}

// javac Main.java && java Main < t.txt 
// javac Main.java && java Main < pontos.txt 