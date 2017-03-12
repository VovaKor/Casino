/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softgroup.ua.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexche
 */
@XmlRootElement
public class TransactionsListReply extends GenericReply {
    @XmlElement
    public List<Transaction> transactions = new ArrayList<Transaction>();
}
