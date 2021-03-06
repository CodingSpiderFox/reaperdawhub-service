package reaperdawhub.persistence.model;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Represents an User for this web application.
 */
@Entity
@Table(name = "projects")
public class Project implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  
  @NotNull
  private String name;
  
  @NotNull
  private long ownerId;

  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  private Date timeStampLastModified;

    public Date getTimeStampLastModified() {
        return timeStampLastModified;
    }

    public void setTimeLastModified(Date lastModified) {
        this.timeStampLastModified = lastModified;
    }

    public Date getTimeStampCreated() {
        return timeStampCreated;
    }

    public void setTimeStampCreated(Date created) {
        this.timeStampCreated = created;
    }
  
  @NotNull
  @Temporal(value = TemporalType.TIMESTAMP)
  private Date timeStampCreated;
  
  public long getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(long ownerId) {
    this.ownerId = ownerId;
  }

  public Project() { }
  
  public Project(long id) { 
    this.id = id;
  }

  public Project(String name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long value) {
    this.id = value;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String value) {
    this.name = value;
  }
  
  
  
}
