package reaperdawhub.persistence.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="settingsFiles")
public class SettingsFile {
    @Id
    private long id;
    
    @NotNull
    private String filePath;
    
    @NotNull
    private byte[] fileBytes;
}
