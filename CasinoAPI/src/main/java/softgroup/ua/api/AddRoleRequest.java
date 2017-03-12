package softgroup.ua.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Вова on 12.03.2017.
 */
@XmlRootElement
public class AddRoleRequest {
    @XmlElement(required=true)
    public Role role;
}
