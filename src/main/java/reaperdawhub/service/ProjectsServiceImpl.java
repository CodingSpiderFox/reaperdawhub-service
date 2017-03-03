package reaperdawhub.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import reaperdawhub.persistence.base.IDao;
import reaperdawhub.persistence.dao.ProjectsDao;

import reaperdawhub.persistence.model.Project;

@Service("projectsService")
@Transactional(isolation = Isolation.SERIALIZABLE)
public class ProjectsServiceImpl implements ProjectsService {
	
        @Autowired    
        private IDao<Project> projectsDao;
    
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Project> projects;
	
	static {
		projects = populateDummyProjects();
	}

	public List<Project> findAllProjects() {
		return projectsDao.getAll();
	}
	
	public Project findById(long id) {
		for(Project project : projects){
			if(project.getId() == id){
				return project;
			}
		}
		return null;
	}
	
	public Project findByName(String name) {
		for(Project project : projects){
			if(project.getName().equalsIgnoreCase(name)){
				return project;
			}
		}
		return null;
	}
	
	public void saveProject(Project project) {
		project.setId(counter.incrementAndGet());
		projects.add(project);
	}

	public void updateProject(Project project) {
		int index = projects.indexOf(project);
		projects.set(index, project);
	}

	public void deleteProjectById(long id) {
		
		for (Iterator<Project> iterator = projects.iterator(); iterator.hasNext(); ) {
		    Project project = iterator.next();
		    if (project.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean doesProjectExist(Project project) {
		return findByName(project.getName())!=null;
	}

	private static List<Project> populateDummyProjects(){
		List<Project> projects = new ArrayList<Project>();
		projects.add(new Project(counter.incrementAndGet()));
		projects.add(new Project(counter.incrementAndGet()));
		projects.add(new Project(counter.incrementAndGet()));
		projects.add(new Project(counter.incrementAndGet()));
		return projects;
	}

	public void deleteAllProjects() {
		projects.clear();
	}

}
