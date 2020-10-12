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
public class Medicamento {
    private int id;
    private String medicina,existencia,fecha_ingreso,fecha_vencimiento,imagen;
    private Conexion cn;
    
    public Medicamento(){}

    public Medicamento(int id, String medicina, String existencia, String fecha_ingreso, String fecha_vencimiento, String imagen) {
        this.id = id;
        this.medicina = medicina;
        this.existencia = existencia;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_vencimiento = fecha_vencimiento;
        this.imagen = imagen;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicina() {
        return medicina;
    }

    public void setMedicina(String medicina) {
        this.medicina = medicina;
    }

    public String getExistencia() {
        return existencia;
    }

    public void setExistencia(String existencia) {
        this.existencia = existencia;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
     public DefaultTableModel leerMedicamento (){
    
    DefaultTableModel tabla = new  DefaultTableModel();
      try{
          cn = new Conexion();
          cn.abrir_conexion();
          String query="select *from medicina;";
          ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
          String encabezado [] = {"id","Medicina","Existencia","Fecha Ingreso","Fecha Vencimiento","Imagen"};
          tabla.setColumnIdentifiers(encabezado);
          String datos [] = new String [6];
          while (consulta.next()){
          datos[0] = consulta.getString("id_medicina");
          datos[1] = consulta.getString("medicina");
          datos[2] = consulta.getString("existencia");
          datos[3] = consulta.getString("fecha_ingreso");
          datos[4] = consulta.getString("fecha_vencimiento");
          datos[5] = consulta.getString("imagen");
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
            String query = "insert into medicina(medicina,existencia,fecha_ingreso,fecha_vencimiento,imagen) values(?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getMedicina());
            parametro.setString(2,getExistencia());
            parametro.setString(3,getFecha_ingreso());
            parametro.setString(4,getFecha_vencimiento());
            parametro.setString(5,getImagen());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    

    public int modificar (){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update medicina set medicina= ?,existencia= ?,fecha_ingreso= ?,fecha_vencimiento= ?,imagen= ? where id_medicina= ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getMedicina());
            parametro.setString(2,getExistencia());
            parametro.setString(3,getFecha_ingreso());
            parametro.setString(4,getFecha_vencimiento());
            parametro.setString(5,getImagen());
            parametro.setInt(6, getId());
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
            String query = "delete from medicina  where id_medicina = ?;";
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
}
