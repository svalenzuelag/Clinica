/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author josue
 */
public class HistorialDAO extends Historial{
    
    private int id_paciente;
    private int id_diagnostico;
    Conexion cn;
    public HistorialDAO(){}

    public HistorialDAO(int id_paciente, int id_diagnostico, int id_historial, String descripcion, String fecha) {
        super(id_historial, descripcion, fecha);
        this.id_paciente = id_paciente;
        this.id_diagnostico = id_diagnostico;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_diagnostico() {
        return id_diagnostico;
    }

    public void setId_diagnostico(int id_diagnostico) {
        this.id_diagnostico = id_diagnostico;
    }
    
        public DefaultTableModel leerHistorial (){
     
      DefaultTableModel tabla = new  DefaultTableModel();
      try{
          cn = new Conexion();
          cn.abrir_conexion();
          String query="SELECT H.id_historial, H.Historial, H.fecha_historial, P.nombres, P.apellidos,D.descripcion,D.presion,D.azucar,D.glucosa from historialpaciente H inner join paciente P ON H.id_paciente=P.id_paciente inner join diagnostico D ON H.id_diagnostico=D.id_diagnostico;";
          ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
          String encabezado [] = {"id","Descripcion","Fecha","Nombres","Apellidos","Diagnostico","Presion","Azucar","Glucosa"};
          tabla.setColumnIdentifiers(encabezado);
          String datos [] = new String [9];
          while(consulta.next()){
              datos[0] = consulta.getString("H.id_historial");
              datos[1] = consulta.getString("H.Historial");
              datos[2] = consulta.getString("H.fecha_historial");
              datos[3] = consulta.getString("P.nombres");
              datos[4] = consulta.getString("P.apellidos");
              datos[5] = consulta.getString("D.descripcion");
              datos[6] = consulta.getString("D.presion");
              datos[7] = consulta.getString("D.azucar");
              datos[8] = consulta.getString("D.glucosa");
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
            String query = "insert into historialpaciente(Historial,id_paciente,id_diagnostico,fecha_historial) VALUES(?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getDescripcion());
            parametro.setString(2, getFecha());
            parametro.setInt(3,getId_paciente());
            parametro.setInt(4, getId_diagnostico());
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
            String query = "update consultas set Historial=?, id_paciente=?,id_diagnostico=?,fecha_consulta=? where id_historial=? ;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getDescripcion());
            parametro.setString(2, getFecha());
            parametro.setInt(3,getId_paciente());
            parametro.setInt(4, getId_diagnostico());
            parametro.setInt(5,getId_Historial());
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
            String query = "delete from consultas  where id_historial= ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_Historial());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    
}
