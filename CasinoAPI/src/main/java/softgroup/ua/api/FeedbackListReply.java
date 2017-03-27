package softgroup.ua.api;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class FeedbackListReply extends GenericReply{

    @XmlElement
    public List<FeedbackAPI> feedbacks = new ArrayList<>();
}
