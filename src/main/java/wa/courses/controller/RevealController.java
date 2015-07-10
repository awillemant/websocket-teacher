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
import wa.courses.service.CommandService;
import wa.courses.service.SessionService;

@ServerEndpoint(
		value = "/control",
		decoders={WebCommandDecoder.class},
		encoders={WebCommandEncoder.class})
public class RevealController {


    private final static SessionService sessionService = new SessionService();
    private final static CommandService commandService = new CommandService();



	@OnOpen
	public void open(Session session){
        sessionService.addSession(session);

        commandService.handleConnection(session);


	}

	
	
	@OnClose
	public void close(Session session){
		sessionService.removeSession(session);

	}

	


	@OnMessage
	public void receiveInfo(WebCommand command, Session session) throws IOException {
		commandService.handle(command);
        sessionService.broadcast(command);

	}

}
