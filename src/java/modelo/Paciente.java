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
public class Paciente extends Persona{
    
    private String cui;
    private String telefonoR;
    private Conexion cn;

    public Paciente(){}

    public Paciente(String cui, String telefonoR, int id, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, String correo) {
        super(id, nombres, apellidos, direccion, telefono, fecha_nacimiento, correo);
        this.cui = cui;
        this.telefonoR = telefonoR;
    }

    
    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getTelefonoR() {
        return telefonoR;
    }

    public void setTelefonoR(String telefonoR) {
        this.telefonoR = telefonoR;
    }
    
     public DefaultTableModel leerPaciente (){
    
    DefaultTableModel tabla = new  DefaultTableModel();
      try{
          cn = new Conexion();
          cn.abrir_conexion();
          String query="select *from paciente;";
          ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
          String encabezado [] = {"id","Nombre","Apellido","Direccion","Telefono","Fecha Nacimiento","Correo","Cui","Tel: Responsable"};
          tabla.setColumnIdentifiers(encabezado);
          String datos [] = new String [9];
          while (consulta.next()){
          datos[0] = consulta.getString("id_paciente");
          datos[1] = consulta.getString("nombres");
          datos[2] = consulta.getString("apellidos");
          datos[3] = consulta.getString("direccion");
          datos[4] = consulta.getString("telefono");
          datos[5] = consulta.getString("fecha_nacimiento");
          datos[6] = consulta.getString("correo");
          datos[7] = consulta.getString("CUI");
          datos[8] = consulta.getString("telResponsable");
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
            String query = "insert into paciente(nombres,apellidos,direccion,telefono,fecha_nacimiento,correo,CUI,telResponsable) values(?,?,?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getNombres());
            parametro.setString(2,getApellidos());
            parametro.setString(3,getDireccion());
            parametro.setString(4,getTelefono());
            parametro.setString(5,getFecha_nacimiento());
            parametro.setString(6, getCorreo());
            parametro.setString(7, getCui());
            parametro.setString(8, getTelefonoR());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    
    @Override
    public int modificar (){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update paciente set nombres= ?,apellidos= ?,direccion= ?,telefono= ?,fecha_nacimiento= ?,correo=?,CUI=?,telResponsable=? where id_paciente= ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getNombres());
            parametro.setString(2,getApellidos());
            parametro.setString(3,getDireccion());
            parametro.setString(4,getTelefono());
            parametro.setString(5,getFecha_nacimiento());
            parametro.setString(6, getCorreo());
            parametro.setString(7, getCui());
            parametro.setString(8, getTelefonoR());
            parametro.setInt(9, getId());
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
            String query = "delete from paciente  where id_paciente = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
  
    public HashMap drop_paciente(){
     HashMap<String,String> drop = new HashMap();
     try {
         cn = new Conexion();
         String query = "select id_paciente AS id,nombres,apellidos from paciente;";
         cn.abrir_conexion();
         ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
         while (consulta.next()){
             drop.put(consulta.getString("id"), consulta.getString("nombres"));
         }
         cn.cerrar_conexion();
     }catch(SQLException ex){
         System.out.println(ex.getMessage());
     }
     return drop;
    }
    
}
