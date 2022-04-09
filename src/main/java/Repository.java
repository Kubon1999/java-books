import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public abstract class Repository<E,K> {
	private final EntityManagerFactory entityManagerFactory;

	private final Class<E> clazz;

	public Repository(EntityManagerFactory entityManagerFactory, Class<E> clazz) {
		this.entityManagerFactory = entityManagerFactory;
		this.clazz = clazz;
	}

	protected EntityManagerFactory  getEmf(){
		return entityManagerFactory;
	}

	public E find(K id){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		E entity = entityManager.find(clazz, id);
		entityManager.close();
		return entity;
	}

	public List<E> findAll(){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<E> list = entityManager.createQuery("select e from " +
				clazz.getSimpleName() +" e", clazz).getResultList();
		entityManager.close();
		return list;
	}
	public void add(E entity){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void deleteById(K id){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		E entity = entityManager.createQuery("select e from " + clazz.getSimpleName() + " e where e.id=:id",clazz)
				.setParameter("e", id).getSingleResult();
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	public void deleteObject(E entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(entityManager.merge(entity));
		transaction.commit();
		entityManager.close();
	}

}
