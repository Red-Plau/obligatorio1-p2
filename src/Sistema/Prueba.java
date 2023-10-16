//AUTORES:
//  Laura Soumastre [317667]
//  Marcos Eppinger [203654]

package Sistema;

import static Sistema.Interfaz.SetUp;
import java.util.Scanner;

public class Prueba {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("""
                            ____   __   __    __  ____  __    __  ____  ____ 
                           / ___) /  \\ (  )  (  )(  __)(  )  (  )(  _ \\/ ___)
                           \\___ \\(  O )/ (_/\\ )(  ) _) / (_/\\ )(  ) __/\\___ \\
                           (____/ \\__/ \\____/(__)(__)  \\____/(__)(__)  (____/
                           (c) 2023 Laura Soumastre [317667], Marcos Eppinger [203654]
                           """);
        
        System.out.println("Desea jugar?");
        String ent = in.nextLine();
        if (ent.equalsIgnoreCase("si")){
            SetUp();
        } else {
            System.out.println("Bueno chau");
        }
    }
}
