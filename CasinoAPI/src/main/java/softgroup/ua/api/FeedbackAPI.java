package softgroup.ua.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class FeedbackAPI {

    @XmlElement(required = false)
    public String feedbackId;

    @XmlElement(required = false)
    public String message;

    @XmlElement(required = false)
    public String email;

    @XmlElement(required = false)
    public Date messageTime;
}
