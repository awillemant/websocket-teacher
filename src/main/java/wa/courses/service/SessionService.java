package wa.courses.service;

import wa.courses.data.WebCommand;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SessionService {
    private final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());


    public void addSession(Session session) {
        sessions.add(session);
        broadcastSessionsSize();
    }

    public void removeSession(Session session) {
        sessions.remove(session);
        broadcastSessionsSize();
    }

    public void broadcast(WebCommand command) {
        for (Session s : sessions) {
            if (s.isOpen()) {
                sendCommand(s, command);
            }
        }
    }

    private void broadcastSessionsSize() {
        try {
            WebCommand commande = new WebCommand("sessions",Integer.toString(sessions.size()));
            for (Session s : sessions) {
                if (s.isOpen()) {
                    s.getBasicRemote().sendObject(commande);
                }
            }
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }

    }

    public void sendCommand(Session session, WebCommand command) {
        try {
            if(command!=null){
                session.getBasicRemote().sendObject(command);
            }
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }



}
