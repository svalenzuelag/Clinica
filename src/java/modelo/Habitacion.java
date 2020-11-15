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
public class Habitacion extends  HabitacionDAO{
    
     private int idSucursal;
     Conexion cn;

    public Habitacion() {}

    public Habitacion(int idSucursal, int id, String habitacion, String nivel, String numero, String capacidad) {
        super(id, habitacion, nivel, numero, capacidad);
        this.idSucursal = idSucursal;
    }
    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
    
     public DefaultTableModel leerHabitacion (){
     
      DefaultTableModel tabla = new  DefaultTableModel();
      try{
          cn = new Conexion();
          cn.abrir_conexion();
          String query="select H.id_habitacion, H.habitacion, H.Nivel, H.NoHabitacion, H.CapacidadPacientes, S.sucursal from habitaciones H inner join sucursal S on H.id_sucursal=S.id_sucursal;";
          ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
          String encabezado [] = {"id","Habitacion","Nivel","Numero","Capacidad","sucursal"};
          tabla.setColumnIdentifiers(encabezado);
          String datos [] = new String [6];
          while(consulta.next()){
              datos[0] = consulta.getString("id_habitacion");
              datos[1] = consulta.getString("habitacion");
              datos[2] = consulta.getString("Nivel");
              datos[3] = consulta.getString("NoHabitacion");
              datos[4] = consulta.getString("CapacidadPacientes");
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
            String query = "insert into habitaciones(habitacion,Nivel,NoHabitacion,CapacidadPacientes,id_sucursal) VALUES(?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getHabitacion());
            parametro.setString(2, getNivel());
            parametro.setString(3,getNumero());
            parametro.setString(4, getCapacidad());
            parametro.setInt(5, getIdSucursal());
           
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
            String query = "update habitaciones set habitacion=?, Nivel=?,NoHabitacion=?,CapacidadPacientes=?,id_sucursal=? where id_habitacion=? ;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getHabitacion());
            parametro.setString(2, getNivel());
            parametro.setString(3,getNumero());
            parametro.setString(4, getCapacidad());
            parametro.setInt(5, getIdSucursal());
            parametro.setInt(6,getId());
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
            String query = "delete from habitaciones  where id_habitacion = ?;";
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

      

