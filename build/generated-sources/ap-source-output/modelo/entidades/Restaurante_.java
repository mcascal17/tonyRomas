package modelo.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Reserva;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-12T14:16:59")
@StaticMetamodel(Restaurante.class)
public class Restaurante_ { 

    public static volatile SingularAttribute<Restaurante, String> cif;
    public static volatile SingularAttribute<Restaurante, Integer> mesas;
    public static volatile SingularAttribute<Restaurante, String> foto;
    public static volatile CollectionAttribute<Restaurante, Reserva> reservas;
    public static volatile SingularAttribute<Restaurante, String> propietario;
    public static volatile SingularAttribute<Restaurante, String> direccion;
    public static volatile SingularAttribute<Restaurante, Long> id;
    public static volatile SingularAttribute<Restaurante, String> provincia;
    public static volatile SingularAttribute<Restaurante, String> nombre;

}