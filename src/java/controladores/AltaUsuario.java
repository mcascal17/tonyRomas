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

/**
 *
 * @author macar
 */
@WebServlet(name = "AltaUsuario", urlPatterns = {"/AltaUsuario"})
public class AltaUsuario extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession();
        sesion.setAttribute("gestion", new Gestion());
        String error= null;
        String nombre= request.getParameter("nombre");
        String apellidos= request.getParameter("apellidos");
        String login= request.getParameter("login");
        String password=request.getParameter("password");
        String email= request.getParameter("email");
        String confirmacion= request.getParameter("confirmacion");
        Cliente nuevo = new Cliente();
        nuevo.setApellidos(apellidos);
        nuevo.setNombre(nombre);
        nuevo.setEmail(email);
        nuevo.setLogin(login);
        System.out.println("Contraseña: "+ password);
        System.out.println("Confirmacion: "+ confirmacion);
        if(password.equals(confirmacion)){
            nuevo.setContraseña(password);
            System.out.println("Contraseña VALIDA!!!");
        }else{
            System.out.println("NO COINCIDE LA CONTRASEÑA!!!");
            error="Las contraseñas deben coincidir";
            request.setAttribute("error", error);
            request.setAttribute("nombre", nombre);
            request.setAttribute("apellidos", apellidos);
            request.setAttribute("email", email);
            request.setAttribute("login", login);
            getServletContext().getRequestDispatcher("/registroUsuario.jsp").forward(request, response);
            //response.sendRedirect(response.encodeRedirectURL("registroUsuario.jsp?error="+error));
            
        }
        
        Gestion gestion = (Gestion) request.getSession().getAttribute("gestion");
        try{
            gestion.altaCliente(nuevo);
        }catch(Exception ex){
            error="Ya existen un usuario con el login "+login;
            System.err.println(ex.getClass()+ " : "+ ex.getMessage()+" : "+ex.getLocalizedMessage()+" : "+ex.toString());
        }
        if(error!=null){
            System.out.println("ERROR != NULL");
            request.setAttribute("error", error);
            request.setAttribute("nombre", nombre);
            request.setAttribute("apellidos", apellidos);
            request.setAttribute("email", email);
            request.setAttribute("login", login);
            getServletContext().getRequestDispatcher("/registroUsuario.jsp").forward(request, response);
        }else{
            
            String mensaje="Se ha dado de alta al cliente";
            response.sendRedirect(response.encodeRedirectURL("index.jsp?mensaje="+mensaje));
            System.out.println("mensaje");
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
