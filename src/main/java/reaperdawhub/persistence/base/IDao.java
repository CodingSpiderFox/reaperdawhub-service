package reaperdawhub.persistence.base;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

/**
 * This class is used to access data for the User entity.
 * Repository annotation allows the component scanning support to find and 
 * configure the DAO wihtout any XML configuration and also provide the Spring 
 * exceptiom translation.
 * Since we've setup setPackagesToScan and transaction manager on
 * DatabaseConfig, any bean method annotated with Transactional will cause
 * Spring to magically call begin() and commit() at the start/end of the
 * method. If exception occurs it will also call rollback().
 */
@Repository
@Transactional
public interface IDao<T> {
    
  /**
   * Save the T in the database.
     * @param object the entity to create
   */
  public void create(T object);
  
  /**
   * Delete the T from the database.
     * @param object the entity to delete
   */
  public void delete(T object);
  
  /**
   * Return all the Ts stored in the database.
     * @return List of all entities of that type
   */
  @SuppressWarnings("unchecked")
  public List<T> getAll();
  
  /**
   * Return the T having the passed id.
     * @param id the ID of the entity to get
     * @return the resulting entity
   */
  public T getById(long id);

  /**
   * Update the passed T in the database.
     * @param object entity to update
   */
  public void update(T object);
}