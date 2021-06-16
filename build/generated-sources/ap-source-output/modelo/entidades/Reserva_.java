package modelo.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Cliente;
import modelo.entidades.Restaurante;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-16T16:25:50")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, Cliente> cliente;
    public static volatile SingularAttribute<Reserva, Date> fecha;
    public static volatile SingularAttribute<Reserva, String> hora;
    public static volatile SingularAttribute<Reserva, Restaurante> restaurante;
    public static volatile SingularAttribute<Reserva, Long> id;
    public static volatile SingularAttribute<Reserva, Integer> comensales;

}