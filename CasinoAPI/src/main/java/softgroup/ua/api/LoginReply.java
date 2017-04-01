package softgroup.ua.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by alexander on 29.03.17.
 */
@XmlRootElement
public class LoginReply extends GenericReply {
    @XmlElement
    public String token = "";
    @XmlElement
    public User user;
}
