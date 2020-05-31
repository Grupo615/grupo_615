package RetrofitPackage;

public class ResponseEvento {
    /*"state": "success",
"env": "TEST/DEV",
"event": {
            "type_events": "Broadcast Receiver",
            "state": "Activo",
            "description": "Broadcast que detecta la desconexión 3G",
            "group": 123
}
}*/
    private String state;
    private String env;
    private ResponseEventoArray event;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public ResponseEventoArray getEvent() {
        return event;
    }

    public void setEvent(ResponseEventoArray event) {
        this.event = event;
    }


}
