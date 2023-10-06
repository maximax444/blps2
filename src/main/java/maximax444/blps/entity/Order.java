package maximax444.blps.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;


    private Long customer;

    @Column(name = "status")
    private boolean status;
}