package edu.tucn.scd.serverapp.terminal;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Entity
@Data
public class Terminal {
    @Id // PK
    private String id;
    private String terminalName;
    @OneToMany(mappedBy = "terminal")
    private List<Position> positions;
}
