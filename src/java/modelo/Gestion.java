/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Persistence;
import modelo.dao.ClienteJpaController;
import modelo.dao.EmpleadoJpaController;
import modelo.dao.ReservaJpaController;
import modelo.dao.RestauranteJpaController;
import modelo.entidades.Cliente;
import modelo.entidades.Empleado;
import modelo.entidades.Reserva;
import modelo.entidades.Restaurante;

/**
 *
 * @author macar
 */
public class Gestion implements Serializable {
    //Unidad de Persistencia
    public static final String PERSISTENCIA = "tonyRomas2PU";
   
    public List<Reserva> getReservas(){
        ReservaJpaController rjc= new
            ReservaJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        return rjc.findReservaEntities();
    }
    public List<Empleado> getEmpleados(){
        EmpleadoJpaController ejc= new
            EmpleadoJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        return ejc.findEmpleadoEntities();
    }
    public List<Restaurante> getRestaurantes(){
        RestauranteJpaController rjc= new
            RestauranteJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        return rjc.findRestauranteEntities();
    }
    
    public List<Cliente> getClientes(){
        ClienteJpaController cjc= new
            ClienteJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        return cjc.findClienteEntities();
    }
    
    public List<Reserva> reservasVigentes(){
        ReservaJpaController rjc= new
            ReservaJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        return rjc.findReservaVigentes();
    }
    
    public List<Reserva> filtraReservas (String nombre){
        ReservaJpaController rjc= new
            ReservaJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        if(nombre.equals("Todos los restaurantes")){
            return this.verReservaSinRepes();
        }else{
            List <Reserva> todas = rjc.filtraPorRestaurante(nombre);
            List <Reserva> sinRepes = new ArrayList();
            for(int i= 0; i<todas.size(); i++){
            if(sinRepes.size()<1){
                sinRepes.add(todas.get(0));
            }
            int j=0;
            boolean repetido = false;
            while(!repetido && j<sinRepes.size()){
                if(todas.get(i).getFecha().equals(sinRepes.get(j).getFecha()) && todas.get(i).getHora().equals(sinRepes.get(j).getHora()) & todas.get(i).getCliente().equals(sinRepes.get(j).getCliente())){
                    repetido=true;
                }
                j++;
            }
            if(repetido == false && !sinRepes.contains(todas.get(i))){
                sinRepes.add(todas.get(i));
            }
        }
        return sinRepes;
            
        }
    }
    
    
    public void altaEmpleado(Empleado em) throws Exception{
        EmpleadoJpaController ejc= new
            EmpleadoJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        ejc.create(em);
    }
    public void altaCliente(Cliente cl) throws Exception{
        ClienteJpaController cjc= new
            ClienteJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        cjc.create(cl);
    }
    
    public void altaRestaurante(Restaurante res) throws Exception{
        RestauranteJpaController rjc= new
            RestauranteJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        rjc.create(res);
    }
    
    public void altaReserva(Reserva res) throws Exception{
        ReservaJpaController rjc= new
            ReservaJpaController(Persistence.createEntityManagerFactory((PERSISTENCIA)));
        rjc.create(res);
    }
    public Empleado buscarEmpleado(Long id) {
        EmpleadoJpaController ejc = new 
            EmpleadoJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        return ejc.findEmpleado(id);
    }
    
    public Restaurante buscarRestauranteNombre(String nombre){
        RestauranteJpaController rjc= new
            RestauranteJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        System.out.println("HA ENTRADO EN LA FUNCION!!!");
        Restaurante res =rjc.findRestauranteNombre(nombre);
        System.out.println(res.getNombre());
        System.out.println(res.getDireccion());
        return res;
        
    }
    
    
    public Cliente buscarClienteLogin(String login){
        ClienteJpaController cjc= new
        ClienteJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        Cliente cli= cjc.findClienteLogin(login);
        return cli;
    }
    
    public Reserva buscarReserva(Long id){
        ReservaJpaController rjc= new
            ReservaJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        Reserva res= rjc.findReserva(id);
        return res;
    }
    
    public Cliente buscarCliente(Long id) {
        ClienteJpaController cjc = new 
            ClienteJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        return cjc.findCliente(id);
    }
    
    public Empleado buscarEmpleadoLogin(String login){
        EmpleadoJpaController ejc= new
            EmpleadoJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        return ejc.findEmpleadoLogin(login);
    }
    
    public List<Reserva> verVisitas(String login){
        ReservaJpaController rjc= new
            ReservaJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        
        List <Reserva> todas = rjc.verVisitas(login);
        List <Reserva> sinRepes= new ArrayList();
        for(int i = 0; i<todas.size(); i++){
            if(sinRepes.size()<1){
                sinRepes.add(todas.get(0));
            }
            int j=0;
            boolean repetido= false;
            while(!repetido && j<sinRepes.size()){
                if(todas.get(i).getFecha().equals(sinRepes.get(j).getFecha()) && todas.get(i).getHora().equals(sinRepes.get(j).getHora())){
                    repetido=true;
                    
                }
                j++;
            }
                
            
            if(repetido==false && !sinRepes.contains(todas.get(i))){
                sinRepes.add(todas.get(i));
            }
        }
        return sinRepes;
    }
    
    public List<Reserva> verReservaSinRepes (){
        List <Reserva> todas = this.reservasVigentes();
        List <Reserva> sinRepes= new ArrayList();
        for(int i= 0; i<todas.size(); i++){
            if(sinRepes.size()<1){
                sinRepes.add(todas.get(0));
            }
            int j=0;
            boolean repetido = false;
            while(!repetido && j<sinRepes.size()){
                if(todas.get(i).getFecha().equals(sinRepes.get(j).getFecha()) && todas.get(i).getHora().equals(sinRepes.get(j).getHora()) & todas.get(i).getCliente().equals(sinRepes.get(j).getCliente())){
                    repetido=true;
                }
                j++;
            }
            if(repetido == false && !sinRepes.contains(todas.get(i))){
                sinRepes.add(todas.get(i));
            }
        }
        return sinRepes;
    }
    
    
    public Restaurante buscarRestaurante(Long id) {
        RestauranteJpaController rjc = new 
            RestauranteJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        return rjc.findRestaurante(id);
    }
    
    public List<Cliente> filtrarClientes(String filtro){
        List<Cliente> clientes= this.getClientes();
        List<Cliente>filtrados= new ArrayList();
        if(!filtro.isEmpty()){
            for(Cliente c: clientes){
                if(c.getNombre().contains(filtro) || c.getApellidos().contains(filtro)){
                    filtrados.add(c);
                }
            }
        }else{
            filtrados = clientes;
        }
        return filtrados;
    }
    
    public List<Empleado> filtrarEmpleados(String filtro){
        List<Empleado> empleados= this.getEmpleados();
        List<Empleado>filtrados= new ArrayList();
        if(!filtro.isEmpty()){
            for(Empleado e: empleados){
                if(e.getNombre().contains(filtro) || e.getApellidos().contains(filtro)){
                    filtrados.add(e);
                }
            }
        }else{
            filtrados = empleados;
        }
        return filtrados;
    }
    
    public void enviarEmail(String from, String to, String subject, String text, String password){
        Email email= new Email();
        email.setFrom(from);
        email.setTo(to);
        email.setSubject(subject);
        email.setText(text);
        new Utilidades().enviarEmail(email, password);
    }
    
    
    public void actualizarCliente(Cliente cli) throws Exception {
        ClienteJpaController cjc= new
            ClienteJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        cjc.edit(cli);
    }
    
    public void actualizarEmpleado(Empleado emp) throws Exception {
        EmpleadoJpaController ejc= new
            EmpleadoJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        ejc.edit(emp);
    }
    
    public void actualizarRestaurante(Restaurante res) throws Exception {
        RestauranteJpaController rjc= new
            RestauranteJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        rjc.edit(res);
    }
    
    
    public int getPosArray(int hora){
        int empieza=10;
        int puesto= hora - empieza;
        return puesto;
    }
    
    public int mesasNecesarias (int comensales){
        int mesas;
        if(comensales%4==0){
            mesas=comensales/4;
        }else{
            mesas=(comensales/4)+1;
        }
        return mesas;
    }
    
    public Date parseFecha(String fecha)
    {
        String[]aux= fecha.split("-");
        fecha=aux[2]+"/"+aux[1]+"/"+aux[0];
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
    }
    
    public void eliminarReserva(Reserva res){
        ReservaJpaController rjc= new
            ReservaJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        List<Reserva>reservas= rjc.eliminarReservas(res);
        for(int i=0 ; i<reservas.size(); i++){
            try{
                rjc.destroy(reservas.get(i).getId());
            }catch(Exception ex){
                
            }
            
        }
    }
    
    public void eliminarEmpleado(Empleado emp){
        EmpleadoJpaController ejc= new
            EmpleadoJpaController(Persistence.createEntityManagerFactory(PERSISTENCIA));
        try{
            ejc.destroy(emp.getId());
        }catch(Exception ex){
            
        }
    }
    
    
    public String fechaVista(String fecha){
        String[] aux= fecha.split(" ");
        String mes= null;
        switch(aux[1]){
            case "Jan":
                mes="01";
                break;
            case "Feb":
                mes="02";
                break;
            case "Mar":
                mes="03";
                break;
            case "Apr":
                mes="04";
                break;
            case "May":
                mes="05";
                break;
            case "Jun":
                mes="06";
                break;
            case "Jul":
                mes="07";
                break;
            case "Aug":
                mes="08";
                break;
            case "Sep":
                mes="09";
                break;
            case "Oct":
                mes="10";
                break;
            case "Nov":
                mes="11";
                break;
            case "Dec":
                mes="12";
                break;
                
        }
        fecha=aux[2]+"/"+mes+"/"+aux[5];
        return fecha;
       
    }
    
    
}
