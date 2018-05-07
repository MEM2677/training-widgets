/*
*
* <Your licensing text here>
*
*/
package org.entando.training.widgets.aps.system.services.random.api;

import javax.xml.bind.annotation.XmlElement;

import org.entando.entando.aps.system.services.api.model.AbstractApiResponseResult;
import org.entando.training.widgets.aps.system.services.random.api.JAXBNumber;


public class RandomResponseResult extends AbstractApiResponseResult {
    
    @Override
    @XmlElement(name = "number", required = false)
    public JAXBNumber getResult() {
        return (JAXBNumber) this.getMainResult();
    }
    
}