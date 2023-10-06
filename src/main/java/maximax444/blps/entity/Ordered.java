package maximax444.blps.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Ordered {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ordered_id")
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "status_store")
    private boolean statusStore;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
}