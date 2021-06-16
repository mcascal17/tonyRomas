/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Gestion;
import modelo.entidades.Empleado;

/**
 *
 * @author macar
 */
@WebServlet(name = "EditarEmpleado", urlPatterns = {"/admin/EditarEmpleado"})
public class EditarEmpleado extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Gestion gestion= (Gestion) request.getSession().getAttribute("gestion");
        Empleado emp = gestion.buscarEmpleado(id);
        String error= null;
        
        if(emp==null){
            error="Se ha producido un error al actualizar el empleado";
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/admin/administrar.jsp").forward(request, response);
        }
        if(request.getParameter("actualizar")!=null){
            emp.setApellidos(request.getParameter("apellidos"));
            emp.setEmail(request.getParameter("email"));
            emp.setNombre(request.getParameter("nombre"));
            if(!request.getParameter("confirmacion").equals("")){
                if(request.getParameter("password").equals(request.getParameter("confirmacion"))){
                    emp.setPassword(request.getParameter("password"));
                }else{
                    error="Las contraseñas no coinciden";
                    request.setAttribute("id", emp.getId());
                    request.setAttribute("error", error);
                    request.setAttribute("nombre",emp.getNombre() );
                    request.setAttribute("apellidos", emp.getApellidos());
                    request.setAttribute("password", request.getParameter("password"));
                request.setAttribute("email", emp.getEmail());
                    getServletContext().getRequestDispatcher("/admin/editarEmpleado.jsp").forward(request, response);
                    return;
                }
                
            }
            if(request.getParameter("administrador")!= null){
                emp.setEncargado(true);
            }else{
                emp.setEncargado(false);
            }
            if(error==null){
                try{
                    gestion.actualizarEmpleado(emp);
                }catch(Exception e){
                    System.err.println(e.getClass().getName()+" : "+e.getMessage());
                    response.sendRedirect("admin/editarEmpleado.jsp?error=Error+al+actualizar");
                }
                response.sendRedirect("administrar.jsp");
            }else{
                request.setAttribute("id", emp.getId());
                request.setAttribute("nombre", emp.getNombre());
                request.setAttribute("apellidos", emp.getApellidos());
                request.setAttribute("email", emp.getEmail());
                request.setAttribute("error", error);
                request.setAttribute("password", request.getParameter("password"));
                getServletContext().getRequestDispatcher("/admin/editarEmpleado.jsp").forward(request, response);
            }
        }else{
            //Pasamos datos a la vista
            request.setAttribute("id", emp.getId());
            request.setAttribute("nombre", emp.getNombre());
            request.setAttribute("apellidos", emp.getApellidos());
            request.setAttribute("password", emp.getPassword());
            request.setAttribute("login", emp.getLogin());
            request.setAttribute("email", emp.getEmail());
            request.setAttribute("checked", emp.isEncargado()?"checked":"");
            getServletContext().getRequestDispatcher("/admin/editarEmpleado.jsp").forward(request, response);

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
