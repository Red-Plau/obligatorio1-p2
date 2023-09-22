package obligatorio1.soliflips;

public class Soliflips {
    
    public static void SetUp(int filas, int columnas) {
        //fgjjf
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
                        System.out.print("| " + j + " ");
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
    
    public static void main(String[] args) {
        SetUp(3, 4);
    }
    
}
