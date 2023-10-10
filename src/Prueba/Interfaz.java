/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Prueba;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import obligatorio1.soliflips.Juego;
import obligatorio1.soliflips.Soliflips;
import static obligatorio1.soliflips.Soliflips.leerTxt;
import static obligatorio1.soliflips.Soliflips.randomTabla;
import obligatorio1.soliflips.Tablero;

/**
 *
 * @author meppi y lau
 */
public class Interfaz {
    //private static Tablero tableroInicial;
    public static void main(String[] args) {
        SetUp();
    }
    
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
                
                Tablero tableroInicial = new Tablero(tabla, nivel);
                mostrarTablero(tabla);
                Juego empezarJuego = new Juego(tableroInicial);
                Jugar(empezarJuego);
            
            } else {
                   
            }
        }
    }
                
    
    public static void Jugar(Juego controlJuego){
        Scanner input = new Scanner(System.in);
        //Tablero tabActual = controlJuego.getLastTablero();
        int nivel = controlJuego.getLastTablero().getNivel();
        while (!controlJuego.seGano()){
            int[] movimiento = new int[2];
            movimiento[0] = input.nextInt();            
            
            if(movimiento[0] != -1){
                movimiento[1] = input.nextInt();
                controlJuego.addHistorialMovimiento(movimiento);
                Tablero tabActual = controlJuego.getLastTablero();
                
                String[][] actual = tabActual.getTabla();
                String[][] cambiada = tabActual.cambiarTabla(movimiento);
                
                Tablero tabNuevo = new Tablero(cambiada, nivel);
                
                mostrarTableroConCambio(actual, cambiada);
                
                controlJuego.addHistorialTableros(tabNuevo);
                
            } else {
                
                if(movimiento[0] == -1){
                    controlJuego.deleteLastTablero();
                    controlJuego.deleteLastMovimiento();
                    
                    mostrarTablero(controlJuego.getLastTablero().getTabla());
                }
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
    
    public static void mostrarTableroConCambio(String[][] unaTablaSinCambio, String[][] unaTablaConCambio) {
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
}
