package wa.courses.converters;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import wa.courses.data.WebCommand;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebCommandEncoder implements Encoder.Text<WebCommand> {

	private ObjectMapper mapper;
	
	@Override
	public void destroy() {
	}

	@Override
	public void init(EndpointConfig arg0) {
		mapper  = new ObjectMapper();
	}

	@Override
	public String encode(WebCommand command)  {
		try {
			return mapper.writeValueAsString(command);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	
	

}
