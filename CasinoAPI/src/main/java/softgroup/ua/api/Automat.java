package softgroup.ua.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Вова on 08.03.2017.
 */
@XmlRootElement
public class Automat {
    @XmlElement(required=false)
    public int automatId;
    @XmlElement(required=true)
    public String automatName;
    @XmlElement(required=true)
    public String description;
}
