package obligatorio1.soliflips;

import java.util.ArrayList;

public class Juego {
    private ArrayList<Tablero> historialTableros;
    private ArrayList<int[]> historialMovimientos;
    
    private void addHistorialTableros(Tablero unTablero){
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
}

