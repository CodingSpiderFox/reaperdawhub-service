package reaperdawhub.persistence.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class User {
    @Id
    private long id;
    
    @NotNull
    private String nickname;
    
    @Column
    private String firstname;
    
    @NotNull
    private String email;
 
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    
    
}
