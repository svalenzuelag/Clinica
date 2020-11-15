<%@page import="modelo.Sucursal"%>
<%@page import="modelo.SucursalDAO"%>
<%@page import="javax.xml.ws.Response"%>
<%@page import="modelo.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Producto"%>
<%@page import="modelo.ProductoDAO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
  	<title>Sucursales</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/style.css">
  </head>
  <body>
      
    
  <%
      Usuario u = (Usuario)session.getAttribute("user");
      if(u == null){
        response.sendRedirect("index.jsp");
        }
      %>
  <div   class="wrapper d-flex align-items-xl-stretch" >
			<nav id="sidebar" class="active">
				<h1><a href="Principal.jsp" class="logo">Cli</a></h1>
        <ul class="list-unstyled components mb-5">
          <li class="active">
            <a href="Principal.jsp"><span class="fa fa-home"></span> Inicio</a>
          </li>
          <li>
              <a href="CrudEmpleado.jsp"><span class="fa fa-user"></span>Usuarios</a>
          </li>
          <li>
            <a href="Historial.jsp"><span class="fa fa-bars"></span> Historial</a>
          </li>
          <li>
            <a href="CrudPaciente.jsp"><span class="fa fa-user"></span> Pacientes</a>
          </li>
          <li>
            <a href="CrudCita.jsp"><span class="fa fa-calendar-o"></span> Citas</a>
            
            <a href="CrudSucursal.jsp"><span class="fa fa-hospital-o"></span> Sucursales</a>
            
            <a href="CrudMedicamento.jsp"><span class="fa fa-plus-square"></span> Medicamentos</a>
            
            <a href="Consultas.jsp"><span class="fa fa-clipboard" ></span> Consultas</a>
            <a href="CrudHabitacion.jsp"><span class="fa fa-bed"></span>Habitaciones</a>
            
            <a href="Diagnostico.jsp"><span class="fa fa-heartbeat" ></span> Diagnostico</a>
            
            <a href="CerrarSesion"><span class="fa fa-sign-out" ></span> Salir</a>
          </li>
        </ul>

        
    	</nav>
         <div id="content" class="p-4 p-md-5">

          <nav class="navbar navbar-light bg-light">
  <form class="form-inline">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" id="txt_Buscar" onkeyup="doSearch()">
  </form>
</nav>
              <div id="resultado">
                                    
                                </div>
                                
            
            <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_Sucursal"onclick="limpiar()">Nuevo</button>
        <div class="container">
            <div class="modal fade" id="modal_Sucursal" role="dialog">
                <div class="modal-dialog ">
                    <div class="modal-content">

                        <!-- Modal content -->
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle">Sucursales</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="ControllerS?accion=Guardar" method="POST" class="form-group" enctype="multipart/form-data">
                <label for="lbl_id">ID</label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                <label for="lbl_medicina">SUCURSAL</label>
                <input type="text" name="txtSucursal" id="txt_sucursal" class="form-control"  required>
                <label for="lbl_existencia">DIRECCION</label>
                <input type="text" name="txtDireccion" id="txt_direccion" class="form-control"  required>
                <label for="lbl_imagen">IMAGEN</label>
                <input type="file" name="fileimagen" id="fileimagen" class="form-control"  required>
                
                 <br>
                 <div class="container align-content-center">
                        <button  name="accion" value="Guardar" class="btn btn-success btn-lg">Agregar</button>
                        <button  name="accion" id="btn_modificar" value="Modificar"class="btn btn-info btn-lg">Modificar</button>
                        <button  name="accion" id="btn_eliminar" value="Eliminar"class="btn btn-danger btn-lg"onclick="javascript:if(!confirm('Desea Eliminar Este Registro'))return false">Eliminar</button>
                       
                        
                        </div>
        </form>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
        


            <div class="form-group">
                <!--<table class="table table-hover">
                    <thead>
                        <tr class="text-center">
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Existencia</th>
                            <th>Fecha Ingreso</th>
                            <th>Fecha Vencimiento</th>
                            <th>Imagen</th>
                            <th>ACCION</th>
                        </tr>
                    </thead>
                    <tbody>
                  
                            <tr class="text-center">
                                <td>${p.id}</td>
                                <td>${p.nombre}</td>
                                <td>${p.existencia}</td>
                                <td>${p.fechaIngreso}</td>
                                <td>${p.fechaVencimiento}</td>
                                <td><img src="${p.ruta}" height="100" width="100"></td>
                                <td>
                                    <a href="#" class="btn btn-warning">Editar</a>
                                    <a href="#" class="btn btn-danger">Delete</a>
                                </td>
                            </tr>
                                  
                    </tbody>
                </table>   -->     
                <div class="container">

                    <div class="row">
                        <%
                            SucursalDAO sdao = new SucursalDAO();
                            List<Sucursal> lista = new ArrayList<>();
                            lista = sdao.listar();
                            
                            for (int l = 0; l < lista.size(); l++) {
                                out.println("<div class='col-12 col-md-4 mb-3' id='divMedicamento'>");
                                out.println("<div class='card'>");
                                out.println("<img src='" + lista.get(l).getRuta() + "' class='card-img-top' style='background: transparent'>");
                                out.println("  <hr/>");
                                out.println("<div class='card-body'>");
                                out.println("<strong>" + lista.get(l).getSucursal() + "</strong><br>");
                                 out.println("<a href='#' class='btn btn-outline-info' data-toggle='modal' data-target='#modal_Read'>Ver</a>");
                                out.println("</div>");
                                out.println("</div>");
                                out.println("</div>");

                            }
                            

                        %>

                      
                        
                        <div class="modal fade" id="modal_Read" role="dialog">
                <div class="modal-dialog ">
                    <div class="modal-content">

                        <!-- Modal content -->
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle">Medicamentos</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                
                                <label id="lbl_id1" style="display: none"></label>
                                <%
                                ProductoDAO pdao1 = new ProductoDAO();
                                List<Producto> listae = new ArrayList<>();
                                listae = pdao1.getMedicamentos("1");
                                for (int l = 0; l < listae.size(); l++) {
                                %>
                               
                                
                                <!--out.println("<form action='Controlador?accion=Guardar' method='POST' class='form-group' enctype='multipart/form-data'>");  
                                  out.println("<label for='lbl_id'>ID</label>");  
                                 out.println("<input type='text' name='txt_id' id='txt_id' class='form-control' value='0' readonly>");   
                                    out.println("<label for='lbl_medicina'>MEDICINA</label>"); 
                                    out.println("<input type='text' name='txtNombre' id='txt_medicina' class='form-control'  required>"); 
                                    out.println(" <label for='lbl_existencia'>EXISTENCIA</label>");
                                    out.println("<input type='text' name='txtExistencia' id='text_existencia' class='form-control'  required>"); 
                                    out.println("<label for='lbl_fi'>FECHA INGRESO</label>"); 
                                    out.println("<input type='date' name='FI' id='txt_fi' class='form-control'  required>"); 
                                    out.println("<label for='lbl_fv'>FECHA VENCIMIENTO</label>"); 
                                    out.println("<input type='date' name='FV' id='txt_fv' class='form-control'  required>"); 
                                    out.println("<label for='lbl_imagen'>IMAGEN</label>"); 
                                    out.println("<input type='file' name='fileimagen' id='fileimagen' class='form-control'  required>");out.println("<br>"); 
                                    out.println("<div class='container align-content-center'>");
                                    out.println(" <button  name='accion' value='Guardar' class='btn btn-success btn-lg'>Agregar</button>");
                                        out.println("<button  name='accion' id='btn_modificar' value='modificar'class='btn btn-info btn-lg'>Modificar</button>"); 
                                        out.println("<button  name='accion' id='btn_eliminar' value='eliminar'class='btn btn-danger btn-lg'onclick='javascript:if (!confirm('Desea Eliminar Este Registro'))");
                                        
                                    out.println("return false'>Eliminar</button>"); 


                                    out.println(" </div>");
                                out.println("</form>"); --> 
                                
                               
                            <form action="Controlador?accion=Guardar" method="POST" class="form-group" enctype="multipart/form-data">
                                    <label for="lbl_id">ID</label>
                                    <input type="text" name="txt_id" id="txt_id" class="form-control" value="" readonly>
                                    <label for="lbl_medicina">MEDICINA</label>
                                    <input type="text" name="txtNombre" id="txt_medicina" class="form-control"  required>
                                    <label for="lbl_existencia">EXISTENCIA</label>
                                    <input type="text" name="txtExistencia" id="text_existencia" class="form-control"  required>
                                    <label for="lbl_fi">FECHA INGRESO</label>
                                    <input type="date" name="FI" id="txt_fi" class="form-control"  required>
                                    <label for="lbl_fv">FECHA VENCIMIENTO</label>
                                    <input type="date" name="FV" id="txt_fv" class="form-control"  required>
                                    <label for="lbl_imagen">IMAGEN</label>
                                    <input type="file" name="fileimagen" id="fileimagen" class="form-control"  required>

                                    <br>
                                    <div class="container align-content-center">
                                        <button  name="accion" value="Guardar" class="btn btn-success btn-lg">Agregar</button>
                                        <button  name="accion" id="btn_modificar" value="modificar"class="btn btn-info btn-lg">Modificar</button>
                                        <button  name="accion" id="btn_eliminar" value="eliminar"class="btn btn-danger btn-lg"onclick="javascript:if (!confirm('Desea Eliminar Este Registro'))
                                    return false">Eliminar</button>


                                    </div>
                                </form>
                                  
                                 <% }
                                
                                
                                %>
                                
                            </div>


                        </div>
                    </div>
                </div>
            </div>
                        
                        


                    </div>        

                </div>

            </div>


        </div>
            
        </div>
                                
                        

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script>
            function buscarM(){
                $.ajax({
                    url: "BuscarMedicamento",
                    data: {
                        filtro: "Saul"
                        
                    },
                    success: function (result) {
                        $("#resultado").html(result);
                    }
                  });
    }        
        </script>
  
  </body>
</html>
