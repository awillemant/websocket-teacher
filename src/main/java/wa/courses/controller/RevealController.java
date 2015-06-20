package wa.courses.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import wa.courses.converters.WebCommandDecoder;
import wa.courses.converters.WebCommandEncoder;
import wa.courses.data.WebCommand;

@ServerEndpoint(
		value = "/control",
		decoders={WebCommandDecoder.class},
		encoders={WebCommandEncoder.class})
public class RevealController {

	private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
	private static WebCommand lastSlideCommand;
	
	@OnOpen
	public void open(Session session){
		System.err.println("Connexion de "+session.getId());
		sessions.add(session);
		broadcastSessionsSize();
		if(lastSlideCommand!=null){
			try {
				session.getBasicRemote().sendObject(lastSlideCommand);
			} catch (IOException | EncodeException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	@OnClose
	public void close(Session session){
		System.err.println("Deconnexion de "+session.getId());
		sessions.remove(session);
		broadcastSessionsSize();
	}
	
	private void broadcastSessionsSize() {
		broadcast(new WebCommand("sessions",Integer.toString(sessions.size())));
	}
	
	


	@OnMessage
	public void receiveInfo(WebCommand command, Session session) throws IOException {
		System.out.println("Message recu de "+session.getId());
		if(command.getCommand().equals("slide")){
			lastSlideCommand = command;
		}
		broadcast(command);

	}

	private void broadcast(WebCommand command) {
		for (Session s : sessions) {
			if (s.isOpen()) {
				sendCommand(s, command);
			}
		}
	}
	
	

	
	
	private void sendCommand(Session session, WebCommand command) {
		try {
			if(command!=null){
				session.getBasicRemote().sendObject(command);
			}
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
		}
	}
	
	
}
