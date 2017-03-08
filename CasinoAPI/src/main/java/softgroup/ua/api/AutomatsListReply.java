package softgroup.ua.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вова on 08.03.2017.
 */
@XmlRootElement
public class AutomatsListReply extends GenericReply{
    @XmlElement(required=true)
    public List<Automat> automats = new ArrayList<>();
}
