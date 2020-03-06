package am.buggle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Seda Ghazaryan
 * @author Ara Hovhannisyan
 * @class Trip
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private String startDestination;

    @Column
    private String endDestination;

    @Column
    private Long price;

    @ManyToOne
    private User driver;

    @OneToMany
    private List<User> users;
}
