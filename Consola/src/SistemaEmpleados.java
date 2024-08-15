import java.util.Scanner;

public class SistemaEmpleados{
    public static void main(String[] args) {
        System.out.println("*** SISTEMA DE EMPLEADOS ***");

        var consola = new Scanner(System.in);

        // nombre del empleado
        System.out.print("Ingrese el nombre del empleado: ");
        var nombreEmpleado = consola.nextLine();

        // edad del empleado
        System.out.print("Ingrese la edad del empleado: ");
        var edad = Integer.parseInt(consola.nextLine());

        // sueldo del empleado
        System.out.print("Ingrese el sueldo del empleado: ");
        var sueldoEmpleado = Double.parseDouble(consola.nextLine());

        // es jefe de departamento
        System.out.println("Es jefe de departamento? (true/false): ");
        var esJefeDepartamento = Boolean.parseBoolean(consola.nextLine());

        // Imprimir los valores
        System.out.println("Datos del Empleado: ");
        System.out.println("\nNombre: " + nombreEmpleado);
        System.out.println("\tEdad: " + edad + " años");
        System.out.printf("\tSueldo: $%.2f".formatted(sueldoEmpleado));
        System.out.println("\tEs Jefe de departamento? " + esJefeDepartamento);
        }
}
