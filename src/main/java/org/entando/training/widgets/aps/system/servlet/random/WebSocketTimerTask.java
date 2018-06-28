/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.training.widgets.aps.system.servlet.random;

import java.util.TimerTask;
import javax.websocket.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Entando
 */
public class WebSocketTimerTask extends TimerTask {
    
    private static final Logger _logger =  LoggerFactory.getLogger(WebSocketTimerTask.class);

    public WebSocketTimerTask(Session session) {
        this.session = session;
    }
    
    @Override
    public void run() {
        try {
            String payload = RandManagerBeanContainer.getRandomNumberPayload();
            // send payload
            if (null != session) {
                session.getBasicRemote().sendText(payload);
            } else {
                _logger.error("no websocket session to deliver data to");
            }
        } catch (Throwable t) {
            _logger.error("could not deliver payload", t);
        }
    }
    
    private Session session;
}
