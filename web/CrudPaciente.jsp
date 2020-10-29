<%-- 
    Document   : CrudPaciente
    Created on : 11-oct-2020, 23:20:52
    Author     : josue
--%>

<%@page import="modelo.Usuario"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="modelo.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Paciente</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/style.css">
                <script src="js/BuscarPaciente.js" type="text/javascript"></script>
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
            <a href="#"><span class="fa fa-bars"></span> Historial</a>
          </li>
          <li>
            <a href="CrudPaciente.jsp"><span class="fa fa-user"></span> Pacientes</a>
          </li>
          <li>
            <a href="CrudCita.jsp"><span class="fa fa-calendar-o"></span> Citas</a>
            
            <a href="CrudSucursal.jsp"><span class="fa fa-hospital-o"></span> Sucursales</a>
            
            <a href="CrudMedicamento.jsp"><span class="fa fa-plus-square"></span> Medicamentos</a>
            
            <a href="#"><span class="fa fa-clipboard" ></span> Consultas</a>
            
            <a href="#"><span class="fa fa-heartbeat" ></span> Diagnostico</a>
            
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
          
          <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_Paciente"onclick="limpiar()">Nuevo</button>
        <div class="container">
            <div class="modal fade" id="modal_Paciente" role="dialog">
  <div class="modal-dialog ">
    <div class="modal-content">

      <!-- Modal content -->
      <div class="modal-content">
	<div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Pacientes</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <form action="sr_paciente" method="post" class="form-group">
                <label for="lbl_id">ID</label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                <label for="lbl_nombres">NOMBRES</label>
                <input type="text" name="txt_nombres" id="txt_nombres" class="form-control"  required>
                <label for="text_apellidos">APELLIDOS</label>
                <input type="text" name="txt_apellidos" id="txt_apellidos" class="form-control"  required>
                <label for="lbl_direccion">DIRECCION</label>
                <input type="text" name="txt_direccion" id="txt_direccion" class="form-control"  required>
                 <label for="lbl_telefono">TELEFONO</label>
                <input type="text" name="txt_telefono" id="txt_telefono" class="form-control"  required>
                <label for="lbl_fn">FECHA NACIMIENTO</label>
                <input type="date" name="txt_fn" id="txt_fn" class="form-control"  required>
                <label for="lbl_correo">CORREO</label>
                <input type="text" name="txt_correo" id="txt_correo" class="form-control"  required>
                <label for="lbl_cui">CUI</label>
                <input type="text" name="txt_cui" id="txt_cui" class="form-control"  >
                <label for="lbl_telResponsable">Tel: Responsable</label>
                <input type="text" name="txt_telResponsable" id="txt_telResponsable" class="form-control"  >
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
        <th>Nombres</th>
        <th>Apellidos</th>
        <th>Direccion</th>
        <th>Telefono</th>
        <th>Fecha Nacimiento</th>
        <th>Correo</th>
        <th>CUI</th>
        <th>Tel: Responsable</th>
      </tr>
    </thead>
    <tbody id="tbl_empleados">
        <%
        Paciente paciente = new Paciente();
        DefaultTableModel tabla = new  DefaultTableModel();
        tabla = paciente.leerPaciente();
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
       $("#txt_nombres").val('');
       $("#txt_apellidos").val('');
       $("#txt_direccion").val('');
       $("#txt_telefono").val('');
       $("#txt_fn").val('');
       $("#txt_correo").val('');
       $("#txt_cui").val('');
       $("#txt_telResponsable").val('');
    }
    
   $('#tbl_empleados').on('click','tr td',function(evt){
       var target,id,nombres,apellidos,direccion,telefono,nacimiento,correo,cui,telResponsable; 
       target = $(event.target);
       id = target.parent().data('id');
       nombres= target.parent("tr").find("td").eq(0).html();
       apellidos = target.parent("tr").find("td").eq(1).html();
       direccion = target.parent("tr").find("td").eq(2).html();
       telefono = target.parent("tr").find("td").eq(3).html();
       nacimiento = target.parent("tr").find("td").eq(4).html();
       correo = target.parent("tr").find("td").eq(5).html();
       cui = target.parent("tr").find("td").eq(6).html();
       telResponsable = target.parent("tr").find("td").eq(7).html();
       $("#txt_id").val(id);
       $("#txt_nombres").val(nombres);
       $("#txt_apellidos").val(apellidos);
       $("#txt_direccion").val(direccion);
       $("#txt_telefono").val(telefono);
       $("#txt_fn").val(nacimiento);
       $("#txt_correo").val(correo);
       $("#txt_cui").val(cui);
       $("#txt_telResponsable").val(telResponsable);
       $("#modal_Paciente").modal('show');
    });
    
</script>
    </body>
</html>
