package maximax444.blps.repository;

import maximax444.blps.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository {

    Optional<Customer> findAllByUserName(String userName);

    void save(Customer user);

    Optional<Customer> findById(Integer id);

    List<Customer> getAllUsers();
}
