package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ItunesDAO {

	public ItunesDAO() {
		
	}
	
	  protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("conorspersistanceunit");

	  public static void persist(Object object) {
	        EntityManager em = emf.createEntityManager();
	        em.getTransaction().begin();
	        em.persist(object);
	        em.getTransaction().commit();
	        em.close();
	    }

}
