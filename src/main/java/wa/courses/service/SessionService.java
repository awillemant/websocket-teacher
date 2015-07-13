package wa.courses.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wa.courses.data.WebCommand;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SessionService {

    private final static Logger LOGGER = LoggerFactory.getLogger(SessionService.class);

    private final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    private final Set<Session> admins = Collections.synchronizedSet(new HashSet<Session>());

    private static SessionService instance;


    private SessionService() {
    }


    public static SessionService getInstance() {
        if (instance == null) {
            instance = new SessionService();
        }
        ;
        return instance;
    }


    public void addSession(Session session) {
        sessions.add(session);
        broadcastSessionsSize();
    }


    public void removeSession(Session session) {
        sessions.remove(session);
        broadcastSessionsSize();
    }


    public void addAdminSession(Session session) {
        admins.add(session);
    }


    public void removeAdminSession(Session session) {
        admins.remove(session);
    }


    private void broadcastToSessions(WebCommand command, Set<Session> sessions) {
        for (Session s : sessions) {
            if (s.isOpen()) {
                sendCommand(s, command);
            }
        }
    }


    public void broadcast(WebCommand command) {
        broadcastToSessions(command, sessions);
    }


    public void broadcastToAdmins(WebCommand command) {
        broadcastToSessions(command, admins);
    }


    private void broadcastSessionsSize() {
        try {
            WebCommand commande = new WebCommand("sessions", Integer.toString(sessions.size()));
            for (Session s : admins) {
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
            if (command != null) {
                session.getBasicRemote().sendObject(command);
            }
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }
}
