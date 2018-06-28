/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package org.entando.training.widgets.aps.system.servlet.random;

import java.io.IOException;
import java.util.Timer;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Entando
 */
@ServerEndpoint("/wstest")
public class RandomWebSocketManager {
    
    private static final Logger _logger =  LoggerFactory.getLogger(RandomWebSocketManager.class);
    
    public RandomWebSocketManager() {
        _logger.info("Initializing timer task for websocket manager"); 
    }
    
    @OnOpen
    public void onOpen(Session session) {
        WebSocketTimerTask wstt = new WebSocketTimerTask(session);
        this.timer = new Timer(true);
        this.timer.scheduleAtFixedRate(wstt, 0, 1000);
    }
    
    @OnMessage
    public void onMessage(String txt, Session session) throws IOException {
        _logger.info("Message received: " + txt);
        // echo in uppercase
        session.getBasicRemote().sendText(txt.toUpperCase());
    }
    
    @OnClose
    public void onClose(CloseReason reason, Session session) {
        _logger.info("Closing a WebSocket [reason: {}]",
                reason.getReasonPhrase());
        if (null != timer) {
            timer.cancel();
        }
    }
    
    private Timer timer;
}
