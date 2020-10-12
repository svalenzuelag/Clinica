/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author josue
 */
public class Empleado extends Persona{
    private int id_usuario,id_puesto;
    private Conexion cn;

    public Empleado(){}

    public Empleado(int id_usuario, int id_puesto, int id, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, String correo) {
        super(id, nombres, apellidos, direccion, telefono, fecha_nacimiento, correo);
        this.id_usuario = id_usuario;
        this.id_puesto = id_puesto;
    }
    
    

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }
    
      public DefaultTableModel leerEmpleado (){
    
    DefaultTableModel tabla = new  DefaultTableModel();
      try{
          cn = new Conexion();
          cn.abrir_conexion();
          String query="SELECT E.id_empleado AS id,E.nombres,E.apellidos,E.direccion,E.telefono,E.fecha_nacimiento,E.correo,U.usuario,P.puesto from empleado E inner join usuario U on E.id_usuario=U.id_usuario inner join puesto P on E.id_puesto=P.id_puesto;";
          ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
          String encabezado [] = {"id","Nombre","Apellido","Direccion","Telefono","Fecha Nacimiento","Correo","Usuario","Puesto"};
          tabla.setColumnIdentifiers(encabezado);
          String datos [] = new String [9];
          while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("nombres");
          datos[2] = consulta.getString("apellidos");
          datos[3] = consulta.getString("direccion");
          datos[4] = consulta.getString("telefono");
          datos[5] = consulta.getString("fecha_nacimiento");
          datos[6] = consulta.getString("correo");
          datos[7] = consulta.getString("usuario");
          datos[8] = consulta.getString("puesto");
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
            String query = "insert into empleado(nombres,apellidos,direccion,telefono,fecha_nacimiento,correo,id_usuario,id_puesto) values(?,?,?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getNombres());
            parametro.setString(2,getApellidos());
            parametro.setString(3,getDireccion());
            parametro.setString(4,getTelefono());
            parametro.setString(5,getFecha_nacimiento());
            parametro.setString(6, getCorreo());
            parametro.setInt(7, getId_usuario());
            parametro.setInt(8, getId_puesto());
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
            String query = "update empleado set nombres= ?,apellidos= ?,direccion= ?,telefono= ?,fecha_nacimiento= ?,correo=?,id_usuario=?,id_puesto=? where id_empleado= ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getNombres());
            parametro.setString(2,getApellidos());
            parametro.setString(3,getDireccion());
            parametro.setString(4,getTelefono());
            parametro.setString(5,getFecha_nacimiento());
            parametro.setString(6,getCorreo());
            parametro.setInt(7,getId_usuario());
            parametro.setInt(8, getId_puesto());
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
            String query = "delete from empleado  where id_empleado = ?;";
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


