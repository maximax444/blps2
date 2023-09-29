package maximax444.blps.repository;

import lombok.Setter;
import maximax444.blps.entity.Customer;
import maximax444.blps.service.usersXML.UsersXML;
import maximax444.blps.service.usersXML.XMLUserMarshaller;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Setter
@Service("customerRepository")
public class CustomerXMLRepository implements CustomerRepository {
    private final XMLUserMarshaller xml;
    private Integer currentId;

    @Override
    public Optional<Customer> findAllByUserName(String username) {
        var users = xml.getUsers();

        if (users == null || users.isEmpty()) return Optional.empty();
        return users.stream().filter(u -> username.equals(u.getUserName())).findFirst();
    }

    @Override
    public void save(Customer user) {
        try {
            var users = xml.getUsers();
            if (users == null) {
                users = new ArrayList<>();
            }
            boolean update = user.getId() != null && users.stream().anyMatch(u -> Objects.equals(u.getId(), user.getId()));
            if (update) {
                users.removeIf(u -> Objects.equals(u.getId(), user.getId()));
                users.add(user);
            } else {
                currentId++;
                user.setId(Long.valueOf(currentId));
                users.add(user);
            }
            xml.writeUsersToXml(users);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return xml.getUsers().stream().filter(u -> Objects.equals(u.getId(), id)).findFirst();
    }

    @Override
    public List<Customer> getAllUsers() {
        return xml.getUsers();
    }

    public CustomerXMLRepository(XMLUserMarshaller xml) {
        this.xml = xml;
        this.currentId = 0;
    }
}