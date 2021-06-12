/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Gestion;
import modelo.dao.ClienteJpaController;
import modelo.dao.EmpleadoJpaController;
import modelo.entidades.Cliente;
import modelo.entidades.Empleado;

/**
 *
 * @author macar
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
        String password= request.getParameter("password");
        String error= null;
        if(login == null || password==null){
            error="Debe acceder";
        }else{
            if(login.isEmpty() || password.isEmpty()){
                error="Se deben rellenar los campos de usuario y contraseña";
            }else{
                EmpleadoJpaController cjc= new
                EmpleadoJpaController(Persistence.createEntityManagerFactory("tonyRomas2PU"));
                System.err.println("HHHOOOHHHHHHHOOLAAAAAAAA");
                List<Empleado> empleados = cjc.findEmpleadoEntities();
                
                    System.out.println("ENTRA COMO CLIENTE!!!!!");
                   for(Empleado emp: empleados){
                        if(emp.getLogin().equals(login) && emp.getPassword().equals(password)){
                            //Login correcto
                            HttpSession sesion = request.getSession();
                            Gestion gestion= new Gestion();
                            Cliente cli = gestion.buscarClienteLogin(login);
                            sesion.setAttribute("usuario", emp);
                            sesion.setAttribute("gestion", new Gestion());
                            sesion.setAttribute("cliente", cli);
                            response.sendRedirect("index.jsp");
                            return;
                        }
                    }
                
                ClienteJpaController ejc= new
                    ClienteJpaController(Persistence.createEntityManagerFactory("tonyRomas2PU"));
                List<Cliente> clientes= ejc.findClienteEntities();
       
                    System.out.println("ENCONTRADO EMPLEADO!!!!!!!!");
                    for(Cliente cli : clientes){
                        if(cli.getLogin().equals(login) && cli.getContraseña().equals(password)){
                            //Login correcto
                            HttpSession sesion = request.getSession();
                            sesion.setAttribute("cliente", cli);
                            sesion.setAttribute("gestion", new Gestion());
                            response.sendRedirect("index.jsp");
                            return;
                        }
                    }
                
                error= "El usuario no está registrado";
                
                
            }
        }
        request.setAttribute("error", error);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
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
