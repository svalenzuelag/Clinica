/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import modelo.Sucursal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josue
 */
public class SucursalDAO {
    
      Conexion cn;
       public int agregar(Sucursal s) {
        int retorno =0;
        
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "insert into sucursal(sucursal, direccion, ruta)values(?,?,?)";
            //con = cn.getConnection();
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, s.getSucursal());
            parametro.setString(2, s.getDireccion());
            parametro.setString(3, s.getRuta());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }
        return retorno;
    }
       
       public int modificar(Sucursal s) {
        int retorno =0;
        
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update sucursal set sucursal=? ,direccion=?, ruta=? where id_sucursal=?";
            //con = cn.getConnection();
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, s.getSucursal());
            parametro.setString(2, s.getDireccion());
            parametro.setString(3, s.getRuta());
            parametro.setInt(4, s.getId());
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
    
       public int eliminar(Sucursal s){
          int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "delete from sucursal  where id_sucursal= ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, s.getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    
    }

       public List<Sucursal> listar() {
        //Conexion cn = new Conexion();
        //String sql = "select * from producto";
        List<Sucursal>lista=new ArrayList<>();
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "select *from sucursal;";
            //con = cn.getConnection();
            parametro = cn.conexionBD.prepareStatement(query);
            ResultSet consulta =parametro.executeQuery();
            while (consulta.next()) {
                Sucursal s=new Sucursal();
                s.setId(consulta.getInt(1));
                s.setSucursal(consulta.getString(2));
                s.setDireccion(consulta.getString(3));
                s.setRuta(consulta.getString(4));
                lista.add(s);
            }
        } catch (SQLException e) {
        }
        return lista;
    }

}
