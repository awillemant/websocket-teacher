package wa.courses.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wa.courses.data.WebCommand;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;

public class SlideCommandService implements CommandSubService{

    private final static Logger LOGGER = LoggerFactory.getLogger(SlideCommandService.class);
    
    private WebCommand lastSlideCommand;


    @Override
    public void handle(WebCommand command) {
        lastSlideCommand = command;
    }

    @Override
    public void handleConnection(Session session) {
        if(lastSlideCommand!=null){
            try {
                session.getBasicRemote().sendObject(lastSlideCommand);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }

    public WebCommand getLastSlideCommand() {
        return lastSlideCommand;
    }


}
