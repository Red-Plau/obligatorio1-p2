package obligatorio1.soliflips;

import java.util.Scanner;

public class Soliflips {
    
    public static void SetUp(int filas, int columnas) {
        String RED = "\u001B[31m";
        Scanner in = new Scanner(System.in);
        System.out.println("Desea jugar?");
        String ent = in.nextLine();
        if (ent.equalsIgnoreCase("SI")){
            System.out.println(RED + "a) Tomar los datos del archivo \"datos.txt\"");
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
            }
        }
    }
    
    public static void crearTabla(int[][] mat){
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
        SetUp(3, 4);
    }
    
}

