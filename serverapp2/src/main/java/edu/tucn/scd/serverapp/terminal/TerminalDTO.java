package edu.tucn.scd.serverapp.terminal;

import edu.tucn.scd.serverapp.position.Position;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

/**
 * @author Radu Miron
 * @version 1
 */

@Data // lombok annotation, creates equals(), hashCode() and toString at compile time
public class TerminalDTO {
    @Id // PK
    private String id;
    private String terminalName;

}
