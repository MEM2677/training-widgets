/*
*
* <Your licensing text here>
*
*/
package org.entando.training.widgets.aps.system.services.random;

import java.util.Properties;

import javax.ws.rs.core.Response;

import org.entando.entando.aps.system.services.api.IApiErrorCodes;
import org.entando.entando.aps.system.services.api.model.ApiException;
import org.entando.training.widgets.aps.system.services.random.api.JAXBNumber;
import org.entando.training.widgets.aps.system.servlet.random.RandManagerBeanContainer;

import com.agiletec.aps.system.common.AbstractService;
import com.agiletec.aps.system.exception.ApsSystemException;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomManager extends AbstractService implements IRandomManager {
    
    private static final Logger _logger =  LoggerFactory.getLogger(RandomManager.class);
    
    @Override
    public void init() throws Exception {
        _logger.debug("{} ready.", this.getClass().getName());
		RandManagerBeanContainer.setBean(this);
    }
    
    @Override
    public Number getRandom() throws ApsSystemException {
        Number number = new Number();
        Random rnd = new Random();
        
        try {
            number.setOutput(rnd.nextInt(101));
        } catch (Throwable t) {
            _logger.error("Error loading random ",  t);
            throw new ApsSystemException("Error loading random: ", t);
        }
        return number;
    }
    
    
    /**
     * GET http://localhost:8080/<portal>/legacyapi/rs/en/random
     * @param properties
     * @return
     * @throws Throwable
     */
    public JAXBNumber getRandomForApi(Properties properties) throws Throwable {
        int id = 0;
        JAXBNumber jaxbRandom = null;
        
        Number random = this.getRandom();
        if (null == random) {
            throw new ApiException(IApiErrorCodes.API_VALIDATION_ERROR, "Random with id '" + id + "' does not exist", Response.Status.CONFLICT);
        }
        jaxbRandom = new JAXBNumber(random);
        return jaxbRandom;
    }
    
    
}
