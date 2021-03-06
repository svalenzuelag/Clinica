<%-- 
    Document   : CrudCita
    Created on : 26-oct-2020, 13:57:39
    Author     : josue
--%>

<%@page import="modelo.CitaMedica"%>
<%@page import="modelo.Sucursal"%>
<%@page import="java.util.HashMap"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.UsuarioDAO" %>
<%@page import="modelo.Usuario" %>
<%@page import="modelo.Empleado" %>

<!doctype html>
<html lang="es">
  <head>
  	<title>Citas Medicas</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/style.css">
                <script src="js/BuscarCita.js" type="text/javascript"></script>
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

            <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_Cita"onclick="limpiar()">Nuevo</button>
        <div class="container">
            <div class="modal fade" id="modal_Cita" role="dialog">
  <div class="modal-dialog ">
    <div class="modal-content">

      <!-- Modal content -->
      <div class="modal-content">
	<div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Cita Medica</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <form action="sr_citaMedica" method="post" class="form-group">
                <label for="lbl_id">ID</label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                 <label for="lbl_fn">FECHA CITA</label>
                <input type="date" name="txt_cita" id="txt_cita" class="form-control"  required>
                <label for="lbl_usuario">SUCURSAL</label>
                <select name="drop_sucursal" id="drop_sucursal" class="form-control">
                    <%
                        Sucursal sucursal = new Sucursal();
                        HashMap<String,String> dropSucursal = sucursal.drop_sucursal();
                                for (String i: dropSucursal.keySet()){
                        out.println("<option value='"+i+"'>"+ dropSucursal.get(i)+"</option>");
                        }
                    %>
                </select>
                <label for="lbl_nombres">NOMBRES</label>
                <input type="text" name="txt_nombres" id="txt_nombres" class="form-control"  required>
                <label for="text_apellidos">APELLIDOS</label>
                <input type="text" name="txt_apellidos" id="txt_apellidos" class="form-control"  required>
                 <label for="lbl_telefono">TELEFONO</label>
                <input type="text" name="txt_telefono" id="txt_telefono" class="form-control"  required>
                <label for="lbl_correo">CORREO</label>
                <input type="text" name="txt_correo" id="txt_correo" class="form-control"  required>
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
          
          <table class="table" id="tblCita">
    <thead class="thead-dark" style="text-align: center">
      <tr>
        <th>Fecha</th>
        <th>Sucursal</th>
        <th>Nombres</th>
        <th>Apellidos</th>
        <th>Telefono</th>
        <th>Correo</th>
      </tr>
    </thead>
    <tbody id="tbl_cita">
        <%
        CitaMedica citaMedida = new CitaMedica();
        DefaultTableModel tabla = new  DefaultTableModel();
        tabla = citaMedida.leerCita();
        for ( int t=0; t<tabla.getRowCount();t++){
            out.println("<tr data-id="+ tabla.getValueAt(t, 0) +">");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,1)+"</td>");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,2)+"</td>");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,3)+"</td>");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,4)+"</td>");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,5)+"</td>");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,6)+"</td>");
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
       $("#txt_cita").val('');
       $("#txt_sucursal").val('');
       $("#txt_nombres").val('');
       $("#txt_apellidos").val('');
       $("#txt_telefono").val('');
       $("#txt_correo").val('');
    }
    
   $('#tbl_cita').on('click','tr td',function(evt){
       var target,id,fecha,sucursal,nombres,apellidos,telefono,correo; 
       target = $(event.target);
       id = target.parent().data('id');
       fecha= target.parent("tr").find("td").eq(0).html();
       sucursal= target.parent("tr").find("td").eq(1).html();
       nombres= target.parent("tr").find("td").eq(2).html();
       apellidos = target.parent("tr").find("td").eq(3).html();
       telefono = target.parent("tr").find("td").eq(4).html();
       correo = target.parent("tr").find("td").eq(5).html();
       $("#txt_id").val(id);
       $("#txt_cita").val(fecha);
       $("txt_sucursal").val(sucursal);
       $("#txt_nombres").val(nombres);
       $("#txt_apellidos").val(apellidos);
       $("#txt_telefono").val(telefono);
       $("#txt_correo").val(correo);
       $("#modal_Cita").modal('show');
    });
   
</script>

  </body>
</html>