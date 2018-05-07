/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.training.widgets.aps.system.services.random.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.entando.entando.aps.system.services.api.model.AbstractApiResponse;
import org.entando.entando.aps.system.services.api.model.AbstractApiResponseResult;


@XmlRootElement(name = "response")
public class RandomResponse extends AbstractApiResponse {
    
    @Override
    @XmlElement(name = "result", required = true)
    public RandomResponseResult getResult() {
        return (RandomResponseResult) super.getResult();
    }
    
    @Override
    protected AbstractApiResponseResult createResponseResultInstance() {
        return new RandomResponseResult();
    }
    
}