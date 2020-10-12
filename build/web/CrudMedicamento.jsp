<%-- 
    Document   : CrudMedicamento
    Created on : 12-oct-2020, 14:03:33
    Author     : josue
--%>

<%@page import="modelo.Medicamento"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medicamentos</title>
        
        <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/style.css">
  </head>
  <body>
		
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
            <a href="#"><span class="fa fa-calendar-o"></span> Citas</a>
            
            <a href="#"><span class="fa fa-hospital-o"></span> Sucursales</a>
            
            <a href="CrudMedicamento.jsp"><span class="fa fa-plus-square"></span> Medicamentos</a>
            
            <a href="#"><span class="fa fa-clipboard" ></span> Consultas</a>
            
            <a href="#"><span class="fa fa-heartbeat" ></span> Diagnostico</a>
            
            <a href="login?accion=Salir"><span class="fa fa-sign-out" ></span> Salir</a>
          </li>
        </ul>

        
    	</nav>

        <!-- Page Content  -->
      <div id="content" class="p-4 p-md-5">

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <div class="container-fluid">

            <button type="button" id="sidebarCollapse" class="btn btn-primary">
              <i class="fa fa-bars"></i>
              <span class="sr-only">Toggle Menu</span>
            </button>
 
        </nav>
          
          <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_Medicamento"onclick="limpiar()">Nuevo</button>
        <div class="container">
            <div class="modal fade" id="modal_Medicamento" role="dialog">
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
      <form action="sr_medicamento" method="post" class="form-group">
                <label for="lbl_id">ID</label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                <label for="lbl_medicina">MEDICINA</label>
                <input type="text" name="txt_medicina" id="txt_medicina" class="form-control"  required>
                <label for="lbl_existencia">EXISTENCIA</label>
                <input type="text" name="text_existencia" id="text_existencia" class="form-control"  required>
                <label for="lbl_fi">FECHA INGRESO</label>
                <input type="date" name="txt_fi" id="txt_fi" class="form-control"  required>
                 <label for="lbl_fv">FECHA VENCIMIENTO</label>
                <input type="date" name="txt_fv" id="txt_fv" class="form-control"  required>
                <label for="lbl_imagen">IMAGEN</label>
                <input type="text" name="txt_imagen" id="txt_imagen" class="form-control"  required>
                
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
           
           <table class="table">
    <thead class="thead-dark" style="text-align: center">
      <tr>
        <th>Medicamentos</th>
        <th>Existencia</th>
        <th>Fecha Ingreso</th>
        <th>Fecha Vencimiento</th>
        <th>Imagen</th>
      </tr>
    </thead>
    <tbody id="tbl_medicamento">
        <%
        Medicamento medicamento = new Medicamento();
        DefaultTableModel tabla = new  DefaultTableModel();
        tabla = medicamento.leerMedicamento();
        for ( int t=0; t<tabla.getRowCount();t++){
            out.println("<tr data-id="+ tabla.getValueAt(t, 0) +">");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,1)+"</td>");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,2)+"</td>");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,3)+"</td>");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,4)+"</td>");
            out.println("<td style='text-align: center'>"+tabla.getValueAt(t,5)+"</td>");
            out.println("</tr>");
        }
        %>
    </tbody>
  </table>
  
            
        </div>
          
</div>
      </div>
        
        <script src="js/jquery.min.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
    <script type="text/javascript">
    function limpiar(){
        $("#txt_id").val(0);
       $("#txt_medicina").val('');
       $("#text_existencia").val('');
       $("#txt_fi").val('');
       $("#txt_fv").val('');
       $("#txt_imagen").val('');
      
    }
    
   $('#tbl_medicamento').on('click','tr td',function(evt){
       var target,id,medicina,existencia,fecha_ingreso,fecha_vencimiento,imagen; 
       target = $(event.target);
       id = target.parent().data('id');
       medicina = target.parent("tr").find("td").eq(0).html();
       existencia = target.parent("tr").find("td").eq(1).html();
       fecha_ingreso = target.parent("tr").find("td").eq(2).html();
       fecha_vencimiento = target.parent("tr").find("td").eq(3).html();
       imagen = target.parent("tr").find("td").eq(4).html();
       $("#txt_id").val(id);
       $("#txt_medicina").val(medicina);
       $("#text_existencia").val(existencia);
       $("#txt_fi").val(fecha_ingreso);
       $("#txt_fv").val(fecha_vencimiento);
       $("#txt_imagen").val(imagen);
       $("#modal_Medicamento").modal('show');
    });
    
</script>
    </body>
</html>
