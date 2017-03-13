package softgroup.ua.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Вова on 13.03.2017.
 */
@XmlRootElement
public class AddAutomatRequest {
    @XmlElement(required=true)
    public Automat automat;
}
