package softgroup.ua.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Stanislav Rymar
 */

@XmlRootElement
public class UserListReply extends GenericReply {
    private List<User> list = new ArrayList<>();

    public List<User> getList() {
        return list;
    }

    @XmlElement
    public void setList(List<User> list) {
        this.list = list;
    }
}
