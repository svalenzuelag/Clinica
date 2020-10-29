package modelo;

import modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    /*Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;*/
    Conexion cn;

    public int agregar(Producto p) {
        int retorno =0;
        
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "insert into medicina(nombre, existencia, fecha_ingreso, fecha_vencimiento, ruta)values(?,?,?,?,?)";
            //con = cn.getConnection();
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, p.getNombre());
            parametro.setString(2, p.getExistencia());
            parametro.setString(3, p.getFechaIngreso());
            parametro.setString(4,p.getFechaVencimiento());
            parametro.setString(5, p.getRuta());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
            //parametro = cn.conexionBD.prepareStatement(query);
            /*ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getRuta());
            ps.executeUpdate();*/
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }
        return retorno;
    }
    
     public int modificar(Producto p) {
        int retorno =0;
        
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update medicina set nombre=? ,existencia=?,fecha_ingreso=?,fecha_vencimiento=?, ruta=? where id=?";
            //con = cn.getConnection();
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, p.getNombre());
            parametro.setString(2, p.getExistencia());
            parametro.setString(3, p.getFechaIngreso());
            parametro.setString(4,p.getFechaVencimiento());
            parametro.setString(5, p.getRuta());
            parametro.setInt(6, p.getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
            //parametro = cn.conexionBD.prepareStatement(query);
            /*ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getRuta());
            ps.executeUpdate();*/
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }
        return retorno;
    }
    
    public int eliminar(Producto p){
          int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "delete from medicina  where id= ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, p.getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    
    }

    public List<Producto> listar() {
        //Conexion cn = new Conexion();
        //String sql = "select * from producto";
        List<Producto>lista=new ArrayList<>();
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "select *from medicina;";
            //con = cn.getConnection();
            parametro = cn.conexionBD.prepareStatement(query);
            ResultSet consulta =parametro.executeQuery();
            while (consulta.next()) {
                Producto p=new Producto();
                p.setId(consulta.getInt(1));
                p.setNombre(consulta.getString(2));
                p.setExistencia(consulta.getString(3));
                p.setFechaIngreso(consulta.getString(4));
                p.setFechaVencimiento(consulta.getString(5));
                p.setRuta(consulta.getString(6));
                lista.add(p);
            }
        } catch (SQLException e) {
        }
        return lista;
    }
    
    public List<Producto> getMedicamentos(String filtro)throws SQLException{
        
         List<Producto>lista=new ArrayList<>();
       
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT *FROM medicina WHERE nombre LIKE '%"+filtro+"%' OR existencia LIKE '%"+filtro+"%' OR fecha_ingreso LIKE '%"+filtro+"%' OR fecha_vencimiento LIKE '%"+filtro+"%';";
            //con = cn.getConnection();
            parametro = cn.conexionBD.prepareStatement(query);
            ResultSet consulta =parametro.executeQuery();
            Producto p;
            while (consulta.next()) {
                p=new Producto();
                p.setId(consulta.getInt(1));
                p.setNombre(consulta.getString(2));
                p.setExistencia(consulta.getString(3));
                p.setFechaIngreso(consulta.getString(4));
                p.setFechaVencimiento(consulta.getString(5));
                p.setRuta(consulta.getString(6));
                lista.add(p);
            }
            cn.cerrar_conexion();
        
        return lista;
        
    }
    
    
    public Producto getMedicamento(String id)throws SQLException{
        
            Producto p = null;
            PreparedStatement parametroP;
            cn = new Conexion();
            cn.abrir_conexion();
            String queryP = "select *from medicina where id_medicina="+id;
            //con = cn.getConnection();
            parametroP = cn.conexionBD.prepareStatement(queryP);
            ResultSet consultaP =parametroP.executeQuery();
            if(consultaP.next()){
                p = new Producto();
                p.setId(consultaP.getInt(1));
                p.setNombre(consultaP.getString(2));
                p.setExistencia(consultaP.getString(3));
                p.setFechaIngreso(consultaP.getString(4));
                p.setFechaVencimiento(consultaP.getString(5));
                
            }
            cn.cerrar_conexion();
            return p;
                
                }
    }
   



