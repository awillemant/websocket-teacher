package wa.courses.service;

import wa.courses.data.WebCommand;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

public class CommandService {

    public SlideCommandService slideCommandService = new SlideCommandService();

    Map<String,CommandSubService> subServices;

    public CommandService() {
        subServices = new HashMap<>();
        subServices.put("slide",slideCommandService);
    }

    public void handle(WebCommand command) {
        String commandType = command.getCommand();
        if(commandType !=null && !commandType.isEmpty() && subServices.containsKey(commandType)){
            subServices.get(commandType).handle(command);
        }
    }


    public void handleConnection(Session session) {
        for(Map.Entry<String,CommandSubService> entry:subServices.entrySet()){
            entry.getValue().handleConnection(session);
        }
    }
}
