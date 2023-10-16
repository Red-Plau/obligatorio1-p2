//AUTORES:
//  Laura Soumastre [317667]
//  Marcos Eppinger [203654]

package Soliflips;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Tablero {
    private String[][] tabla;
    private ArrayList<int[]> solucion;
    private int nivel;
    
    public void setTabla(String[][] unaTabla) {
        tabla = unaTabla;
    }
    
    public String[][] getTabla() {
        return this.tabla;
    }
    
    public void setSolucion(ArrayList<int[]> array){
        solucion = array;
    }
    
    public ArrayList<int[]> getSolucion() {
        return solucion;
    }
    
    public void setNivel(int unNivel) {
        nivel = unNivel;
    }
    
    public int getNivel() {
        return nivel;
    }
        
    public Tablero(){
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
        ArrayList<int[]> coso = new ArrayList<>();
        coso.add(new int[]{5, 6});
        coso.add(new int[]{4, 4});
        coso.add(new int[]{2, 4});
        solucion = coso;
    }
    
    public Tablero(String[][] unaTabla, int unNivel){
        tabla = unaTabla;
        nivel = unNivel;
    }
    
    public Tablero(int cantFilas, int cantColumnas, int unNivel) {
        tabla = randomTabla(cantFilas, cantColumnas, unNivel);
        //solucion = new int[unNivel]; //en random agregar array de solucion
        nivel = unNivel;
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
    
    public String[][] cambiarTabla(int[] mov){
        String[][] res = new String[tabla.length][tabla[0].length];
        for (int i = 0; i < tabla.length; i++) {
            System.arraycopy(tabla[i], 0, res[i], 0, tabla[i].length);
        }
        int filas = res.length;
        int col = res[0].length;
        int filaC = mov[0]-1;
        int colC = mov[1]-1;
        System.out.println(filaC+1 + ", " + (colC+1));
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
    
    private String[][] randomTabla(int filas, int col, int nivel){        
        String BLUE = "\u001B[34m";
        String RESET = "\033[0m";
        String[][] res = new String[filas][col];
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < col; j++){
                res[i][j] = BLUE + String.valueOf(random()) + RESET;               
            }
        }
        Set<String> coordenadasUsadas = new HashSet<>();
        ArrayList<int[]> listaRandoms = new ArrayList<>();
        Random r = new Random();
        
        for (int i = 0; i < nivel; i++) {            
            int filaR;
            int colR;
            String coordenada;
            do {
                filaR = r.nextInt(filas);
                colR = r.nextInt(col);
                coordenada = filaR + "," + colR;
            } while (coordenadasUsadas.contains(coordenada));
            
            coordenadasUsadas.add(coordenada);
            
            int[] movimientoR = {filaR+1, colR+1}; 
            listaRandoms.add(movimientoR);
            this.setSolucion(listaRandoms);
            
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
}