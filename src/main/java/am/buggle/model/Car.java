package am.buggle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Seda Ghazaryan
 * @author Ara Hovhannisyan
 * @class Car
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String model;

    @Column
    private String series;

    @Column
    private Integer seats;

    @Column
    private String nymber;

    @Column
    private String color;

    @Column
    private Integer year;


}
