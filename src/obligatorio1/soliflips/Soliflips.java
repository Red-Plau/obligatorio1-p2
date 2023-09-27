package obligatorio1.soliflips;

import java.util.Random;
import java.util.Scanner;

public class Soliflips {
    
    public static void SetUp() {
        Scanner in = new Scanner(System.in);
        System.out.println("Desea jugar?");
        String ent = in.nextLine();
        if (ent.equalsIgnoreCase("SI")){
            System.out.println("a) Tomar los datos del archivo \"datos.txt\"");
            System.out.println("b) Usar el tablero predefinido");
            System.out.println("c) Usar un tablero al azar");
            
            ent = in.nextLine();
            if (ent.equalsIgnoreCase("a")){
                //SIScanner input = new Scanner(new File(".\\Test\\datos.txt")); 
            }
            else if (ent.equalsIgnoreCase("b")){
               // Tableros.predefinido();
            }
            else if (ent.equalsIgnoreCase("c")){
                //crear tabla con matriz[fila][columna] random
                System.out.println("Ingrese cantidad de filas (entre 3 y 9)");
                int filas = in.nextInt();
                System.out.println("Ingrese cantidad de columnas (entre 3 y 9)");
                int col = in.nextInt();
                System.out.println("Ingrese nivel de dificultad (entre 1 y 8)");
                int nivel = in.nextInt();
                
                String[][] tabla = randomMat(filas, col, nivel);
                crearTabla(tabla);
            }
        }
    }
    
    public static void crearTabla(String[][] mat){
        int filas = mat.length;
        int columnas = mat[0].length;
                
        for (int i = -1; i < filas; i++){
            for (int j = -1; j < columnas; j++){
                if (i == -1){
                    if (j == -1){
                        System.out.print("   ");
                    } else {
                        System.out.print(" " + (j+1) + " ");
                    }
                    System.out.print(" ");
                } else {
                    if (j == -1){
                        System.out.print(" " + (i+1) + " ");
                    } else {
                        //if letrita == R ...... (en rojo)
                        //else ..... (en azul)
                        System.out.print("| " + mat[i][j] + " ");
                    }
                }
            }
            
            if (i == -1){
                System.out.println();
            } else {
                System.out.println("|");
            }
            
            for (int j = -1; j < columnas; j++){
                if (j == -1){
                    System.out.print("   ");
                } else {
                    System.out.print("+---");
                }
            }
            System.out.println("+");
        }
    }
    
    public static String[][] randomMat(int filas, int col, int nivel){
        //String RED = "\u001B[31m";
        String BLUE = "\u001B[34m";
        String RESET = "\033[0m";
        String[][] res = new String[filas][col];
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < col; j++){
                res[i][j] = BLUE + String.valueOf(random()) + RESET;
            }
        }
        
        for (int i = 0; i < nivel; i++) {
            Random r = new Random();
            int filaR = r.nextInt(filas);
            int colR = r.nextInt(col);
            String sym = res[filaR][colR];
            System.out.println(filaR+1 + ", " + (colR+1));
            if (sym.contains("-")){
                for (int j = 0; j < col; j++){
                    if (res[filaR][j].contains("\u001B[34m")){
                        String cambio = res[filaR][j].replace("\u001B[34m", "\u001B[31m");
                        res[filaR][j] = cambio;
                    }else{
                        String cambio = res[filaR][j].replace("\u001B[31m", "\u001B[34m");
                        res[filaR][j] = cambio;
                    }
                }
            }else if (sym.contains("|")){
                for (int j = 0; j < filas; j++){
                    if (res[j][colR].contains("\u001B[34m")){
                        String cambio = res[j][colR].replace("\u001B[34m", "\u001B[31m");
                        res[j][colR] = cambio;
                    }else{
                        String cambio = res[j][colR].replace("\u001B[31m", "\u001B[34m");
                        res[j][colR] = cambio;
                    }
                }
            }
        }
        
        return res;
    }
        
                
                    
    
    public static char random(){
        double value = Math.random();
        char res;
        if (value < 0.25){
            res = '/';
        }else if (value < 0.50){
            res = '-';
        }else if (value < 0.75){
            res = '\\';
        }else{
            res = '|';
        }
        return res;
    }
    
    public static void main(String[] args) {
       SetUp();
    }
    
}

