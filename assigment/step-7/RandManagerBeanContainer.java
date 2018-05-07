package org.entando.training.widgets.aps.system.servlet.random;

import java.util.Date;

import org.entando.training.widgets.aps.system.services.random.IRandomManager;
import org.entando.training.widgets.aps.system.services.random.Number;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class RandManagerBeanContainer {
    
    private static final Logger _logger = LoggerFactory.getLogger(RandManagerBeanContainer.class);
    
    private RandManagerBeanContainer() { }
    
    public static RandManagerBeanContainer getInstance() {
        if (null == _instance) {
            _instance = new RandManagerBeanContainer();
        }
        return _instance;
    }
    
    public static void setBean(IRandomManager manager) {
        RandManagerBeanContainer instance = getInstance();
        if (null != manager) {
            instance._manager = manager;
        }
    }
    
    public static String getRandomNumberPayload() {
        RandManagerBeanContainer instance = getInstance();
        JSONObject json = new JSONObject();
        
        try {
            if (null != instance._manager) {
                Number number = instance._manager.getRandom();
                
                json.put("output", number.getOutput());
                json.put("timestamp", new Date().getTime());
            }
        } catch (Throwable t) {
            _logger.error("Error generating payload ", t);
        }
        return json.toString();
    }
    
    private static RandManagerBeanContainer _instance;
    private IRandomManager _manager;
}
