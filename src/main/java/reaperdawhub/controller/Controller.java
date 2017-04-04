package reaperdawhub.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reaperdawhub.persistence.model.Project;
import reaperdawhub.service.ProjectsService;

@RestController
public class Controller {

    @Autowired
    private ProjectsService projectsService;

    private final AtomicLong counter = new AtomicLong();
    
    /*
    To Create a resource : HTTP POST should be used
    To Retrieve a resource : HTTP GET should be used
    To Update a resource : HTTP PUT should be used
    To Delete a resource : HTTP DELETE should be used
    */
 
    private boolean projectNotFound(Project project, long projectId) {
        if (project == null) {
            System.out.println("Project with id " + projectId + " not found");
            return true;
        }
        return false;
    }
    
    @RequestMapping("/projects")
    public ResponseEntity<List<Project>> getProjects() {
        List<Project> users = projectsService.findAllProjects();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    @RequestMapping(value="/projects/{projectId}", method=RequestMethod.GET)
    public ResponseEntity<Project> getProject(@PathVariable("projectId") long projectId, Model model) {
        System.out.println("Fetching project with id " + projectId);
        Project project = projectsService.findById(projectId);
        if (projectNotFound(project, projectId)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @RequestMapping(value="/projects/{projectId}", method=RequestMethod.DELETE)
    public ResponseEntity<Project> deleteProject(@PathVariable("projectId") long projectId, Model model) {
        System.out.println("Fetching & Deleting project with id " + projectId);
 
        Project user = projectsService.findById(projectId);
        if (user == null) {
            System.out.println("Unable to delete. Project with id " + projectId + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
 
        projectsService.deleteProjectById(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/projects/{projectId}", method = RequestMethod.PUT)
    public ResponseEntity<Project> updateUser(@PathVariable("projectId") long projectId, @RequestBody Project project) {
        Project currentProject = projectsService.findById(projectId);
         
        if (projectNotFound(currentProject, projectId)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
 
        currentProject.setName(project.getName());
        projectsService.updateProject(project);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/projects/", method = RequestMethod.POST)
      public ResponseEntity<Void> createProject(@RequestBody Project project, 
              UriComponentsBuilder ucBuilder) {
      System.out.println("Creating project " + project.getName());

      if (projectsService.doesProjectExist(project)) {
          System.out.println("A project with name " + project.getName() + " already exist");
          return new ResponseEntity<>(HttpStatus.CONFLICT);
      }

      projectsService.saveProject(project);

      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(ucBuilder.path("/projects/{id}").buildAndExpand(project.getId()).toUri());
      return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}