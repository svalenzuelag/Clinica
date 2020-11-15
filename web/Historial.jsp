<%-- 
    Document   : Historial
    Created on : 14-nov-2020, 18:54:51
    Author     : josue
--%>

<%@page import="modelo.HistorialDAO"%>
<%@page import="modelo.Diagnostico"%>
<%@page import="modelo.ConsultaDAO"%>
<%@page import="java.util.HashMap"%>
<%@page import="modelo.Sucursal"%>
<%@page import="modelo.Paciente"%>
<%@page import="modelo.Usuario"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="modelo.Habitacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Habitaciones</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/style.css">
                <script src="js/BuscarHabitacion.js" type="text/javascript"></script>
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

        <!-- Page Content  -->
      <div id="content" class="p-4 p-md-5">

          <nav class="navbar navbar-light bg-light">
  <form class="form-inline">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" id="txt_Buscar" onkeyup="doSearch()"/>
    
  </form>
</nav>
          
          <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_consulta"onclick="limpiar()">Nuevo</button>
        <div class="container">
            <div class="modal fade" id="modal_consulta" role="dialog">
  <div class="modal-dialog ">
    <div class="modal-content">

      <!-- Modal content -->
      <div class="modal-content">
	<div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Consultas</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <form action="sr_historial" method="post" class="form-group">
                <label for="lbl_id">ID</label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                <label for="lbl_descripcion">DESCRIPCION</label>
                <input type="text" name="txt_descripcion" id="txt_descripcion" class="form-control"  required>
                <label for="lbl_azucar">PACIENTE</label>
                <select name="drop_paciente" id="drop_paciente" class="form-control">
                    <%
                        Paciente paciente = new Paciente ();
                          HashMap<String,String> dropPaciente = paciente.drop_paciente();
                             for (String i: dropPaciente.keySet()){
                        out.println("<option value='"+i+"'>"+ dropPaciente.get(i)+"</option>");
                        }
                    %>
                </select>
                 <label for="lbl_sucursal">DIAGNOSTICO</label>
                <select  name="drop_diagnostico" id="drop_diagnostico" class="form-control">
                    <%
                            Diagnostico diagnostico = new Diagnostico();
                        HashMap<String,String> dropDiagnostico = diagnostico.drop_diagnostico();
                                for (String i: dropDiagnostico.keySet()){
                        out.println("<option value='"+i+"'>"+ dropDiagnostico.get(i)+"</option>");
                        }
                        %>
                </select>
                <label for="lbl_fecha">Fecha</label>
                <input type="date" name="txt_fecha" id="txt_fecha" class="form-control"  required>
                 <br>
                 <div class="container align-content-center">
                        <button  name="btn_agregar" id="btn_agregar" value="agregar"class="btn btn-success btn-lg">Agregar</button>
                        <button  name="btn_modificar" id="btn_modificar" value="modificar"class="btn btn-info btn-lg">Modificar</button>
                        <button  name="btn_eliminar" id="btn_eliminar" value="eliminar"class="btn btn-danger btn-lg"onclick="javascript:if(!confirm('Desea Eliminar Este Registro'))return false">Eliminar</button>
                       
                        
                        </div>
        </form>
      </div>
      

    </div>
  </div>
</div>
</div>
     <br>
          
          <br>

          
          <table class="table" id="tblPaciente">
    <thead class="thead-dark" style="text-align: center">
      <tr>
        <th>Descripcion</th>
        <th>Fecha</th>
        <th>Nombres</th>
        <th>Apellidos</th>
        <th>Consulta</th>
        <th>Presion</th>
        <th>Azucar</th>
        <th>Glucosa</th>
      </tr>
    </thead>
    <tbody id="tbl_empleados">
        <%
        HistorialDAO historial = new HistorialDAO();
        DefaultTableModel tabla = new  DefaultTableModel();
        tabla = historial.leerHistorial();
        for ( int t=0; t<tabla.getRowCount();t++){
            out.println("<tr data-id="+ tabla.getValueAt(t, 0) +">");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,1)+"</td>");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,2)+"</td>");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,3)+"</td>");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,4)+"</td>");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,5)+"</td>");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,6)+"</td>");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,7)+"</td>");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,8)+"</td>");
            out.println("</tr>");
        }
        %>
    </tbody>
  </table>
</div>
      </div>  
        
    <script src="js/jquery.min.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
    <script type="text/javascript">
    function limpiar(){
       $("#txt_id").val(0);
       $("#txt_descripcion").val('');
       $("#txt_fecha").val('');
       
    }
    
    
   $('#tbl_empleados').on('click','tr td',function(evt){
       var target,id,descripcion,presion,azucar,glucosa; 
       target = $(event.target);
       id = target.parent().data('id');
       descripcion= target.parent("tr").find("td").eq(0).html();
       presion = target.parent("tr").find("td").eq(1).html();
       azucar = target.parent("tr").find("td").eq(2).html();
       glucosa = target.parent("tr").find("td").eq(3).html();
       $("#txt_id").val(id);
       $("#txt_descripcion").val(descripcion);
       $("#txt_fecha").val(presion);
       
       $("#modal_consulta").modal('show');
    });
    
</script>
    </body>
</html>
