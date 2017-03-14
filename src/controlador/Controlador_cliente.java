package controlador;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import modelo.Client;
import modelo.Vehicle;

public class Controlador_cliente {

    /**
     * Metodo que inserta un cliente dentro de una base de datos.
     *
     * @param p
     */
    public void Insertar(Client p) {
        //Primero debemos crear un objeto EM_controller
        EM_Controller ec = new EM_Controller();
        
        //Seguidamente creamos un objeto entityManager
        EntityManager em = ec.getEntityManager();

        //Creamos un objeto de EntityTransiction
        EntityTransaction et = em.getTransaction();
        
        //Ejecutamos l metodo begin del entityTransiction
        System.out.println("begin");
        et.begin();
        
        //Seguidamente ejecutamos el metodo persist del entityManager
        System.out.println("persist");
        em.persist(p);

        System.out.println("commit");
        //em.getTransaction().commit();
        et.commit();

        System.out.println("close");
        em.close();
    }

    /**
     * Le llega por parametro un cliente para modificarlo
     *
     * @param cliente
     */
    public void Modificar(Client cliente) {
        // Creamos un controlador y recuperamos el entityManager
        EM_Controller ec = new EM_Controller();
        EntityManager em = ec.getEntityManager();

        
        EntityTransaction et = em.getTransaction();

        //Ejectuamos el begin
        System.out.println("begin");
        et.begin();
        
        //Ejectuamos el merge con el cliente por parametro
        System.out.println("merge");
        em.merge(cliente);
        
        //Finalmente hacemos un merge
        System.out.println("commit");
        //em.getTransaction().commit();
        et.commit();
        
        //Cerramos el entityManager
        System.out.println("close");
        em.close();
    }

    /**
     * Este metodo recupera por parametro un objeto de tipo cliente y lo elimina
     * de la basse de datos.
     *
     * @param cliente
     */
    public void Eliminar(Client cliente) {
        //Perimero recupera el entityManager
        EM_Controller oem = new EM_Controller();
        EntityManager em = oem.getEntityManager();

        
        EntityTransaction etx = em.getTransaction();

        System.out.println("begin");
        etx.begin();

        System.out.println("remove");
        
        //En caso de que el cliente exista se borrará sino se hara un merge
        if (em.contains(cliente)){
            em.remove(em);
        }
        
        else {
            em.merge(cliente);
        }
  

        System.out.println("commit");
        //em.getTransaction().commit();
        etx.commit();

        System.out.println("close");
        em.close();
    }

    /**
     * Recoge una id de cliente y busca al cliente con esa ID.
     *
     * @param id
     * @return
     */
    public Client Buscar(Long id) {
        // Recupera el entity manager
        EntityManager em = new EM_Controller().getEntityManager();

        System.out.println("busqueda");
        
        //Creamos un objeto cliente y con el em.find buscamos si la id existe.
        Client cliente = (Client) em.find(Client.class, id);

        System.out.println("close");
        em.close();

        return cliente;
    }

    /**
     * Le llega por parametro el nombre del cliente y devuelve un objeto de tipo cliente
     *
     * @param nom
     * @return
     */
    public Client BuscarPerNom(String nom) {
        // Recupera el entity manager
        EntityManager em = new EM_Controller().getEntityManager();

        System.out.println("Buscar clientes por su nombre");
        //Creamos la query recuperandola de la clase cliente donde ya la teenmos creada
        Query query = em.createNamedQuery("buscarClientePorNombre", Client.class);
        
        
        
        //Le pasamos el parametro nombre, con el primer campo decimos que campo vamos a sobrescribir
        //En el segundo campo le damos un valor.
        query.setParameter("nom", nom);
        //Recuperamos la select dentro de un objeto cliente
        Client cliente = (Client) query.getSingleResult();
        System.out.println("close");
        em.close();
        return cliente;
    }

    /**
     * Metodo para imprimir persona, simplemente nos imprimirá el toString.
     *
     * @param cliente
     */
    public void imprimirPersona(Client cliente) {
        System.out.println(cliente);
    }
    /**
     * Metodo para imprimir los vehiculos que tiene un cliente.
     * @param id
     * @return 
     */
    public List<Vehicle> obtenirVehiclesClient(long id){
        EntityManager  em = new EM_Controller().getEntityManager();
        System.out.println("Cerca de vehicles que te un client");
        Query query = em.createNamedQuery("cochesCliente", Vehicle.class);
        query.setParameter("id", id);
        List<Vehicle> lista = (List<Vehicle>) query.getResultList();
        System.out.println(lista);
        System.out.println("close");
        em.close();
        return lista;
    }

}
