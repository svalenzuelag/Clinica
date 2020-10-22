/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Medicamento;
import modelo.MedicamentoDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author josue
 */
public class sr_medicamento extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    Medicamento medicamento;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
          String accion = request.getParameter("accion");
        Medicamento m = new Medicamento();
        MedicamentoDAO mdao = new MedicamentoDAO();
        switch (accion) {
            case "Guardar":
                ArrayList<String> lista = new ArrayList<>();
                try {
                    FileItemFactory file = new DiskFileItemFactory();
                    ServletFileUpload fileUpload = new ServletFileUpload(file);
                    List items = fileUpload.parseRequest(request);
                    for (int i = 0; i < items.size(); i++) {
                        FileItem fileItem = (FileItem) items.get(i);
                        if (!fileItem.isFormField()) {
                            File f = new File("C:\\xampp\\htdocs\\img\\"+ fileItem.getName());
                            fileItem.write(f);
                            m.setRuta("http://localhost/img/"+fileItem.getName());
                        } else {
                            lista.add(fileItem.getString());
                        }
                    }
                    m.setNombre(lista.get(0));
                    m.setExistencia(lista.get(1));
                    m.setFechaIngreso(lista.get(2));
                    m.setFechaVencimiento(lista.get(3));
                    mdao.agregar(m);
                } catch (Exception e) {
                }
                request.getRequestDispatcher("sr_medicamento?accion=Listar").forward(request, response);
                break;
                
            case "Editar":
                ArrayList<String> listae = new ArrayList<>();
                try {
                    FileItemFactory file = new DiskFileItemFactory();
                    ServletFileUpload fileUpload = new ServletFileUpload(file);
                    List items = fileUpload.parseRequest(request);
                    for (int i = 0; i < items.size(); i++) {
                        FileItem fileItem = (FileItem) items.get(i);
                        if (!fileItem.isFormField()) {
                            File f = new File("C:\\xampp\\htdocs\\img\\"+ fileItem.getName());
                            fileItem.write(f);
                            m.setRuta("http://localhost/img/"+fileItem.getName());
                        } else {
                            listae.add(fileItem.getString());
                        }
                    }
                    m.setNombre(listae.get(0));
                    mdao.modificar(m);
                } catch (Exception e) {
                }
                request.getRequestDispatcher("sr_medicamento?accion=Listar").forward(request, response);
                
                break;
            case "Eliminar":
                break;
            case "Listar":
                List<Medicamento> medicamentos=mdao.listar();
                request.setAttribute("medicamentos", medicamentos);
                request.getRequestDispatcher("CrudMedicamento.jsp").forward(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
