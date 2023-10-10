package obligatorio1.soliflips;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//public class Ficha{
//    private char ficha;
//    private String color;
//    
//    public void setFicha (char unaFicha){
//        ficha = unaFicha;
//    }
//    
//    public char getFicha (){
//        return ficha;
//    }
//    
//    public void setColor (String unColor){
//        color = unColor;
//    }
//    
//    public String getColor (){
//        return color;
//    }
//    
//    public static char random(){
//        double value = Math.random();
//        char res;
//        if (value < 0.25){
//            res = '/';
//        }else if (value < 0.50){
//            res = '-';
//        }else if (value < 0.75){
//            res = '\\';
//        }else{
//            res = '|';
//        }
//        return res;
//    }
//}

public class Soliflips {
    private static Tablero tableroInicial;
    
    public static void SetUp() {
        Scanner in = new Scanner(System.in);
        System.out.println("Desea jugar?");
        String ent = in.nextLine();
        if (ent.equalsIgnoreCase("SI")){
            System.out.println("a) Tomar los datos del archivo \"datos.txt\"");
            System.out.println("b) Usar el tablero predefinido");
            System.out.println("c) Usar un tablero al azar");
            
            ent = in.nextLine();
            if ((ent.equalsIgnoreCase("a")) || (ent.equalsIgnoreCase("b")) || (ent.equalsIgnoreCase("c"))){
                String[][] tabla = null;
                int nivel = 0;
                
                if (ent.equalsIgnoreCase("a")){
                    try {
                        leerTxt();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Soliflips.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (ent.equalsIgnoreCase("b")){                   
                    String R = "\u001B[31m";
                    String B = "\u001B[34m";
                    String Rs = "\033[0m";

                    String[][] predef = {
                        {B + "|" + Rs, B + "|" + Rs, R + "-" + Rs, B + "/" + Rs, R + "|" + Rs, R + "-" + Rs},
                        {R + "-" + Rs, B + "/" + Rs, B + "/" + Rs, B + "|" + Rs, R + "-" + Rs, R + "-" + Rs},
                        {R + "-" + Rs, R + "-" + Rs, B + "|" + Rs, R + "-" + Rs, R + "/" + Rs, R + "-" + Rs},
                        {R + "\\" + Rs, R + "-" + Rs, R + "|" + Rs, R + "\\" + Rs, B + "|" + Rs, R + "|" + Rs},
                        {R + "\\" + Rs, R + "/" + Rs, R + "/" + Rs, B + "|" + Rs, B + "/" + Rs, B + "\\" + Rs}
                    };

                    tabla = predef;
                    nivel = 3;
                }
                else {
                    //crear tabla con matriz[fila][columna] random
                    System.out.println("Ingrese cantidad de filas (entre 3 y 9)");
                    int filas = in.nextInt();
                    System.out.println("Ingrese cantidad de columnas (entre 3 y 9)");
                    int col = in.nextInt();
                    System.out.println("Ingrese nivel de dificultad (entre 1 y 8)");
                    nivel = in.nextInt();

                    tabla = randomTabla(filas, col, nivel);
                }
                
                crearTablero(tabla);
                Tabla tablaInicial = new Tabla(tabla);
                Tablero tableroInicial = new Tablero(tablaInicial, nivel);
                //tableroInicial.setTabla(tabla);
                Juego empezarJuego = new Juego(tableroInicial);
                Jugar(empezarJuego);
            
            } else {
                   
            }
        }
    }
    
    public static boolean seGano() {
        //func
        return false;
    }
    
    public static void Jugar(Juego controlJuego){
        Scanner input = new Scanner(System.in);
        
        while (!seGano()){
            int[] movimiento = new int[2];
            movimiento[0] = input.nextInt();            
            
            if(movimiento[0] != -1){
                movimiento[1] = input.nextInt();
                
                controlJuego.addHistorialMovimiento(movimiento);
                
                //String[][] cambiada = cambiarTabla(tableroInicial.getTabla(), movimiento);
                Tabla cambiada = tableroInicial.getTabla().cambiarTabla(tableroInicial.getTabla(), movimiento);
                
                crearTableroConCambio(ksafsakfjh, cambiada.getSTabla());
                
                //sigue el juego
            } else {
                if(movimiento[0] == -1){
                    controlJuego.deleteLastTablero();
                    controlJuego.deleteLastMovimiento();
                    
                    crearTablero(controlJuego.getLastTablero().getTabla());
                }
            }
        }
    }
    
    public static String[][] cambiarTabla(String[][] actual, int[] mov){
        //String[][] res = new String[actual.length][actual[0].length];
        String[][] res = actual;
        int filas = actual.length;
        int col = actual[0].length;
        int filaC = mov[0];
        int colC = mov[1];
        String sym = actual[filaC][colC];
        
        if (sym.contains("-")){
            for (int j = 0; j < col; j++) {
                if (res[filaC][j].contains("\u001B[34m")){
                    String cambio = res[filaC][j].replace("\u001B[34m", "\u001B[31m");
                    res[filaC][j] = cambio;
                }else{
                    String cambio = res[filaC][j].replace("\u001B[31m", "\u001B[34m");
                    res[filaC][j] = cambio;
                }
            }
        }else if (sym.contains("|")) {
            for (int j = 0; j < filas; j++){
                if (res[j][colC].contains("\u001B[34m")){
                    String cambio = res[j][colC].replace("\u001B[34m", "\u001B[31m");
                    res[j][colC] = cambio;
                }else{
                    String cambio = res[j][colC].replace("\u001B[31m", "\u001B[34m");
                    res[j][colC] = cambio;
                }
            }
        }else if (sym.contains("/")) {
            int y = filaC;
            for (int j = colC; j <= col-1 && y >= 0; j++) {
                if (res[y][j].contains("\u001B[34m")) {
                    String cambio = res[y][j].replace("\u001B[34m", "\u001B[31m");
                    res[y][j] = cambio;
                    y--;
                } else {
                    String cambio = res[y][j].replace("\u001B[31m", "\u001B[34m");
                    res[y][j] = cambio;
                    y--;
                }
            }
            y = filaC+1;
            for (int j = colC-1; j >= 0 && y <= filas-1; j--) {
                if (res[y][j].contains("\u001B[34m")) {
                    String cambio = res[y][j].replace("\u001B[34m", "\u001B[31m");
                    res[y][j] = cambio;
                    y++;
                } else {
                    String cambio = res[y][j].replace("\u001B[31m", "\u001B[34m");
                    res[y][j] = cambio;
                    y++;
                }
            }
        } else {
            int y = filaC;
            for (int j = colC; j >= 0 && y >= 0; j--) {
                if (res[y][j].contains("\u001B[34m")) {
                    String cambio = res[y][j].replace("\u001B[34m", "\u001B[31m");
                    res[y][j] = cambio;
                    y--;
                } else {
                    String cambio = res[y][j].replace("\u001B[31m", "\u001B[34m");
                    res[y][j] = cambio;
                    y--;
                }
            }
            y = filaC+1;
            for (int j = colC+1; j <= col-1 && y <= filas-1; j++) {
                if (res[y][j].contains("\u001B[34m")) {
                    String cambio = res[y][j].replace("\u001B[34m", "\u001B[31m");
                    res[y][j] = cambio;
                    y++;
                } else {
                    String cambio = res[y][j].replace("\u001B[31m", "\u001B[34m");
                    res[y][j] = cambio;
                    y++;
                }
            }
        }
        return res;
    }
    
    public static void leerTxt() throws FileNotFoundException{
        
        try {
            String Rs = "\033[0m";
            File file = new File(".\\Test\\datos.txt");
            Scanner in = new Scanner(file);
            int m = in.nextInt();
            int n = in.nextInt();           
            in.nextLine();
            String[][] mat = new String[m][n];
            for (int i = 0; i < m; i++) {
                String fila = in.nextLine();
                String[] lista = fila.split(" ");
                for (int j = 0; j < n; j++) {
                    String color = obtenerCodigoColor(lista[j].substring(1));
                    String resto = (lista[j].substring(0, 1));
                    mat[i][j] = color + resto + Rs;
                }
            }
            int nivel = in.nextInt();
            in.nextLine();
                   
                      
            in.close();    
            crearTablero(mat);  
            } catch (FileNotFoundException e) {
                System.out.println("Error.");
        }
    }
    
    public static String obtenerCodigoColor(String codigo) {
        return switch (codigo) {
            case "R" -> "\u001B[31m";
            case "A" -> "\u001B[34m";
            default -> "";
        };
    }
    
    public static void crearTablero(String[][] unaTabla){
        int filas = unaTabla.length;
        int columnas = unaTabla[0].length;
                
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
                        System.out.print("| " + unaTabla[i][j] + " ");
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
    
    public static void crearTableroConCambio(String[][] unaTablaSinCambio, String[][] unaTablaConCambio) {
      int filas = unaTablaSinCambio.length;
      int columnas = unaTablaSinCambio[0].length * 2; //como tenemos que imprimir dos matrices, van a haber doble la cantidad de columnas
                
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
                        System.out.print("| " + unaTablaSinCambio[i][j] + " ");
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
    
    public static String[][] randomTabla(int filas, int col, int nivel){
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
                for (int j = 0; j < col; j++) {
                    if (res[filaR][j].contains("\u001B[34m")){
                        String cambio = res[filaR][j].replace("\u001B[34m", "\u001B[31m");
                        res[filaR][j] = cambio;
                    }else{
                        String cambio = res[filaR][j].replace("\u001B[31m", "\u001B[34m");
                        res[filaR][j] = cambio;
                    }
                }
            }else if (sym.contains("|")) {
                for (int j = 0; j < filas; j++){
                    if (res[j][colR].contains("\u001B[34m")){
                        String cambio = res[j][colR].replace("\u001B[34m", "\u001B[31m");
                        res[j][colR] = cambio;
                    }else{
                        String cambio = res[j][colR].replace("\u001B[31m", "\u001B[34m");
                        res[j][colR] = cambio;
                    }
                }
            }else if (sym.contains("/")) {
                int y = filaR;
                for (int j = colR; j <= col-1 && y >= 0; j++) {
                    if (res[y][j].contains("\u001B[34m")) {
                        String cambio = res[y][j].replace("\u001B[34m", "\u001B[31m");
                        res[y][j] = cambio;
                        y--;
                    } else {
                        String cambio = res[y][j].replace("\u001B[31m", "\u001B[34m");
                        res[y][j] = cambio;
                        y--;
                    }
                }
                y = filaR+1;
                for (int j = colR-1; j >= 0 && y <= filas-1; j--) {
                    if (res[y][j].contains("\u001B[34m")) {
                        String cambio = res[y][j].replace("\u001B[34m", "\u001B[31m");
                        res[y][j] = cambio;
                        y++;
                    } else {
                        String cambio = res[y][j].replace("\u001B[31m", "\u001B[34m");
                        res[y][j] = cambio;
                        y++;
                    }
                }
            } else {
                int y = filaR;
                for (int j = colR; j >= 0 && y >= 0; j--) {
                    if (res[y][j].contains("\u001B[34m")) {
                        String cambio = res[y][j].replace("\u001B[34m", "\u001B[31m");
                        res[y][j] = cambio;
                        y--;
                    } else {
                        String cambio = res[y][j].replace("\u001B[31m", "\u001B[34m");
                        res[y][j] = cambio;
                        y--;
                    }
                }
                y = filaR+1;
                for (int j = colR+1; j <= col-1 && y <= filas-1; j++) {
                    if (res[y][j].contains("\u001B[34m")) {
                        String cambio = res[y][j].replace("\u001B[34m", "\u001B[31m");
                        res[y][j] = cambio;
                        y++;
                    } else {
                        String cambio = res[y][j].replace("\u001B[31m", "\u001B[34m");
                        res[y][j] = cambio;
                        y++;
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
    
}
