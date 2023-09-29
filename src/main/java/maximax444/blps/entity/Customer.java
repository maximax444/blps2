package maximax444.blps.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;


    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;
}
