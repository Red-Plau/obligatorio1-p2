package Soliflips;

import java.util.ArrayList;
import java.util.Iterator;

public final class Juego {
    private ArrayList<Tablero> historialTableros;
    private ArrayList<int[]> historialMovimientos;
    
    public void addHistorialTableros(Tablero unTablero){
        if (historialTableros == null) {
            historialTableros = new ArrayList();
        }
        historialTableros.add(unTablero);
    }
    
    public ArrayList<Tablero> getAllTableros(){
        return this.historialTableros;
    }
    
    public void deleteLastTablero(){
        historialTableros.remove(historialTableros.size() - 1);
    }
    
    public Tablero getLastTablero(){
        return historialTableros.get(historialTableros.size() - 1);
    }
    
    public void addHistorialMovimiento(int[] unMovimiento){
        if (historialMovimientos == null) {
            historialMovimientos = new ArrayList();
        }
        historialMovimientos.add(unMovimiento);
    }
    
    public void deleteLastMovimiento(){
        if (historialMovimientos != null) {
            if (historialMovimientos.isEmpty()) {
                //nada
            } else {
                historialMovimientos.remove(historialMovimientos.size() - 1);
            }
        }
    }
    
    public int[] getLastMovimiento(){
        return historialMovimientos.get(historialMovimientos.size() - 1);
    }
    
    public ArrayList<int[]> getAllMovimiento(){
        return historialMovimientos;
    }
    
    public Juego(Tablero primerTablero){
        this.addHistorialTableros(primerTablero);
        
    }

    public boolean seGano(){
        String[][] tabla = this.getLastTablero().getTabla();
        boolean res = true;               
        String primerColor;
        if (tabla[0][0].contains("\u001B[34m")){
            primerColor = "\u001B[34m";
        } else {
            primerColor = "\u001B[31m";
        }
        for (int i = 0; i < tabla.length && res; i++) {
            for (int j = 0; j < tabla[0].length && res; j++) {
                if (!tabla[i][j].contains(primerColor)) {
                    res = false;
                }
            }
        }
        return res;
    }
      
    public String[] movimientostoString() {
        ArrayList<int[]> hist = this.getAllMovimiento();
        String[] str = new String[hist.size()];
        for (int i = 0; i < hist.size(); i++) {            
            int[] mov = hist.get(i);
            str[i] = ("(" + mov[0] + ", " + mov[1] + ")");
        }
        return str;
    }
}
        
        
        

