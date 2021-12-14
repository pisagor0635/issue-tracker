package ag.pinguin.issuetracker.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "developers")
@Data
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
