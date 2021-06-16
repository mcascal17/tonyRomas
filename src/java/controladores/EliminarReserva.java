/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Gestion;
import modelo.entidades.Reserva;

/**
 *
 * @author macar
 */
@WebServlet(name = "EliminarReserva", urlPatterns = {"/admin/EliminarReserva"})
public class EliminarReserva extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            Long id=Long.parseLong(request.getParameter("id"));
            Gestion gestion= (Gestion) request.getSession().getAttribute("gestion");
            Reserva res = gestion.buscarReserva(id);
            String from ="tonyRomas2021@gmail.com";
            String to = res.getCliente().getEmail();
            String subject= "Cancelacion reserva";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String cadenaHoy = sdf.format(res.getFecha());
            String text = "Estimado "+res.getCliente().getNombre()+", le informamos que la reserva "+
                    "que tenía para el día "+cadenaHoy+" en el restaurante "+res.getRestaurante().getNombre()+
                    " ha sido cancelada";
            String password = "tonys2021";
            try{
                gestion.eliminarReserva(res);
                //gestion.enviarEmail(from, to, subject, text, password);
            }catch(Exception e){
                String error= "Error al eliminar la reserva";
                response.sendRedirect("administrar.jsp?error="+error);
            }
            String mensaje= "Se ha eliminado la reserva";
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
