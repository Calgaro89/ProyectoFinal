package veterinaria.AccesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;
import veterinaria.Entidades.Cliente;

public class ClienteData {
    private Connection con = null;
    private static final Logger logger = Logger.getLogger(ClienteData.class.getName());

    public ClienteData() {
        con= Conexion.getConexion();
    }

    public void guardarCliente(Cliente cliente){
        String sql = "INSERT INTO cliente (dni,apellido,nombre,direccion,telefono,personaAlternativa,estado) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cliente.getDni());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getDireccion());
            ps.setLong(5, cliente.getTelefono());
            ps.setString(6, cliente.getPersonaAlternativa());
            ps.setBoolean(7, cliente.isEstado());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt(1));
                    logger.log(Level.INFO, "Cliente agregado con exito: {0}", cliente);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al agregar el cliente", ex);
        }
    }
    
    public void modificarCliente(Cliente cliente){
        String sql = "UPDATE cliente SET dni = ? , apellido = ?, nombre = ?, direccion = ?, telefono = ?, personaAlternativa = ?, estado = ? WHERE idCliente = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cliente.getDni());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getDireccion());
            ps.setLong(5, cliente.getTelefono());
            ps.setString(6, cliente.getPersonaAlternativa());
            ps.setBoolean(7, cliente.isEstado());
            ps.setInt(8, cliente.getIdCliente());
            ps.executeUpdate();
            logger.log(Level.INFO, "Cliente actualizado con exito: {0}", cliente);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al modificar el cliente", ex);
        }
    }

    public void borrarCliente(int dni){
        String sql = "UPDATE cliente SET estado = 0 WHERE dni = ? ";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dni);
            ps.executeUpdate();
            logger.log(Level.INFO, "Cliente eliminado con exito: DNI {0}", dni);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar el cliente", ex);
        }
    }

    public Cliente buscarCliente(int dni) {
        Cliente cliente = new Cliente();
        String sql = "SELECT idCliente, dni, apellido, nombre, direccion, telefono, personaAlternativa, estado FROM cliente WHERE dni = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt("idCliente"));
                    cliente.setDni(rs.getInt("dni"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setTelefono(rs.getLong("telefono"));
                    cliente.setPersonaAlternativa(rs.getString("personaAlternativa"));
                    cliente.setEstado(rs.getBoolean("estado"));
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al acceder a la tabla cliente", ex);
        }
        return cliente;
    }

    public ArrayList<Cliente> listarClientes() {
    ArrayList<Cliente> lista = new ArrayList<>();
    String sql = "SELECT * FROM cliente WHERE estado = 1";
         try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setDni(rs.getInt("dni"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getLong("telefono"));
                cliente.setPersonaAlternativa(rs.getString("personaAlternativa"));
                cliente.setEstado(rs.getBoolean("estado"));
                lista.add(cliente);
            }
         } catch (SQLException ex) {
             logger.log(Level.SEVERE, "Error al acceder a la tabla Cliente", ex);
         }
         return lista;
    }

    public ArrayList<Cliente> listarClientes2(){
    ArrayList <Cliente> lista = new ArrayList();
        try {
            String sql = "SELECT * FROM cliente";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setDni(rs.getInt("dni"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getLong("telefono"));
                cliente.setPersonaAlternativa(rs.getString("personaAlternativa"));
                cliente.setEstado(rs.getBoolean("estado"));
                lista.add(cliente);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Cliente" + ex.getMessage());
        }
        return lista;
    }
}
