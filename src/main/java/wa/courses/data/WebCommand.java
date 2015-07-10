package wa.courses.data;

public class WebCommand {

    private String command;

    private String data;


    public WebCommand() {
        super();
    }


    public WebCommand(String command, String data) {
        super();
        this.command = command;
        this.data = data;
    }


    public String getCommand() {
        return command;
    }


    public void setCommand(String command) {
        this.command = command;
    }


    public String getData() {
        return data;
    }


    public void setData(String data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "WebCommand [command=" + command + ", data=" + data + "]";
    }
}
