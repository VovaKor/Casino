package softgroup.ua.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by alexander on 29.03.17.
 */
@XmlRootElement
public class LoginRequest {
    @XmlElement(required = true)
    public String login;
    @XmlElement(required = true)
    public String password;
}
