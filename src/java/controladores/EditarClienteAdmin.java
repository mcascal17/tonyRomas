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

/**
 *
 * @author macar
 */
@WebServlet(name = "EditarUsuario", urlPatterns = {"/admin/EditarClienteAdmin"})
public class EditarClienteAdmin extends HttpServlet {

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
        Gestion gestion= (Gestion) request.getSession().getAttribute("gestion");
        Cliente cli = gestion.buscarCliente(id);
        String error = null;
        System.out.println("Hola");
        
        if(cli==null){
            error="Se ha producido un error al actualizar el cliente";
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/admin/administrar.jsp").forward(request, response);
        }
        //Si recibimos datos del formulario
        if(request.getParameter("actualizar")!= null){
            //Actualizamos los datos del cliente
            cli.setNombre(request.getParameter("nombre"));
            cli.setApellidos(request.getParameter("apellidos"));
            cli.setEmail(request.getParameter("email"));
            if(!request.getParameter("confirmacion").equals("")){
                if(request.getParameter("password").equals(request.getParameter("confirmacion"))){
                    cli.setContraseña(request.getParameter("password"));
                    System.out.println(cli.getContraseña());
                    System.out.println(error);
                }else{
                    error= "Las contraseñas no coinciden";
                    System.out.println(error);
                
                }
            }
            
            
            
            
            if(!request.getParameter("password").isEmpty()){
                cli.setContraseña(request.getParameter("password"));
            }
            System.out.println("PREVIO A IF!!!");
            if(error==null){
                System.out.println("ERROR DIFERENTE DE NULO");
                try{
                    System.out.println("ENTRA A ACTUALIZAR");
                    gestion.actualizarCliente(cli);
                }catch(Exception e){
                    System.err.println(e.getClass().getName()+" : "+e.getMessage());
                    response.sendRedirect("/admin/administrar.jsp");
                    return; 
                }
                response.sendRedirect("administrar.jsp");
                
            }else{
                System.out.println("Entra en el error");
                System.out.println(error);
                request.setAttribute("id", cli.getId());
                request.setAttribute("nombre", cli.getNombre());
                request.setAttribute("apellidos", cli.getApellidos());
                request.setAttribute("login", cli.getLogin());
                request.setAttribute("password", cli.getContraseña());
                request.setAttribute("email", cli.getEmail());
                request.setAttribute("error", error);
                getServletContext().getRequestDispatcher("/administrar.jsp").forward(request, response);
            }
        }else{
            //Pasamos datos a la vistas
            
            request.setAttribute("id", cli.getId());
            request.setAttribute("nombre", cli.getNombre());
            request.setAttribute("apellidos", cli.getApellidos());
            request.setAttribute("login", cli.getLogin());
            request.setAttribute("password", cli.getContraseña());
            request.setAttribute("email", cli.getEmail());
            if(error!=null){
                request.setAttribute("error", error);
                System.out.println("Error: "+error);
            }
            
            getServletContext().getRequestDispatcher("/admin/editarPerfil.jsp").forward(request, response);
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
