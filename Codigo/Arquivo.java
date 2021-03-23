import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
class Arquivo{

    public void escreveCoordenada(Ponto[] arrayPontos){
        
        try {
            FileWriter myWriter = new FileWriter("py_input.txt");
            myWriter.write((arrayPontos.length + 1) + "\n");
            for(int i = 0 ; i < arrayPontos.length ; i++){
                myWriter.write(arrayPontos[i].X + " " + arrayPontos[i].Y + "\n");
            }
            myWriter.write(arrayPontos[0].X + " " + arrayPontos[0].Y + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}