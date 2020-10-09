<%-- 
    Document   : index
    Created on : 01-oct-2020, 16:14:31
    Author     : josue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Login</title>
       <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="contenedor1">
             <form action="login" method="post" id="form1">
            <h1>Sign In</h1>
          
                
             <input type="text" name="txtusu"  placeholder="Username"/>
             <hr>
             
            
                   <input type="password" name="txtpass"  placeholder="Password"/>
            <hr>
            <input type="submit" name="accion" value="Ingresar"/>
             </form>
        </div>
        <div id="contenedor2">
            <form id="fomr2">
                <input type="submit" name="accion" value="Registro"/>
                <div id="referencias">
                    <a>Login</a>
                    <a href="">Term of Use</a>
                        <a href="Registro.jsp">Privacy Policy</a>
                </div>
                
            </form>
        </div>
    </body>
</html>
