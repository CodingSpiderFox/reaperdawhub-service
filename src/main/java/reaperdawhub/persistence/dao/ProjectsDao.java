package reaperdawhub.persistence.dao;

import reaperdawhub.persistence.base.IDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import reaperdawhub.persistence.model.Project;

@Component
public class ProjectsDao implements IDao<Project> {
  /**
   * Save the user in the database.
   */
  @Override
  public void create(Project object) {
    entityManager.persist(object);
  }
  
  /**
   * Delete the user from the database.
   */
  @Override
  public void delete(Project object) {
    if (entityManager.contains(object))
      entityManager.remove(object);
    else
      entityManager.remove(entityManager.merge(object));
  }
  
  /**
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  @Override
  public List<Project> getAll() {
    return entityManager.createQuery("from Project").getResultList();
  }
  
  /**
   * Return the user having the passed id.
   */
  @Override
  public Project getById(long id) {
    return entityManager.find(Project.class, id);
  }

  /**
   * Update the passed user in the database.
   */
  @Override
  public void update(Project object) {
    entityManager.merge(object);
  }

  
  @PersistenceContext
  private EntityManager entityManager; 
}
