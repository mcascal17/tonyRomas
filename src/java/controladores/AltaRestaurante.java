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
@WebServlet(name = "AltaRestaurante", urlPatterns = {"/admin/AltaRestaurante"})
public class AltaRestaurante extends HttpServlet {

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
        String error= null;
        String nombre= request.getParameter("nombre");
        String provincia= request.getParameter("provincia");
        String cif= request.getParameter("cif");
        String propietario= request.getParameter("propietario");
        String direccion= request.getParameter("direccion");
        int mesas= Integer.parseInt(request.getParameter("mesas"));
        
        //Comprobamos que el formulario contiene o no la imagen
        /*if(request.getPart("foto").getSize()>0){
            //Nos aseguramos que el fichero es una imagen y que no excede unos 8mb
            if(request.getPart("foto").getContentType().contains("image")==false ||
                    request.getPart("foto").getSize()>8388608){
                //TIPO DE ARCHIVO NO VALIDO
                request.setAttribute("resultado", "Archivo no válido");
                request.getRequestDispatcher("/admin/administrar.jsp").forward(request, response);
                return;
            }else{
                //Obtenemos la ruta absoluta del sistema donde queremos guardar la imagen
                String fileName= this.getServletContext().getRealPath("/images/restaurantes/imagen");
                //Guardamos la imagen en disco con la ruta que hemos obtenido en el paso anterior
            }
        }*/
        
        /*try{
            String nombreFichero= "";
            String extension= "";
            //BufferedReader lectorNombre= new BufferedReader (new InputStreamReader(request.getPart("nombreFichero").getInputStream()));
            nombreFichero="restaurante"+request.getParameter("nombre");
            //lectorNombre.close();
            Part datosSubidos=request.getPart("foto");
            if(datosSubidos == null){//No se ha subido el fichero
                error="No se ha subido la imagen";
            }else{
                if(datosSubidos.getSize()>100*1024){//Fichero demasiado grande
                    error="No se permiten fichero superiores a 100kb";
                }else{
                    if(datosSubidos.getContentType().indexOf("image")==1){
                        error="El fichero recibido no es una imagen";
                    }else{
                        String tipoContenido= datosSubidos.getContentType();
                        int posicion= tipoContenido.indexOf("/");
                        extension=tipoContenido.substring(posicion+1);
                    }
                    if(error==null){
                        nombreFichero=nombreFichero+"."+extension;
                        String ruta= request.getServletContext().getRealPath("WEB-INF/usuarios"+nombreFichero);
                        ServletContext context = request.getServletContext();
                        InputStream is = context.getResourceAsStream("generate.xml");
                        File directorioUsuario = new File(ruta);
                        directorioUsuario.mkdir();
                        FileOutputStream fichero = new FileOutputStream(ruta);
                        InputStream contenido = datosSubidos.getInputStream();
                        byte[] bytes = new byte [2048];
                        while(contenido.available()>0){
                            int longitud = contenido.read(bytes);
                            fichero.write(bytes,0,longitud);
                            System.err.println("DENTRO DEL WHILE");
                        }
                        fichero.close();
                    }
                    request.setAttribute("nombreFichero", nombreFichero);
                    
                }
                request.getRequestDispatcher(response.encodeURL("admin/administrar.jsp")).forward(request, response);
            }
            
        }catch(Exception e){
            e.printStackTrace();
            System.err.println("ERRRROOOOR SUBIR IMAGEN");
        }*/
        Restaurante res = new Restaurante();
        res.setCif(cif);
        res.setDireccion(direccion);
        res.setNombre(nombre);
        res.setMesas(mesas);
        res.setPropietario(propietario);
        res.setProvincia(provincia);
        Gestion gestion = (Gestion) request.getSession().getAttribute("gestion");
        try{
            gestion.altaRestaurante(res);
        }catch(Exception ex){
            error="Ya existe un restaurante con el nombre "+nombre;
            System.err.println(ex.getClass()+" : "+ ex.getMessage()+" : "+ex.getLocalizedMessage()+" : "+ex.toString());
        }
        if(error!=null){
            System.out.println("ERROR != NULL");
            request.setAttribute("error", error);
            request.setAttribute("nombre", nombre);
            request.setAttribute("provincia", provincia);
            request.setAttribute("cif", cif);
            request.setAttribute("propietario", propietario);
            request.setAttribute("direccion", direccion);
            getServletContext().getRequestDispatcher("/admin/administrar.jsp").forward(request, response);
            
        }else{
            String mensaje="Se ha dado de alta el restaurante";
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
