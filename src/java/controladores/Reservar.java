/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Gestion;
import static modelo.Gestion.PERSISTENCIA;
import modelo.dao.ReservaJpaController;
import modelo.entidades.Cliente;
import modelo.entidades.Reserva;
import modelo.entidades.Restaurante;

/**
 *
 * @author macar
 */
@WebServlet(name = "Reservar", urlPatterns = {"/Reservar"})
public class Reservar extends HttpServlet {

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
        Long id= Long.parseLong(request.getParameter("id"));
        Gestion gestion= (Gestion) request.getSession().getAttribute("gestion");
        Restaurante restaurante= gestion.buscarRestaurante(id);
        Cliente cli;
        int comensales =Integer.parseInt(request.getParameter("comensales"));
        
        String error= null;
        //Date fecha = gestion.ParseFecha(request.getParameter("fecha"));
        String fecha= request.getParameter("fecha");
        
        
        if(restaurante==null){
            error="Se ha producido un error al cargar el Paciente";
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/reservar.jsp").forward(request, response);
            System.err.println("RESTAURANTE NULOOO");
        }
        
        if(request.getParameter("reservar")!=null){
            if(request.getParameter("login")!=null){
            String login= request.getParameter("login");
            Long idCliente= gestion.buscarClienteLogin(login).getId();
            cli= gestion.buscarCliente(idCliente);
        }else{
            cli = (Cliente) request.getSession().getAttribute("usuario");
        }
            for(int i=0; i<Integer.parseInt(request.getParameter("mesas")); i++){
            String hora = request.getParameter("hora");
            Reserva reserva= new Reserva ();
            reserva.setCliente(cli);
            reserva.setFecha(gestion.parseFecha(fecha));
            reserva.setHora(hora);
            reserva.setRestaurante(restaurante);
            reserva.setComensales(comensales);
            try{
                
                    gestion.altaReserva(reserva);
      
            }catch(Exception e){
                error="No se ha podido hacer la reserva";
            }
            }
            if(error!=null){
                
            }else{
                String mensaje= "Se ha realizado la reserva";
                response.sendRedirect(response.encodeRedirectURL("index.jsp?mensaje="+mensaje));
                
            }
            
        }else{
            //Pasamos datos a la vista 
            //¿Cuantas mesas necesita?
            int mesas= gestion.mesasNecesarias(comensales);
            System.out.println("Mesas: "+mesas);
            Calendar calendario = Calendar.getInstance();
            int horaActual= calendario.get(Calendar.HOUR_OF_DAY);
            ReservaJpaController rjc = new ReservaJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
            ArrayList<String> horasDisponibles= new ArrayList();
            for (int i = 0; i < 15; i++) {
                int horaEnNumeros= 10+i;
                String hora= 10+i +":00";
                String fechaReserva=fecha.replace("-", "/");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String fechaHoy = sdf.format(new Date());
                
                List<Reserva> mesasOcupadas = rjc.reservasHoraRestaurante(fecha, restaurante ,hora );
                int mesasLibres= restaurante.getMesas()- mesasOcupadas.size();
                if(fechaReserva.equals(fechaHoy)){
                    if(mesasLibres >= mesas && horaEnNumeros>horaActual){
                    horasDisponibles.add(hora);
                    
                    }
                }else{
                    if(mesasLibres >= mesas ){
                    horasDisponibles.add(hora);
                    
                    }
                }
                
            }
            request.setAttribute("horas", horasDisponibles);
            request.setAttribute("comensales", comensales);
            request.setAttribute("fecha", fecha);
            request.setAttribute("restaurante", restaurante);
            request.setAttribute("mesas", mesas);
            getServletContext().getRequestDispatcher("/confirmarReserva.jsp").forward(request, response);
            
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
