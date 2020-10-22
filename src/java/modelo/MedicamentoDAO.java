/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josue
 */
public class MedicamentoDAO {
    
      Conexion cn;
              
               public int agregar(Medicamento m) {
        int retorno =0;
        
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "insert into imagen(nombre, existencia, fecha_ingreso, fecha_vencimiento, ruta)values(?,?,?,?,?)";
            //con = cn.getConnection();
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, m.getNombre());
            parametro.setString(2, m.getExistencia());
            parametro.setString(3, m.getFechaIngreso());
            parametro.setString(4, m.getFechaVencimiento());
            parametro.setString(5, m.getRuta());
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
    
     public int modificar(Medicamento m) {
        int retorno =0;
        
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update imagen set nombre=? ,existencia=?,fecha_ingreso=?,fecha_vencimiento=?, ruta=? where id=?";
            //con = cn.getConnection();
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, m.getNombre());
            parametro.setString(2, m.getExistencia());
            parametro.setString(3, m.getFechaIngreso());
            parametro.setString(4, m.getFechaVencimiento());
            parametro.setString(5, m.getRuta());
            parametro.setInt(6, m.getId());
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
    
    public int eliminar(Medicamento m){
          int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "delete from imagen  where id= ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, m.getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    
    }

    public List<Medicamento> listar() {
        //Conexion cn = new Conexion();
        //String sql = "select * from producto";
        List<Medicamento>lista=new ArrayList<>();
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "select *from medicina;";
            //con = cn.getConnection();
            parametro = cn.conexionBD.prepareStatement(query);
            ResultSet consulta =parametro.executeQuery();
            while (consulta.next()) {
                Medicamento m=new Medicamento();
                m.setId(consulta.getInt(1));
                m.setNombre(consulta.getString(2));
                m.setExistencia(consulta.getString(3));
                m.setFechaIngreso(consulta.getString(4));
                m.setFechaVencimiento(consulta.getString(5));
                m.setRuta(consulta.getString(6));
                lista.add(m);
            }
        } catch (SQLException e) {
        }
        return lista;
    }
    
}
