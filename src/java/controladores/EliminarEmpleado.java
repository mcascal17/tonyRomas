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
import modelo.Gestion;
import modelo.entidades.Empleado;

/**
 *
 * @author macar
 */
@WebServlet(name = "EliminarEmpleado", urlPatterns = {"/admin/EliminarEmpleado"})
public class EliminarEmpleado extends HttpServlet {

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
        Long id=Long.parseLong(request.getParameter("id"));
        Gestion gestion= (Gestion) request.getSession().getAttribute("gestion");
        Empleado emp = gestion.buscarEmpleado(id);
        String mensaje= null;
        String error= null;
        
        if(emp==null){
            error= "Error al eliminar al empleado";
            response.sendRedirect("administrar.jsp?erro="+mensaje);
            return;
        }
        try{
            gestion.eliminarEmpleado(emp);
        }catch(Exception ex){
            error= "Error al eliminar al empleado";
        }
        
        if(error!=null){
            response.sendRedirect("administrar.jsp?error="+error);
            return;
        }else{
            mensaje= "Se ha eliminado el Empleado "+emp.getNombre()+" "+emp.getApellidos();
            request.setAttribute("mensaje", mensaje);
            getServletContext().getRequestDispatcher("/admin/administrar.jsp").forward(request, response);
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
