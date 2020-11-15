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
public class Diagnostico {
    
    private int id;
    private String descripcion;
    private String presion;
    private String azucar;
    private String glucosa;
    Conexion cn;
    
    public Diagnostico(){}

    public Diagnostico(int id, String descripcion, String presion, String azucar, String glucosa) {
        this.id = id;
        this.descripcion = descripcion;
        this.presion = presion;
        this.azucar = azucar;
        this.glucosa = glucosa;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPresion() {
        return presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public String getAzucar() {
        return azucar;
    }

    public void setAzucar(String azucar) {
        this.azucar = azucar;
    }

    public String getGlucosa() {
        return glucosa;
    }

    public void setGlucosa(String glucosa) {
        this.glucosa = glucosa;
    }
    
    public DefaultTableModel leerDiagnostico (){
     
      DefaultTableModel tabla = new  DefaultTableModel();
      try{
          cn = new Conexion();
          cn.abrir_conexion();
          String query="select id_diagnostico as id, descripcion, presion, azucar, glucosa from diagnostico;";
          ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
          String encabezado [] = {"id","Descripcion","Presion","Azucar","Glucosa"};
          tabla.setColumnIdentifiers(encabezado);
          String datos [] = new String [5];
          while(consulta.next()){
              datos[0] = consulta.getString("id");
              datos[1] = consulta.getString("Descripcion");
              datos[2] = consulta.getString("Presion");
              datos[3] = consulta.getString("Azucar");
              datos[4] = consulta.getString("Glucosa");
              tabla.addRow(datos);
          }
           cn.cerrar_conexion();
      }catch(SQLException ex){
      System.out.println(ex.getMessage());
      }
      return tabla;
      }
 
      public int agregar(){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "insert into diagnostico(descripcion,presion,azucar,glucosa) VALUES(?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getDescripcion());
            parametro.setString(2, getPresion());
            parametro.setString(3,getAzucar());
            parametro.setString(4, getGlucosa());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    
    public int modificar(){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update diagnostico set descripcion=?, presion=?,azucar=?,glucosa=? where id_diagnostico=? ;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getDescripcion());
            parametro.setString(2, getPresion());
            parametro.setString(3,getAzucar());
            parametro.setString(4, getGlucosa());
            parametro.setInt(5,getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    
    }
    
    public int eliminar (){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "delete from habitaciones  where id_diagnostico= ?;";
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
    
    
   public HashMap drop_diagnostico(){
     HashMap<String,String> drop = new HashMap();
     try {
         cn = new Conexion();
         String query = "select id_diagnostico as id, descripcion from diagnostico;";
         cn.abrir_conexion();
         ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
         while (consulta.next()){
             drop.put(consulta.getString("id"), consulta.getString("descripcion"));
         }
         cn.cerrar_conexion();
     }catch(SQLException ex){
         System.out.println(ex.getMessage());
     }
     return drop;
    }
    
}

      

