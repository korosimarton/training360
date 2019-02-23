package daos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "auditlogs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String message;

    public AuditLog(final long id, final String message) {
        this.id = id;
        this.message = message;
    }

    public AuditLog(final String message) {
        this.message = message;
    }

    // Needed for Hibernate
    public AuditLog() {
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
