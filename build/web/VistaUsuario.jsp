<%-- 
    Document   : VistaUsuario
    Created on : 10-oct-2020, 21:24:00
    Author     : josue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema Usuario</title>
            <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/style.css">
    </head>
    <body>

        	<div   class="wrapper d-flex align-items-xl-stretch" >
			<nav id="sidebar" class="active">
				<h1><a href="VistaUsuario.jsp" class="logo">Clinica</a></h1>
        <ul class="list-unstyled components mb-5">
          <li class="active">
            <a href="VistaUsuario.jsp"><span class="fa fa-home"></span> Inicio</a>
          </li>
          
          <li>
            <a href="CrudUsuario.jsp"><span class="fa fa-bars"></span> Historial</a>
          </li>
          <li>
            <a href="#"><span class="fa fa-user"></span> Pacientes</a>
          </li>
          <li>
            <a href="#"><span class="fa fa-calendar-o"></span> Citas</a>
            
            <a href="#"><span class="fa fa-hospital-o"></span> Sucursales</a>
            
            <a href="#"><span class="fa fa-plus-square"></span> Medicamentos</a>
            
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

        <div class="container mt-4">
            <h1>Bienvenido al Sistema  <strong style="text-transform: capitalize">${usuario}</strong></h1><br><br>
            
            <div class="card-group">
                <div class="card" style="margin-right: 10px">
    <img class="card-img-top img-fluid" src="imagen/consulta.jpg" style="height: 300px" alt="Card image cap">
    <div class="card-body">
      <h5 class="card-title">Consulta General</h5>
      <p class="card-text"></p>
    </div>
  </div>
  <div class="card" style="margin-right: 10px">
      <img class="card-img-top img-fluid" src="imagen/pediatria.jpg" style="height: 300px" alt="Card image cap">
    <div class="card-body">
      <h5 class="card-title">Pediatria</h5>
      <p class="card-text"></p>
      
    </div>
  </div>
  <div class="card" style="margin-right: 10px">
    <img class="card-img-top" src="imagen/examenes.jpg" style="height: 300px" alt="Card image cap">
    <div class="card-body">
      <h5 class="card-title">Exámenes Medicos</h5>
      <p class="card-text"></p>
    </div>
  </div>
</div>
        </div>
      </div>
		</div>

    <script src="js/jquery.min.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
  </body>
</html>