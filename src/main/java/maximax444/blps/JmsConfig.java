package maximax444.blps;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rabbitmq.jms.admin.RMQConnectionFactory;
import javax.jms.ConnectionFactory;

@Configuration
@AllArgsConstructor
public class JmsConfig {
    private RabbitConfig rabbitConfig;

    @Bean
    public ConnectionFactory jmsConnectionFactory() {
        RMQConnectionFactory connectionFactory = new RMQConnectionFactory();
        connectionFactory.setUsername(rabbitConfig.getUsername());
        connectionFactory.setPassword(rabbitConfig.getPassword());
        connectionFactory.setHost(rabbitConfig.getHost());
        connectionFactory.setPort(Integer.parseInt(rabbitConfig.getPort()));
        return connectionFactory;
    }
}