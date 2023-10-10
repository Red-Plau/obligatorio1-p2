package obligatorio1.soliflips;

import java.util.ArrayList;

public class Juego {
    private ArrayList<Tablero> historialTableros;
    private ArrayList<int[]> historialMovimientos;
    
    public void addHistorialTableros(Tablero unTablero){
        historialTableros.add(unTablero);
    }
    
    public void deleteLastTablero(){
        historialTableros.remove(historialTableros.size() - 1);
    }
    
    public Tablero getLastTablero(){
        return historialTableros.get(historialTableros.size() - 1);
    }
    
    public void addHistorialMovimiento(int[] unMovimiento){
        historialMovimientos.add(unMovimiento);
    }
    
    public void deleteLastMovimiento(){
        historialMovimientos.remove(historialMovimientos.size() - 1);
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
        for (int i = 0; i < tabla.length && !res; i++) {
            for (int j = 0; j < tabla[0].length && !res; j++) {
                if (!tabla[i][j].contains(primerColor)) {
                    res = false;
                }
            }
        }
        return res;
    }
}
        
        
        

