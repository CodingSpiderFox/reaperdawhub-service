package reaperdawhub.controller;

import java.util.*;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reaperdawhub.persistence.model.Project;
import reaperdawhub.persistence.model.ProjectFile;
import reaperdawhub.service.ProjectsService;

@RestController
public class Controller {

    @Autowired
    private ProjectsService projectsService;
    
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    /*
    To Create a resource : HTTP POST should be used
    To Retrieve a resource : HTTP GET should be used
    To Update a resource : HTTP PUT should be used
    To Delete a resource : HTTP DELETE should be used
    */
    
    @RequestMapping("/projects")
    public List<Project> getProjects(@RequestParam(value="name", defaultValue="World") String name) {
        return new ArrayList<Project>();
    }
    
    @RequestMapping(value="/projects/{projectId}", method=RequestMethod.GET)
    public Project getProject(@PathVariable("projectId") long projectId, Model model) {
        return new Project(counter.incrementAndGet());
    }
    
    
    @RequestMapping(value="/projects/{projectId}", method=RequestMethod.DELETE)
    public void deleteProject(@PathVariable("projectId") long projectId, Model model) {
        return;
    }
    
    @RequestMapping(value="/projects/{projectId}", method=RequestMethod.PUT)
    public void updateProject(@PathVariable("projectId") long projectId, Model model) {
        return;
    }
    
    
    @RequestMapping(value = "/projects/", method = RequestMethod.POST)
      public ResponseEntity<Void> createProject(@RequestBody Project project, 
              UriComponentsBuilder ucBuilder) {
      System.out.println("Creating project " + project.getName());

      if (projectsService.doesProjectExist(project)) {
          System.out.println("A project with name " + project.getName() + " already exist");
          return new ResponseEntity<Void>(HttpStatus.CONFLICT);
      }

      projectsService.saveProject(project);

      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(ucBuilder.path("/projects/{id}").buildAndExpand(project.getId()).toUri());
      return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
      
    @RequestMapping(value="/projects/{projectId}/projectFiles", method=RequestMethod.GET)
    public List<ProjectFile> getProjectFiles(@PathVariable("projectId") long projectId, Model model) {
        return new ArrayList<ProjectFile>();
    }
}