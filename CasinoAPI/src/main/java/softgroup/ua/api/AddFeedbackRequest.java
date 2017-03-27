package softgroup.ua.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class AddFeedbackRequest {

    @XmlElement(required = true)
    public FeedbackAPI feedback;
}
