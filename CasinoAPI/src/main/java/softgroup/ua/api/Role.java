package softgroup.ua.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Вова on 08.03.2017.
 */
@XmlRootElement
public class Role {
    @XmlElement(required=false)
    public int roleId;
    @XmlElement(required=true)
    public String roleName;
    @XmlElement(required=true)
    public String description;

}
