package edu.tucn.scd.serverapp.position;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.tucn.scd.serverapp.terminal.Terminal;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

/**
 * @author Radu Miron
 * @version 1
 */

@Entity
@Data
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String latitude;
    private String longitude;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "terminal_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Terminal terminal;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Date creationDate;
}
