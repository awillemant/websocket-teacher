package wa.courses.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wa.courses.data.WebCommand;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class WebCommandEncoder implements Encoder.Text<WebCommand> {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebCommandEncoder.class);

    private ObjectMapper mapper;


    @Override
    public void destroy() {
    }


    @Override
    public void init(EndpointConfig arg0) {
        mapper = new ObjectMapper();
    }


    @Override
    public String encode(WebCommand command) {
        try {
            return mapper.writeValueAsString(command);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
