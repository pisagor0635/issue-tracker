package ag.pinguin.issuetracker.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "developers")
@Getter
@Setter
public class Developer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "developer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Story> stories;

}
