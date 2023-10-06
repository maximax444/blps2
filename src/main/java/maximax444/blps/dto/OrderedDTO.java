package maximax444.blps.dto;

import lombok.*;
import lombok.experimental.Accessors;
import maximax444.blps.entity.Order;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderedDTO implements Serializable {

    private String pdtos;
    private Long order;
}
