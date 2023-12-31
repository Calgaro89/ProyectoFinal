package veterinaria.AccesoADatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import veterinaria.Entidades.Mascota;
import veterinaria.Entidades.Tratamiento;
import veterinaria.Entidades.Visita;

// FEDE
public class VisitaData {

    private Connection con = null;

    public VisitaData() {

        con = Conexion.getConexion();

    }

    public void guardarVisita(Visita visita) {

        String sql = "INSERT INTO visita( idMascota, idTratamiento, fechaVisita,"
                + " observaciones, pesoActual) VALUES (?,?,?,?,?)";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, visita.getMascota().getIdMascota());
            ps.setInt(2, visita.getTratamiento().getIdTratamiento());
            ps.setDate(3, Date.valueOf(visita.getFechaTratamiento()));
            ps.setString(4, visita.getObservaciones());
            ps.setDouble(5, visita.getPesoActual());

            ps.executeUpdate();

            ps.close();

            JOptionPane.showMessageDialog(null, " visita guardada ");

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, " error al acceder a la tabla visita " + ex);
        }
    }

    public void medificarVisita(Visita visita) {

        String sql = " UPDATE visita SET idVisita = ?, idMascota = ?,idTratamiento = ?,fechaVisita = ?,observaciones = ?,pesoActual = ? WHERE idVisita = ? ";

        try {

            PreparedStatement ps = null;

            ps.setInt(1, visita.getMascota().getIdMascota());
            ps.setInt(2, visita.getTratamiento().getIdTratamiento());
            ps.setDate(3, Date.valueOf(visita.getFechaTratamiento()));
            ps.setString(4, visita.getObservaciones());
            ps.setDouble(5, visita.getPesoActual());

            int bienAhi = ps.executeUpdate();

            if (bienAhi == 1) {

                JOptionPane.showMessageDialog(null, " modificado exitosamente ");
            } else {

                JOptionPane.showMessageDialog(null, " la cagaste pa' ");
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, " error al acceder a la tabla visita ");
        }

    }

    public void eliminarVisita(Visita visita) {

        String sql = " DELETE FROM `visita` WHERE idVisita = ? ";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, visita.getIdVisita());

            int borrado = ps.executeUpdate();

            if (borrado == 1) {

                JOptionPane.showMessageDialog(null, " visita borrada ");
            }

            ps.close();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, " error al acceder a la tabla visita ");
        }
    }

    public List<Visita> listarVisitaXMascota(int id) {
        List<Visita> visita = new ArrayList();
        String sql = "SELECT * FROM `visita` WHERE idMascota=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Visita visit = new Visita();
                Mascota mascota = new Mascota();
                Tratamiento tratamiento = new Tratamiento();

                visit.setIdVisita(rs.getInt("idVisita"));

                mascota.setIdMascota(rs.getInt("idMascota"));
                tratamiento.setIdTratamiento(rs.getInt("idTratamiento"));

                visit.setMascota(mascota);
                visit.setTratamiento(tratamiento);

                visit.setFechaTratamiento(rs.getDate("fechaVisita").toLocalDate());
                visit.setObservaciones(rs.getString("observaciones"));
                visit.setPesoActual(rs.getDouble("pesoActual"));
                visita.add(visit);
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al querer listar visita" + ex.getMessage());
        }
        return visita;
    }
    public List<Visita> listarVisitaXTratamiento(int id) {
        List<Visita> visita = new ArrayList();
        String sql = "SELECT * FROM `visita` WHERE idTratamiento=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Visita visit = new Visita();
                Mascota mascota = new Mascota();
                Tratamiento tratamiento = new Tratamiento();

                visit.setIdVisita(rs.getInt("idVisita"));

                mascota.setIdMascota(rs.getInt("idMascota"));
                tratamiento.setIdTratamiento(rs.getInt("idTratamiento"));

                visit.setMascota(mascota);
                visit.setTratamiento(tratamiento);

                visit.setFechaTratamiento(rs.getDate("fechaVisita").toLocalDate());
                visit.setObservaciones(rs.getString("observaciones"));
                visit.setPesoActual(rs.getDouble("pesoActual"));
                visita.add(visit);
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al querer listar visita" + ex.getMessage());
        }
        return visita;
    }

    public void sacarPesoPromedio(Mascota mascota) {
        try {
            String sql = "SELECT AVG(pesoActual) AS pesoPromedio "
                    + "FROM (SELECT pesoActual FROM visita WHERE idMascota = ? ORDER BY fechaVisita DESC LIMIT 10) "
                    + "AS UltimasVisitas";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mascota.getIdMascota());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MascotaData maData = new MascotaData();
                double promedio = rs.getDouble("pesoPromedio");
                mascota.setPesoPromedio(promedio);
                maData.modificarPromedio(mascota);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al acceder al promedio");
        }

    }

    public List<Visita> listarVisita() {
        List<Visita> visita = new ArrayList();
        String sql = "SELECT * FROM `visita`";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Visita visit = new Visita();
                Mascota mascota = new Mascota();
                Tratamiento tratamiento = new Tratamiento();
                visit.setIdVisita(rs.getInt("idVisita"));
                mascota.setIdMascota(rs.getInt("idMascota"));
                tratamiento.setIdTratamiento(rs.getInt("idTratamiento"));
                visit.setMascota(mascota);
                visit.setTratamiento(tratamiento);
                visit.setFechaTratamiento(rs.getDate("fechaVisita").toLocalDate());
                visit.setObservaciones(rs.getString("observaciones"));
                visit.setPesoActual(rs.getDouble("pesoActual"));
                visita.add(visit);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al querer listar visita" + ex.getMessage());
        }
        return visita;
    }

    public double importeTotalDiario(LocalDate fecha) {
        String sql = "SELECT SUM(tratamiento.importe) as total_importe FROM "
                + "visita JOIN tratamiento ON (visita.idTratamiento=tratamiento.idTratamiento)"
                + "WHERE fechaVisita =?";
        try {
            PreparedStatement ps2 = con.prepareStatement(sql);
            ps2.setDate(1, Date.valueOf(fecha));
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
                double importeTotal = rs2.getDouble("total_importe");
                return importeTotal;
            }
            ps2.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al querer listar visita" + ex.getMessage());
        }
        return 0;
    }
       public double importeTotalMensual(int mes, int año) {
        String sql = "SELECT SUM(tratamiento.importe) as total_importe FROM "
                + "visita JOIN tratamiento ON (visita.idTratamiento=tratamiento.idTratamiento)"
                + "WHERE MONTH(fechaVisita)=? AND YEAR(fechaVisita)=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mes);
            ps.setInt(2,año);
            ResultSet rs2 = ps.executeQuery();
            if (rs2.next()) {
                double importeTotal = rs2.getDouble("total_importe");
                return importeTotal;
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al querer listar visita" + ex.getMessage());
        }
        return 0;
    }

    public List<Visita> balanceDiario(LocalDate fecha) {
        List<Visita> visita = new ArrayList<Visita>();

        String sql1 = "SELECT * FROM visita JOIN tratamiento ON "
                + "(visita.idTratamiento=tratamiento.idTratamiento) WHERE fechaVisita =?";
try {
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setDate(1, Date.valueOf(fecha));
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                Visita visit = new Visita();
                Mascota mascota = new Mascota();
                Tratamiento tratamiento = new Tratamiento();
                visit.setIdVisita(rs1.getInt("idVisita"));
                mascota.setIdMascota(rs1.getInt("idMascota"));
                tratamiento.setIdTratamiento(rs1.getInt("idTratamiento"));
                visit.setMascota(mascota);
                visit.setTratamiento(tratamiento);
                visit.setFechaTratamiento(rs1.getDate("fechaVisita").toLocalDate());
                visit.setObservaciones(rs1.getString("observaciones"));
                visit.setPesoActual(rs1.getDouble("pesoActual"));

                visita.add(visit);
            }
            ps1.close();
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al querer listar visita" + ex.getMessage());
        }
        return visita;
    }
    
    public List<Visita> balanceMensual(int mes,int año) {
        List<Visita> visita = new ArrayList<Visita>();

        String sql1 = "SELECT * FROM visita JOIN tratamiento ON "
                + "(visita.idTratamiento=tratamiento.idTratamiento) WHERE MONTH(fechaVisita)=? AND YEAR(fechaVisita)=?";

        try {
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setInt(1, mes);
            ps1.setInt(2,año);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                Visita visit = new Visita();
                Mascota mascota = new Mascota();
                Tratamiento tratamiento = new Tratamiento();
                visit.setIdVisita(rs1.getInt("idVisita"));
                mascota.setIdMascota(rs1.getInt("idMascota"));
                tratamiento.setIdTratamiento(rs1.getInt("idTratamiento"));
                visit.setMascota(mascota);
                visit.setTratamiento(tratamiento);
                visit.setFechaTratamiento(rs1.getDate("fechaVisita").toLocalDate());
                visit.setObservaciones(rs1.getString("observaciones"));
                visit.setPesoActual(rs1.getDouble("pesoActual"));

                visita.add(visit);
            }
            ps1.close();
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al querer listar visita" + ex.getMessage());
        }
        return visita;
    }
}
