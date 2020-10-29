package controlador;

import modelo.Producto;
import modelo.ProductoDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        Producto p = new Producto();
        ProductoDAO pdao = new ProductoDAO();
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
                            p.setRuta("http://localhost/img/"+fileItem.getName());
                        } else {
                            lista.add(fileItem.getString());
                        }
                    }
                    p.setNombre(lista.get(1));
                    p.setExistencia(lista.get(2));
                    p.setFechaIngreso(lista.get(3));
                    p.setFechaVencimiento(lista.get(4));
                    pdao.agregar(p);
                } catch (Exception e) {
                }
               request.getRequestDispatcher("CrudMedicamento.jsp").forward(request, response);
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
                            p.setRuta("http://localhost/img/"+fileItem.getName());
                        } else {
                            listae.add(fileItem.getString());
                        }
                    }
                    p.setNombre(listae.get(0));
                    pdao.modificar(p);
                } catch (Exception e) {
                }
        request.getRequestDispatcher("CrudMedicamento.jsp").forward(request, response);                
                break;
            case "Eliminar":
                break;
           
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
