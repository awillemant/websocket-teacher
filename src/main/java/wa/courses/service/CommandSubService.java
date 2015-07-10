package wa.courses.service;

import wa.courses.data.WebCommand;

import javax.websocket.Session;

public interface CommandSubService {

    void handle(WebCommand command);

    void handleConnection(Session session);
}
