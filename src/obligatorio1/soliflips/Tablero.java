package obligatorio1.soliflips;

public class Tablero {
    private String[][] tabla;
    private int[] solucion;
    private int nivel;
    //agregar arraylist de movimientos
    
    public void setTabla(String[][] unaTabla) {
        tabla = unaTabla;
    }
    
    public String[][] getTabla() {
        return tabla;
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
        tabla = new String[0][0];
        solucion = new int[0];
        nivel = 0;
    }
    
    public Tablero(int cantFilas, int cantColumnas, int unNivel) {
        tabla = new String[cantFilas][cantColumnas];
        solucion = new int[unNivel]; //en random agregar array de solucion
        nivel = unNivel;
    }
}