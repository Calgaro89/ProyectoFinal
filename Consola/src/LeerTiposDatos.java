import java.util.Scanner;

public class LeerTiposDatos {
    public static void main(String[] args) {
            // leer distintos tipos de datos
            // leer un tipo int
        var consola = new Scanner(System.in);
        System.out.println("Ingresa tu edad: ");
        var edad = consola.nextInt();
        System.out.println("edad = " + edad);

            // leer un tipo doble
        System.out.println("Ingresa tu altura: ");
        var altura = consola.nextDouble();
        System.out.println("altura = " + altura);

            // leer un tipo String
        System.out.println("Ingresa tu nombre: ");
        var nombre = consola.nextLine();
        System.out.println("nombre = " + nombre);

            // conversion de datos
        System.out.println("Proporciona un valor entero: ");
        var enteroString = consola.nextLine();
        var entero = Integer.parseInt(enteroString);
        System.out.println("entero = " + entero);

            // tipo flotante
        System.out.println("Proporciona un valor flotante");
        var flotante = Float.parseFloat(consola.nextLine());
        System.out.println("flotante = " + flotante);
        // Double.parseDouble()
        // Boolean.parseBoolean()
    }
}
