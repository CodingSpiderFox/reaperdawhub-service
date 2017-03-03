package reaperdawhub.persistence.dao;

import com.mysql.jdbc.Blob;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="files")
public class File {
    @Id
    private long id;

    @NotNull
    private Blob File;
}
