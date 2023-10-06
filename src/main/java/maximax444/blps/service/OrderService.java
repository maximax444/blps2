package maximax444.blps.service;

import maximax444.blps.RabbitConfig;
import maximax444.blps.dto.OrderedDTO;
import maximax444.blps.dto.ProductDTO;
import maximax444.blps.entity.Customer;
import maximax444.blps.entity.Order;
import maximax444.blps.entity.Ordered;
import maximax444.blps.entity.Product;
import maximax444.blps.repository.OrderRepository;
import lombok.AllArgsConstructor;
import maximax444.blps.repository.OrderedRepository;
import maximax444.blps.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service("orderInterface")
public class OrderService implements OrderInterface {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderedRepository orderedRepository;


	private final JmsTemplate jmsTemplate;
	private final RabbitConfig rabbitConfig;


	@Override
	public void save(Order order) {
		orderRepository.save(order);
	}

	@Override
	public void delete(Order order) {
		orderRepository.delete(order);
	}

	@Transactional
	public Order addOrder(List<ProductDTO> productDTOs, Customer customer) {
		Order newOrder = new Order();
		newOrder.setCustomer(customer.getId());
		newOrder.setStatus(true);
		System.out.println(newOrder);
		orderRepository.save(newOrder);

		for (ProductDTO itProd : productDTOs) {
			jmsTemplate.convertAndSend(rabbitConfig.getQueueName(), new OrderedDTO().setOrder(newOrder.getId()).setPdtos(itProd.getName()));
		}
		return newOrder;
	}

	public List<Order> findAllByCustomer(Customer customer) {
		return orderRepository.findAllByCustomer(customer.getId());
	}

}
