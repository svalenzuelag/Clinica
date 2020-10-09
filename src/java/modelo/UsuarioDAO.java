/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author josue
 */
public class UsuarioDAO implements Validar {

    Conexion cn;
    
   
    @Override
    public int validar(Usuario usu) {
         int retorno=0;
        try{
            PreparedStatement parametro;
            ResultSet rs;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "Select *from usuario where usuario=? and pass=?;";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,usu.getUsuario());
            parametro.setString(2,usu.getPass());
            rs= parametro.executeQuery();
            while(rs.next()){
                retorno=retorno+1;
                usu.setUsuario(rs.getString("usuario"));
                usu.setPass(rs.getString("pass"));
            }
            cn.cerrar_conexion();
            if(retorno==1){
                return 1;
            }else {
                return 0;
            } 
           
        }catch(SQLException ex){}
        return 0;
    }
   
}
