/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Gestion;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Cliente;
import modelo.entidades.Reserva;
import modelo.entidades.Restaurante;

/**
 *
 * @author macar
 */
public class ReservaJpaController implements Serializable {

    public ReservaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reserva reserva) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Restaurante restaurante = reserva.getRestaurante();
            if (restaurante != null) {
                restaurante = em.getReference(restaurante.getClass(), restaurante.getId());
                reserva.setRestaurante(restaurante);
            }
            em.persist(reserva);
            if (restaurante != null) {
                restaurante.getReservas().add(reserva);
                restaurante = em.merge(restaurante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reserva reserva) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva persistentReserva = em.find(Reserva.class, reserva.getId());
            Restaurante restauranteOld = persistentReserva.getRestaurante();
            Restaurante restauranteNew = reserva.getRestaurante();
            if (restauranteNew != null) {
                restauranteNew = em.getReference(restauranteNew.getClass(), restauranteNew.getId());
                reserva.setRestaurante(restauranteNew);
            }
            reserva = em.merge(reserva);
            if (restauranteOld != null && !restauranteOld.equals(restauranteNew)) {
                restauranteOld.getReservas().remove(reserva);
                restauranteOld = em.merge(restauranteOld);
            }
            if (restauranteNew != null && !restauranteNew.equals(restauranteOld)) {
                restauranteNew.getReservas().add(reserva);
                restauranteNew = em.merge(restauranteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = reserva.getId();
                if (findReserva(id) == null) {
                    throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva reserva;
            try {
                reserva = em.getReference(Reserva.class, id);
                reserva.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.", enfe);
            }
            Restaurante restaurante = reserva.getRestaurante();
            if (restaurante != null) {
                restaurante.getReservas().remove(reserva);
                restaurante = em.merge(restaurante);
            }
            em.remove(reserva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reserva> findReservaEntities() {
        return findReservaEntities(true, -1, -1);
    }

    public List<Reserva> findReservaEntities(int maxResults, int firstResult) {
        return findReservaEntities(false, maxResults, firstResult);
    }

    private List<Reserva> findReservaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reserva.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Reserva findReserva(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reserva.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Reserva> eliminarReservas(Reserva res){
        EntityManager em = getEntityManager();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fechaComoCadena = sdf.format(res.getFecha());
        fechaComoCadena= fechaComoCadena.replace("/", "-");
        List<Reserva> reservas = new ArrayList();
        String sql= "Select * from reserva where CLIENTE_ID="+res.getCliente().getId()+" and fecha='"+fechaComoCadena+"' and hora='"+res.getHora()+"' and RESTAURANTE_ID="+res.getRestaurante().getId()+" ;";
        try{
            reservas=em.createNativeQuery(sql, Reserva.class).getResultList();
        }catch(Exception e){
            
        }
        return reservas;
    }
    
    public List<Reserva> verVisitas(String login){
        EntityManager em = getEntityManager();
        Gestion gestion = new Gestion ();
        Cliente cli = gestion.buscarClienteLogin(login);
        
        String sql= "Select * from reserva where CLIENTE_ID="+cli.getId()+";";
        List<Reserva> reservas = new ArrayList();
        
        try{
            reservas=em.createNativeQuery(sql, Reserva.class).getResultList();
        }catch(Exception e){
            
        }
        return reservas;
    }
    
    public List<Reserva> findReservaVigentes(){
        EntityManager em = getEntityManager();
        List <Reserva> filtrar= new ArrayList();
        Date hoy = new Date();
        
        Calendar calendario = new GregorianCalendar();
        int horaActual= calendario.get(Calendar.HOUR_OF_DAY);
        String sql= "SELECT * FROM RESERVA ORDER BY FECHA, HORA;";
        try{
            List <Reserva> todas = em.createNativeQuery(sql, Reserva.class).getResultList();
            
            for(int i=0; i<todas.size(); i++){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String cadenaHoy = sdf.format(hoy);
                String cadenaFecha= sdf.format(todas.get(i).getFecha());
                String horaReserva[]= todas.get(i).getHora().split(":");
                int hora=Integer.parseInt(horaReserva[0]);
                if(todas.get(i).getFecha().after(hoy)||(cadenaHoy.equals(cadenaFecha) && hora>=horaActual )){
                    filtrar.add(todas.get(i));
                }
            }
        }catch(Exception e){
            
        }
        return filtrar;
    }
    
    public List<Reserva> filtraPorRestaurante(String nombre){
        Gestion gestion= new Gestion();
        Restaurante res = gestion.buscarRestauranteNombre(nombre);
        List<Reserva> todas= null;
        try{
            todas= this.findReservaVigentes();
        }catch(Exception ex){
            System.err.println("Error en filtraPorRestaurante");
        }
        List<Reserva> aux = new ArrayList();
        for(int i = 0 ; i<todas.size(); i++){
            if(todas.get(i).getRestaurante().getId()==res.getId()){
                aux.add(todas.get(i));
            }
        }
        return aux;
    }
    
    
    public List<Reserva> reservasHoraRestaurante(String fecha,Restaurante res, String hora){
        EntityManager em= getEntityManager();
        String sql= "Select * from RESERVA where FECHA='"+fecha+"' and HORA='"+hora+"' and RESTAURANTE_ID='"+res.getId()+"';";        
        List<Reserva> listaReservas = null;
        try{
            listaReservas = em.createNativeQuery(sql, Reserva.class).getResultList();
        }catch(Exception ex){
            
        }
        
        if(listaReservas == null){
            listaReservas= new ArrayList<>();
        }
        return listaReservas;
    }

    public int getReservaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reserva> rt = cq.from(Reserva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
