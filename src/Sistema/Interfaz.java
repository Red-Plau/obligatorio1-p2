//AUTORES:
//  Laura Soumastre [317667]
//  Marcos Eppinger [203654]

package Sistema;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import Soliflips.Juego;
import Soliflips.Tablero;
import java.util.ArrayList;

public class Interfaz {
    public static void SetUp() {
        Scanner in = new Scanner(System.in);
        
        System.out.println("""
                           MODO DE JUEGO:
                            a) Tomar los datos del archivo \"datos.txt\"
                            b) Usar el tablero predefinido
                            c) Usar un tablero al azar""");

        String ent = in.nextLine();
        while (!(ent.equalsIgnoreCase("a")) && !(ent.equalsIgnoreCase("b")) && !(ent.equalsIgnoreCase("c"))){
            System.out.println("* Por favor ingrese una de las opciones indicadas (A / B / C)");
            ent = in.nextLine();
        }
        
        int nivel;
        Tablero tableroInicial = null;
        
        if (ent.equalsIgnoreCase("a")){
            try {
                tableroInicial = leerTxt();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (ent.equalsIgnoreCase("b")){                   

            tableroInicial = new Tablero();                    
        } else {
            System.out.println("Ingrese cantidad de filas (entre 3 y 9)");
            int filas = in.nextInt();
            while (filas < 3 || filas > 9){
                System.out.println("* Por favor ingrese un numero entre 3 y 9.");
                filas = in.nextInt();
            }
            
            System.out.println("Ingrese cantidad de columnas (entre 3 y 9)");
            int col = in.nextInt();
            while (col < 3 || col > 9){
                System.out.println("* Por favor ingrese un numero entre 3 y 9.");
                col = in.nextInt();
            }
            
            System.out.println("Ingrese nivel de dificultad (entre 1 y 8)");
            nivel = in.nextInt();
            while (nivel < 1 || nivel > 8){
                System.out.println("* Por favor ingrese un numero entre 1 y 8.");
                nivel = in.nextInt();
            }

            tableroInicial = new Tablero(filas, col, nivel);                    
        }
        
        mostrarTablero(tableroInicial.getTabla());
        
        Juego empezarJuego = new Juego(tableroInicial);
        Jugar(empezarJuego);
    }
    
    public static void Jugar(Juego controlJuego){
        Scanner input = new Scanner(System.in);
        //Empezamos a contar el tiempo desde el comienzo de la partida.
        long tiempoInicio = System.nanoTime();
        
        int nivel = controlJuego.getLastTablero().getNivel();
        Boolean x = false; //Controla si el jugador eligio la opcion X.
        while (!(controlJuego.seGano() || x)){
            
            String inputStr = input.next();
            
            //Mientras la entrada sea de formato invalido, se repitira que re-ingrese los datos.
            while (!(esNumero(inputStr) 
                    || (!esNumero(inputStr) && (inputStr.equalsIgnoreCase("H") || inputStr.equalsIgnoreCase("S") || inputStr.equalsIgnoreCase("X"))))){
                System.out.println("""
                    INVALIDO. Por favor ingrese:
                       > S para ver una posible solucion
                       > H para ver un historial de sus movimientos
                       > X para terminar el juego
                       > -1 -1 si quiere retroceder
                       > dos coordenadas dentro del rango del tamaño de la tabla""");
                
                inputStr = input.next();
            }
            
            if (inputStr.equalsIgnoreCase("H")) {
                //SI HAY MOVIMIENTOS EN EL HISTORIAL, los imprime todos.
                if (controlJuego.getAllMovimientos() != null) {
                    String[] movs = controlJuego.movimientosToString();

                    for (String mov : movs) {
                        System.out.println(mov);
                    }
                }
            } else if (inputStr.equalsIgnoreCase("S")){
                //SI HAY MOVIMIENTOS EN EL HISTORIAL:
                //Se imprime una solucion de manera que el jugador "retroceda" hasta el primer movimiento...
                if (controlJuego.getAllMovimientos() != null) {
                    for (int i = (controlJuego.getAllMovimientos().size() - 1); i >= 0; i--){
                        System.out.println("(" + (controlJuego.getAllMovimientos().get(i)[0]) + ", " + controlJuego.getAllMovimientos().get(i)[1] + ")");
                    }
                }
                
                //...y luego se imprime la solucion del tablero.
                for (int i = (controlJuego.getSolucionInicial().size() - 1); i >= 0 ; i--){
                    System.out.println("(" + controlJuego.getSolucionInicial().get(i)[0] + ", " + controlJuego.getSolucionInicial().get(i)[1] + ")");
                }
            } else if (inputStr.equalsIgnoreCase("X")){
                x = true;
                System.out.println("Adios!");
            } else {
                //Si se detecta una primera coordenada, armamos sistema de coordenadas.
                int[] movimiento = new int[2];
                movimiento[0] = Integer.parseInt(inputStr);
                
                inputStr = input.next();
                
                if (esNumero(inputStr)) {
                    //Si la siguiente "coordenada" es un numero, se aceptara como valor.
                    movimiento[1] = Integer.parseInt(inputStr);
                } else {
                    //En caso contrario, la segunda coordenada sera un numero invalido.
                    movimiento[1] = controlJuego.getLastTablero().getTabla()[0].length + 1;
                }
                
                //Mientras sean invalidos las entradas (menor o mayor al tamanio de la tabla o no (-1, -1)) se repitira que re-ingrese los datos.
                while (!(movimiento[0] == -1 && movimiento[1] == -1) &&
                        (movimiento[0] < 1 || movimiento[0] > controlJuego.getLastTablero().getTabla().length 
                        || movimiento[1] < 1 || movimiento[1] > controlJuego.getLastTablero().getTabla()[0].length)){
                    System.out.println("""
                                       INVALIDO. Por favor ingrese:
                                       > -1 -1 si quiere retroceder
                                       > Dos coordenadas dentro del rango de la tabla""");
                    
                    inputStr = input.next();
                    
                    if (esNumero(inputStr)) {
                        movimiento[0] = Integer.parseInt(inputStr);
                    } else {
                        movimiento[0] = controlJuego.getLastTablero().getTabla().length + 1;
                    }
                    
                    inputStr = input.next();
                    
                    if (esNumero(inputStr)) {
                        movimiento[1] = Integer.parseInt(inputStr);
                    } else {
                        movimiento[1] = controlJuego.getLastTablero().getTabla()[0].length + 1;
                    }
                }
                
                if(movimiento[0] != -1){
                    controlJuego.addHistorialMovimiento(movimiento);
                    Tablero tabActual = controlJuego.getLastTablero();

                    String[][] actual = tabActual.getTabla();
                    String[][] cambiada = tabActual.cambiarTabla(movimiento);

                    Tablero tabNuevo = new Tablero(cambiada, nivel);
                    mostrarTableroConCambio(actual, cambiada);

                    controlJuego.addHistorialTableros(tabNuevo);
                } else {
                    if (controlJuego.getAllTableros().size() == 1){
                        controlJuego.deleteLastMovimiento();
                        
                        mostrarTablero(controlJuego.getLastTablero().getTabla());
                    } else {
                        controlJuego.deleteLastTablero();
                        controlJuego.deleteLastMovimiento();
                        
                        mostrarTablero(controlJuego.getLastTablero().getTabla());   
                    }
                }
            }
        }
        
        if (!x) {
            long tiempoFin = System.nanoTime();
            long tiempoTotal = tiempoFin - tiempoInicio;
            
            //Convertir nanosegundos a milisegundos
            long milisegundos = tiempoTotal / 1000000;

            long horas = milisegundos / 3600000;
            long minutos = (milisegundos % 3600000) / 60000;
            long segundos = (milisegundos % 60000) / 1000;
            long milisegundosRestantes = milisegundos % 1000;
            
            System.out.println("¡Has ganado!");
            System.out.println("Tiempo insumido  -  " + horas + ":" + minutos + ":" + segundos + "." + milisegundosRestantes);
            
            System.out.println("Desea jugar de nuevo?");
            String inputStr = input.next();
            
            if (inputStr.equalsIgnoreCase("si")){
                SetUp();
            } else {
                System.out.println("Adios!");
            }
        }
    }            

    public static void mostrarTablero(String[][] unaTabla){
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
    
    public static void mostrarTableroConCambio(String[][] unaTablaSinCambio, String[][] unaTablaConCambio) {
      int filas = unaTablaSinCambio.length;
      int columnas = unaTablaSinCambio[0].length; //Dos matrices del mismo tamanio - 1 para dar lugar a las flechas
                
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
                        System.out.print("| " + unaTablaSinCambio[i][j] + " ");
                    }
                }
            }
            
            if (i != -1){
                System.out.print("| ==> " + (i+1) + " ");
            }
            
            for (int j = -1; j < columnas; j++){
                if (i == -1){
                    if (j == -1){
                        System.out.print("       ");
                    } else {
                        System.out.print(" " + (j+1) + " ");
                    }
                    System.out.print(" ");
                } else {
                    if (j != -1){
                        System.out.print("| " + unaTablaConCambio[i][j] + " ");
                    }
                }
            }
            
            if (i != -1){
                System.out.println("|");
            } else {
                System.out.println();
            }
            
            for (int j = -1; j < columnas; j++){
                if (j == -1){
                    System.out.print("   ");
                } else {
                    System.out.print("+---");
                }
            }
            System.out.print("+");
            
            for (int j = -1; j < columnas; j++){
                if (j == -1){
                    System.out.print("       ");
                } else {
                    System.out.print("+---");
                }
            }
            System.out.println("+");
        }  
    }
    
    public static String obtenerCodigoColor(String codigo) {
        return switch (codigo) {
            case "R" -> "\u001B[31m";
            case "A" -> "\u001B[34m";
            default -> "";
        };
    }
    
    public static boolean esNumero(String cadena) { 
        try {  
            double intento = Double.parseDouble(cadena);  
            return true;
        } catch(NumberFormatException e){  
            return false;  
        }  
    }
    
    public static Tablero leerTxt() throws FileNotFoundException{
        try {
            String Rs = "\033[0m";
            File file = new File(".\\Test\\datos.txt");
            
            String[][] mat;
            int nivel;
            ArrayList<int[]> solucion;
            
            try (Scanner in = new Scanner(file)) {
                int m = in.nextInt();
                int n = in.nextInt();

                in.nextLine();

                mat = new String[m][n];
                for (int i = 0; i < m; i++) {
                    String fila = in.nextLine();
                    String[] lista = fila.split(" ");
                    for (int j = 0; j < n; j++) {
                        String color = obtenerCodigoColor(lista[j].substring(1));
                        String resto = (lista[j].substring(0, 1));
                        mat[i][j] = color + resto + Rs;
                    }
                }

                nivel = in.nextInt();
                in.nextLine();

                solucion = new ArrayList<>();

                for (int i = 0; i < nivel; i++) {
                    int[] coor = new int[2];
                    coor[0] = in.nextInt();
                    coor[1] = in.nextInt();
                    solucion.add(coor);
                }
            }
            
            Tablero ini = new Tablero(mat, nivel);
            ini.setSolucion(solucion);
            
            return ini;
        } catch (FileNotFoundException e) {
            System.out.println("Error en la lectura de datos.");
        }
        
        return null;
    }
}
        
