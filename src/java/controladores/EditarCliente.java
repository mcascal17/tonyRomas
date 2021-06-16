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
import modelo.entidades.Cliente;
import modelo.entidades.Empleado;

/**
 *
 * @author macar
 */
@WebServlet(name = "EditarCliente", urlPatterns = {"/EditarCliente"})
public class EditarCliente extends HttpServlet {

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
        String login= request.getParameter("login");
        Gestion gestion= (Gestion) request.getSession().getAttribute("gestion");
        Cliente cli = gestion.buscarClienteLogin(login);
        String error = null;
        
        if(cli==null){
            error="Se ha producido un error al actualizar el empleado";
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/miPerfil.jsp").forward(request, response);
        }
        if(request.getParameter("actualizar")!=null){
            cli.setNombre(request.getParameter("nombre"));
            cli.setApellidos(request.getParameter("apellidos"));
            cli.setEmail(request.getParameter("email"));
            if(!request.getParameter("confirmacion").equals("")){
                if(request.getParameter("password").equals(request.getParameter("confirmacion"))){
                    cli.setContraseña(request.getParameter("password"));
                }else{
                    error="Las contraseñas no coinciden";
                    request.setAttribute("error", error);
                    getServletContext().getRequestDispatcher("/miPerfil.jsp").forward(request, response);
                    return;
                }
            }
            if(error==null){
                try{
                    gestion.actualizarCliente(cli);
                    
                }catch(Exception e){
                    System.err.println(e.getClass().getName()+" : "+e.getMessage());
                    response.sendRedirect("miPerfil.jsp?error=Error+al+actualizar+cliente");
                }
                
                    HttpSession sesion= request.getSession();
                    sesion.removeAttribute("cliente");
                    sesion.setAttribute("cliente", cli);
                
                response.sendRedirect("miPerfil.jsp");
            }
        }else{
            //Pasamos datos a la vista
            request.setAttribute("id", cli.getId());
            request.setAttribute("nombre", cli.getNombre());
            request.setAttribute("apellidos", cli.getApellidos());
            request.setAttribute("password", cli.getContraseña());
            request.setAttribute("login", cli.getLogin());
            request.setAttribute("email", cli.getEmail());
            getServletContext().getRequestDispatcher("/editarPerfil.jsp").forward(request, response);
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
