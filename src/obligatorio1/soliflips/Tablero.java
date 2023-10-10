package obligatorio1.soliflips;

import java.util.Random;

public class Tablero {
    private String[][] tabla;
    private int[] solucion;
    private int nivel;
    //agregar arraylist de movimientos
    
    public void setTabla(String[][] unaTabla) {
        tabla = unaTabla;
    }
    
    public String[][] getTabla() {
        return this.tabla;
    }
    
    public void setSolucion(int[] unaSolucion) {
        solucion = unaSolucion;
    }
    
    public int[] getSolucion() {
        return solucion;
    }
    
    public void setNivel(int unNivel) {
        nivel = unNivel;
    }
    
    public int getNivel() {
        return nivel;
    }
    
    public Tablero() {
        //tabla = new String[0][0];
        solucion = new int[0];
        nivel = 0;
    }
    
    public Tablero(String[][] unaTabla, int unNivel){
        tabla = unaTabla;
        nivel = unNivel;
    }
    
    public Tablero(int cantFilas, int cantColumnas, int unNivel) {
        //tabla = new Tabla[cantFilas][cantColumnas];
        solucion = new int[unNivel]; //en random agregar array de solucion
        nivel = unNivel;
    }
    
    public String[][] cambiarTabla(int[] mov){
        String[][] res = this.getTabla();
        int filas = res.length;
        int col = res[0].length;
        int filaC = mov[0];
        int colC = mov[1];
        String sym = res[filaC][colC];
        
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