
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Editar Medicamento</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div   class="wrapper d-flex align-items-xl-stretch" >
            <nav id="sidebar" class="active">
                <h1><a href="Principal.jsp" class="logo">Clinica</a></h1>
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
            
            <!--Formulario para editar medicamento -->
            
            <div id="content" class="p-4 p-md-5">
                <div class="container">
                    <h3>Editar Medicamento</h3>
                    <br/>
                    <div class="form-group">
                        <form action="EditMedicinaServlet?accion=Guardar" method="POST" class="form-group" enctype="multipart/form-data">
                            <label for="lbl_id">ID</label>
                            <input type="text" name="txt_id" id="txt_id" class="form-control" readonly>
                            <label for="lbl_medicina">MEDICINA</label>
                            <input type="text" name="txtNombre" id="txt_medicina" class="form-control"  required>
                            <label for="lbl_existencia">EXISTENCIA</label>
                            <input type="text" name="txtExistencia" id="text_existencia" class="form-control"  required>
                            <label for="lbl_fi">FECHA INGRESO</label>
                            <input type="date" name="FI" id="txt_fi" class="form-control" required>
                            <label for="lbl_fv">FECHA VENCIMIENTO</label>
                            <input type="date" name="FV" id="txt_fv" class="form-control" required>
                           
                            <br>
                            <div class="container align-content-center">
                                <button  name="accion" id="btn_modificar" value="guardar"class="btn btn-info btn-lg">Modificar</button>
                                <button  name="accion" id="btn_eliminar" value="eliminar"class="btn btn-danger btn-lg"onclick="javascript:if (!confirm('Desea Eliminar Este Registro'))return false">Eliminar</button>
                            </div>
                        </form>      
                    </div>
                </div>
            </div>
        </div>



        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </body>
</html>
