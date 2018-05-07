package org.entando.training.widgets.aps.system.servlet.random;

import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomWebsocketManager extends WebSocketServlet {
    
    private static final Logger _logger = LoggerFactory.getLogger(RandomWebsocketManager.class);
    
    private final Set<RandomNumberSocket> _members = new CopyOnWriteArraySet<RandomNumberSocket>();
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    
    @Override
    public void init() throws ServletException {
        super.init();
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                _logger.debug("Running Server Message Sending");
                for(RandomNumberSocket member : _members){
                    _logger.debug("Trying to send to Member!");
                    if(member.isOpen()){
                        _logger.debug("Sending!");
                        try {
                            String payload = RandManagerBeanContainer.getRandomNumberPayload();
                            
                            member.sendMessage(payload);
                        } catch (IOException e) {
                            _logger.error("Error in Websocket manager", e);
                        }
                    }
                }
            }
        }, 2, 1, TimeUnit.SECONDS);
        
    }
    
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getNamedDispatcher("default").forward(request,
                response);
    }
    
    public WebSocket doWebSocketConnect(HttpServletRequest request,
            String protocol) {
        return new RandomNumberSocket();
    }
    
    class RandomNumberSocket implements WebSocket.OnTextMessage {
        private Connection _connection;
        
        @Override
        public void onClose(int closeCode, String message) {
            _members.remove(this);
        }
        
        public void sendMessage(String data) throws IOException {
            _connection.sendMessage(data);
        }
        
        @Override
        public void onMessage(String data) {
            _logger.info("Received websocket request '{}': ", data);
        }
        
        public boolean isOpen() {
            return _connection.isOpen();
        }
        
        @Override
        public void onOpen(Connection connection) {
            _members.add(this);
            _connection = connection;
            try {
                connection.sendMessage("Server received Web Socket upgrade and added it to Receiver List.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
