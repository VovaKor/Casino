package softgroup.ua.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Вова on 08.03.2017.
 */
@XmlRootElement
public class Automat {
    @XmlElement
    public Integer automatId;
    @XmlElement(required=true)
    public String automatName;
    @XmlElement(required=true)
    public String description;
    @XmlElement(required = true)
    public Integer slot1;
    @XmlElement(required = true)
    public Integer slot2;
    @XmlElement(required = true)
    public Integer slot3;
    @XmlElement
    public Boolean isWon;
}
