import java.util.Scanner;

public class RecetasCocina {
    public static void main(String[] args) {
        System.out.println("*** Recetas de Cocina ***");

        var consola = new Scanner(System.in);

        System.out.print("Ingresa el nombre: ");
        var nombre = consola.nextLine();
        System.out.print("Ingresa los ingredientes: ");
        var ingredientes = consola.nextLine();
        System.out.print("Ingresa el tiempo de preparación (min): ");
        var tiempo = Integer.parseInt(consola.nextLine());
        System.out.print("Ingrese la dificultad: ");
        var dificultad = consola.nextLine();

        System.out.println("\n--- Receta de Cocina ---");
        System.out.println("Nombre receta: " + nombre);
        System.out.println("Ingredientes: " + ingredientes);
        System.out.println("Tiempo de preparación: " + tiempo + " minutos");
        System.out.println("Dificultad: " + dificultad);

    }




}
