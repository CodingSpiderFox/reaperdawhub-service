package reaperdawhub.persistence.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="projectFiles")
public class ProjectFile {
    @Id
    private long id;
    
    @NotNull
    private long projectId;
    
    @NotNull
    private long sizeBytes;
    
    @NotNull
    private String storageLocation;
    
    @NotNull
    private String filePath;
}
