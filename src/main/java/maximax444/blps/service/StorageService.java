package maximax444.blps.service;

import maximax444.blps.dto.OrderedDTO;
import maximax444.blps.dto.ProductDTO;
import maximax444.blps.entity.Order;
import maximax444.blps.entity.Ordered;
import maximax444.blps.entity.Product;
import maximax444.blps.repository.OrderRepository;
import maximax444.blps.repository.OrderedRepository;
import maximax444.blps.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderedRepository orderedRepository;

    @Autowired
    private OrderRepository orderRepository;

    public void checkStore() {
        System.out.println("ready");
        List<Ordered> orded = orderedRepository.findAllByStatusStore(false);
        if (orded.size() == 0) {
            System.out.println("nothing to change");
        }
        for (Ordered str : orded) {
            Product pr = str.getProduct();
            pr.setCount(pr.getCount() - 1);
            productRepository.save(pr);
            str.setStatusStore(true);
            orderedRepository.save(str);
        }
    }

    @JmsListener(destination = "myQueue")
    public void addOrderedToStore(OrderedDTO odto) {
        String productDTOs = odto.getPdtos();
        Long newOrder =odto.getOrder();
        Ordered newOrdered;
            newOrdered  = new Ordered();
            newOrdered.setOrder(orderRepository.findById(newOrder).get());
            newOrdered.setStatusStore(false);

            Product product = productRepository.findAllByName(productDTOs).get(0);
//			product.setOwner(customer);
//			product.setName(itProd.getName());
//			product.setPrice(itProd.getPrice());
//			product.setCount(itProd.getCount());
            newOrdered.setProduct(product);

            orderedRepository.save(newOrdered);

    }
}
