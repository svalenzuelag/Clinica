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
public class CitaMedica extends CitaMedicaDAO{
    
    
    private int idSucursal;
    Conexion cn;
    public CitaMedica(){}

    public CitaMedica(int idSucursal, int id, String fechaCita, String nombres, String apellidos, String telefono,String correo) {
        super(id, fechaCita, nombres, apellidos, telefono,correo);
        this.idSucursal = idSucursal;
    }

    
    
    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
    
     public DefaultTableModel leerCita (){
    
    DefaultTableModel tabla = new  DefaultTableModel();
      try{
          cn = new Conexion();
          cn.abrir_conexion();
          String query="select C.id_cita,C.fecha_cita,S.sucursal,C.Nombre,C.Apellidos,C.Telefono,C.correo from cita_medica C inner Join sucursal S on C.id_sucursal= S.id_sucursal;";
          ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
          String encabezado [] = {"id","Fecha","Sucursal","Nombres","Apellidos","Telefono","Correo"};
          tabla.setColumnIdentifiers(encabezado);
           String datos [] = new String [7];
          while (consulta.next()){
              datos[0] = consulta.getString("id_cita");
              datos[1] = consulta.getString("fecha_cita");
              datos[2] = consulta.getString("sucursal");
              datos[3] = consulta.getString("Nombre");
              datos[4] = consulta.getString("Apellidos");
              datos[5] = consulta.getString("Telefono");
              datos[6] = consulta.getString("correo");
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
            String query = "insert into cita_medica(fecha_cita,id_sucursal,Nombre,Apellidos,Telefono,correo)VALUES(?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getFechaCita());
            parametro.setInt(2, getIdSucursal());
            parametro.setString(3,getNombres());
            parametro.setString(4, getApellidos());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getCorreo());
           
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
            String query = "update cita_medica set fecha_cita=?, id_sucursal=?,Nombre=?,Apellidos=?,Telefono=?,correo=? where id_cita=? ;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getFechaCita());
            parametro.setInt(2, getIdSucursal());
            parametro.setString(3,getNombres());
            parametro.setString(4, getApellidos());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getCorreo());
            parametro.setInt(7,getId());
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
            String query = "delete from cita_medica  where id_cita = ?;";
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
