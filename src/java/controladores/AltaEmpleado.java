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
import modelo.entidades.Cliente;
import modelo.entidades.Empleado;

/**
 *
 * @author macar
 */
@WebServlet(name = "AltaEmpleado", urlPatterns = {"/admin/AltaEmpleado"})
public class AltaEmpleado extends HttpServlet {

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
        String error = null;
        String nombre= request.getParameter("nombre");
        String apellidos= request.getParameter("apellidos");
        String login= request.getParameter("login");
        boolean administrador = (request.getParameter("administrador")!= null);
        String password= request.getParameter("password");
        String email= request.getParameter("email");
        String confirmacion= request.getParameter("confirmacion");
        Gestion gestion= (Gestion) request.getSession().getAttribute("gestion");
        Empleado emp = new Empleado();
        if(!password.equals(confirmacion)){
            error= "Las contraseñas introducidad no coincidesn";
            response.sendRedirect(response.encodeRedirectURL("administrar.jsp?error="+error));
            return;
        }
        emp.setApellidos(apellidos);
        emp.setEmail(email);
        emp.setNombre(nombre);
        emp.setEncargado(administrador);
        emp.setPassword(password);
        emp.setLogin(login);
        
        
        
        Cliente cli = new Cliente();
        cli.setApellidos(apellidos);
        cli.setContraseña(password);
        cli.setEmail(email);
        cli.setNombre(nombre);
        cli.setLogin(login);
        //Creamos cliente primero ya que , si hay un empleado con ese login
        // habrá un cliente tambien, si gestiono primero el empleado, podría crearse
        //un empleado sin fallos pero me tiraría para atras el cliente si ya existe
        try{
            gestion.altaCliente(cli);
        }catch(Exception e){
            error= "Ya existe un cliente con el nombre"+login;
        }
        
        try{
            gestion.altaEmpleado(emp);
        }catch(Exception e){
            error= "Ya existe un empleado con el nombre"+login;
        }
        
        if(error != null){
            request.setAttribute("error", error);
            request.setAttribute("nombre", nombre);
            request.setAttribute("apellidos", apellidos);
            request.setAttribute("login", login);
            request.setAttribute("email", email);
            request.setAttribute("checked", administrador?"checked":"");
            getServletContext().getRequestDispatcher("/admin/administracion.jsp").forward(request, response);
        }else{
            String mensaje = "Se ha dado de alta al empleado";
            response.sendRedirect(response.encodeRedirectURL("administrar.jsp?mensaje="+mensaje));
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
