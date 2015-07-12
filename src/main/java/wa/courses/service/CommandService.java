package wa.courses.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wa.courses.data.WebCommand;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

public class CommandService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommandService.class);

    Map<String, CommandSubService> subServices;


    public CommandService() {
        subServices = new HashMap<>();
        subServices.put("slide", new SlideCommandService());
        subServices.put("quiz", new QuizCommandService());
    }


    public void handle(WebCommand command, Session session) {
        String commandType = command.getCommand();
        if (commandType != null && !commandType.isEmpty() && subServices.containsKey(commandType)) {
            subServices.get(commandType).handle(command, session);
        }
    }


    public void handleConnection(Session session) {
        for (Map.Entry<String, CommandSubService> entry : subServices.entrySet()) {
            entry.getValue().handleConnection(session);
        }
    }
}
