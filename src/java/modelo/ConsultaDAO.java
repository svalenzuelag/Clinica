/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author josue
 */
public class ConsultaDAO extends Consulta{
    
    private int id_paciente;
    private int id_sucursal;
    
    Conexion cn;
    public ConsultaDAO(){}

    public ConsultaDAO(int id_paciente, int id_sucursal, int id_consulta, String descripcion, String fecha) {
        super(id_consulta, descripcion, fecha);
        this.id_paciente = id_paciente;
        this.id_sucursal = id_sucursal;
    }

    
    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }
    
    public DefaultTableModel leerConsulta (){
     
      DefaultTableModel tabla = new  DefaultTableModel();
      try{
          cn = new Conexion();
          cn.abrir_conexion();
          String query="SELECT C.id_consulta, C.descripcion, C.fecha_consulta, P.nombres, P.apellidos,S.sucursal from consultas C inner join paciente P ON C.id_paciente=P.id_paciente inner join sucursal S ON C.id_sucursal=S.id_sucursal;";
          ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
          String encabezado [] = {"id","Descripcion","Fecha","Nombres","Apellidos","Sucursal"};
          tabla.setColumnIdentifiers(encabezado);
          String datos [] = new String [6];
          while(consulta.next()){
              datos[0] = consulta.getString("id_consulta");
              datos[1] = consulta.getString("descripcion");
              datos[2] = consulta.getString("fecha_consulta");
              datos[3] = consulta.getString("nombres");
              datos[4] = consulta.getString("apellidos");
              datos[5] = consulta.getString("sucursal");
              tabla.addRow(datos);
          }
           cn.cerrar_conexion();
      }catch(SQLException ex){
      System.out.println(ex.getMessage());
      }
      return tabla;
      }
    
    @Override
     public int agregar(){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "insert into consultas(descripcion,fecha_consulta,id_paciente,id_sucursal) VALUES(?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getDescripcion());
            parametro.setString(2, getFecha());
            parametro.setInt(3,getId_paciente());
            parametro.setInt(4, getId_sucursal());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    
    @Override
    public int modificar(){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update consultas set descripcion=?, fecha_consulta=?,id_paciente=?,id_sucursal=? where id_consulta=? ;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getDescripcion());
            parametro.setString(2, getFecha());
            parametro.setInt(3,getId_paciente());
            parametro.setInt(4, getId_sucursal());
            parametro.setInt(5,getId_consulta());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    
    }
    
    @Override
    public int eliminar (){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "delete from consultas  where id_consulta= ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_consulta());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    
    
}
