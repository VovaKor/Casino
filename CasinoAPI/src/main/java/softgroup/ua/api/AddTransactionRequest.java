/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softgroup.ua.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexander
 */
@XmlRootElement
public class AddTransactionRequest {
    @XmlElement(required = true)
    public Transaction transaction;
}
