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
import modelo.entidades.Restaurante;

/**
 *
 * @author macar
 */
@WebServlet(name = "EditarRestaurante", urlPatterns = {"/admin/EditarRestaurante"})
public class EditarRestaurante extends HttpServlet {

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
        Long id= Long.parseLong(request.getParameter("id"));
        System.out.println("ID: "+id);
        Gestion gestion=(Gestion) request.getSession().getAttribute("gestion");
        Restaurante res = gestion.buscarRestaurante(id);
        String error= null;
        
        if(res== null){
            error="Se ha producido un error al actualizar el restaurante";
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/admin/administrar.jsp").forward(request, response);
        }
        //Si recibimos datos del formulario
        System.out.println("PULSAMOS ACTUALIZAR!!!!!");
        res.setNombre(request.getParameter("nombre"));
        res.setDireccion(request.getParameter("direccion"));
        res.setCif(request.getParameter("cif"));
        res.setPropietario(request.getParameter("propietario"));
        res.setProvincia(request.getParameter("provincia"));
        res.setMesas((Integer.parseInt(request.getParameter("mesas"))));
        try{
            gestion.actualizarRestaurante(res);
        }catch(Exception e){
            System.err.println(e.getClass().getName()+" : "+e.getMessage());
            response.sendRedirect("/admin/administrar.jsp");
        }
        response.sendRedirect("administrar.jsp");
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
