package maximax444.blps.service.usersXML;

import lombok.RequiredArgsConstructor;
import maximax444.blps.entity.Customer;
import maximax444.blps.entity.Role;
import maximax444.blps.repository.CustomerRepository;
import maximax444.blps.repository.CustomerXMLRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Comparator;
import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class configXML {
    @Bean
    public UserDetailsService userDetailsService(CustomerRepository userRepository) {
        return username -> (UserDetails) userRepository.findAllByUserName(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("User: %s, not found", username)));
    }



    @Bean
    public XMLUserMarshaller xml(){
        var xml = new XMLUserMarshaller();
        try {
            xml.readAllUsersFromXmlIntoMemory();
            return xml;
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public CustomerRepository userRepository(XMLUserMarshaller xml, PasswordEncoder passwordEncoder){
        var repo = new CustomerXMLRepository(xml);
        var users = repo.getAllUsers();
        if (users.isEmpty()){
        } else {
            var maxId = users.stream().map(Customer::getId).max(Comparator.naturalOrder()).get();
            repo.setCurrentId((int) (maxId+1));
        }
        return repo;
    }
}