package wa.courses.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wa.courses.data.WebCommand;

import javax.websocket.Session;

public class QuizCommandService implements CommandSubService {

    private final static Logger LOGGER = LoggerFactory.getLogger(QuizCommandService.class);

    private final static SessionService sessionService = SessionService.getInstance();


    @Override
    public void handle(WebCommand command, Session session) {
        LOGGER.info(command.getData() + "from " + session.getId());
        sessionService.broadcastToAdmins(command);
    }


    @Override
    public void handleConnection(Session session) {
    }
}
