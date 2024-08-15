package veterinaria.Entidades;

public class Cliente {

    private int idCliente;
    private int dni;
    private String apellido;
    private String nombre;
    private String direccion;
    private long telefono;
    private String personaAlternativa;
    private boolean estado;

    public Cliente() {
    }

    /**
     * Constructor para inicializar un cliente sin ID.
     *
     * @param dni               el DNI del cliente
     * @param apellido          el apellido del cliente
     * @param nombre            el nombre del cliente
     * @param direccion         la dirección del cliente
     * @param telefono          el teléfono del cliente
     * @param personaAlternativa la persona alternativa de contacto del cliente
     * @param estado            el estado del cliente
     */

    public Cliente(int dni, String apellido, String nombre, String direccion, long telefono, String personaAlternativa, boolean estado) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.personaAlternativa = personaAlternativa;
        this.estado = estado;
    }

    /**
     * Constructor para inicializar un cliente con ID.
     *
     * @param idCliente         el ID del cliente
     * @param dni               el DNI del cliente
     * @param apellido          el apellido del cliente
     * @param nombre            el nombre del cliente
     * @param direccion         la dirección del cliente
     * @param telefono          el teléfono del cliente
     * @param personaAlternativa la persona alternativa de contacto del cliente
     * @param estado            el estado del cliente
     */

    public Cliente(int idCliente, int dni, String apellido, String nombre, String direccion, long telefono, String personaAlternativa, boolean estado) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.personaAlternativa = personaAlternativa;
        this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        if (dni > 0) {
            this.dni = dni;
        } else {
            throw new IllegalArgumentException("El DNI debe ser positivo");
        }
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido != null && !apellido.trim().isEmpty()) {
            this.apellido = apellido;
        } else {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion != null && !direccion.trim().isEmpty()) {
            this.direccion = direccion;
        } else {
            throw new IllegalArgumentException("La dirección no puede estar vacía");
        }
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        if (telefono > 0) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("El teléfono debe ser con números positivo");
        }
    }

    public String getPersonaAlternativa() {
        return personaAlternativa;
    }

    public void setPersonaAlternativa(String personaAlternativa) {
        if (personaAlternativa != null && !personaAlternativa.trim().isEmpty()) {
            this.personaAlternativa = personaAlternativa;
        } else {
            throw new IllegalArgumentException("La persona alternativa no puede estar vacía");
        }
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return idCliente + " " + dni + " " + apellido + " " + nombre + " " 
            + direccion + " " + telefono + " " + personaAlternativa + " " + estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (idCliente != cliente.idCliente) return false;
        if (dni != cliente.dni) return false;
        if (telefono != cliente.telefono) return false;
        if (estado != cliente.estado) return false;
        if (!apellido.equals(cliente.apellido)) return false;
        if (!nombre.equals(cliente.nombre)) return false;
        if (!direccion.equals(cliente.direccion)) return false;
        return personaAlternativa.equals(cliente.personaAlternativa);
    }

    @Override
    public int hashCode() {
        int result = idCliente;
        result = 31 * result + dni;
        result = 31 * result + apellido.hashCode();
        result = 31 * result + nombre.hashCode();
        result = 31 * result + direccion.hashCode();
        result = 31 * result + Long.hashCode(telefono);
        result = 31 * result + personaAlternativa.hashCode();
        result = 31 * result + (estado ? 1 : 0);
        return result;
    }
}
