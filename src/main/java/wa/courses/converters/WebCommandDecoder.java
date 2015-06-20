package wa.courses.converters;

import java.io.IOException;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import wa.courses.data.WebCommand;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WebCommandDecoder implements Decoder.Text<WebCommand> {

	private ObjectMapper mapper;
	
	@Override
	public void destroy() {
	}

	@Override
	public void init(EndpointConfig arg0) {
		mapper  = new ObjectMapper();
	}

	@Override
	public WebCommand decode(String json) throws DecodeException {
		try {
			return mapper.readValue(json, WebCommand.class);
		} catch (IOException e) {
			throw new DecodeException(json,"Impossible à decoder", e);
		}
	}

	@Override
	public boolean willDecode(String json) {
		return json.startsWith("{") && json.endsWith("}");
	}

}
