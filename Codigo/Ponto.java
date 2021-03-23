import java.util.TreeSet;
public class Ponto implements Comparable<Ponto>{
    int X;
    int Y;
    int conflitos;
    int posicao;
    TreeSet<Ponto> lista_de_conflitos;
    Ponto(){
        lista_de_conflitos = new TreeSet<Ponto>();
    }
    Ponto(int X, int Y){
        this.X = X;
        this.Y = Y;
        lista_de_conflitos = new TreeSet<Ponto>();
    }

    public String toString(){
        return "(" + X + "," + Y + ")";
    }

    @Override
    public int compareTo(Ponto p){
        if(conflitos < p.conflitos) return -1;
        else return 1;
    }
    

}

//javac Ponto.java