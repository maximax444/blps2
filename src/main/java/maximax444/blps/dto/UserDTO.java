package maximax444.blps.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;
    private String userName;
    private String password;
    private Date lastPasswordResetDate;
    private Collection<? extends GrantedAuthority> authorities;

}
