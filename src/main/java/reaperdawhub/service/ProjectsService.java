package reaperdawhub.service;

import java.util.List;

import reaperdawhub.persistence.model.Project;


public interface ProjectsService {
	
	Project findById(long id);
	
	Project findByName(String name);
	
	void saveProject(Project project);
	
	void updateProject(Project project);
	
	void deleteProjectById(long id);

	List<Project> findAllProjects();
	
	public boolean doesProjectExist(Project project);
	
}
