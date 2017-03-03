package reaperdawhub.persistence.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="permissions")
public class Permission {
    
    @Id
    private long id;
    
    @NotNull
    private String entityType;
    
    @NotNull
    private long entityId;
    
    @NotNull
    private PermittedAction permittedAction;
}
