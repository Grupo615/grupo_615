package RetrofitPackage;

public class PostEvento {
    /*“env”: “TEST”,”DEV”,
"type_events": String(255), //Debe indicarse si es un evento de un sensor, Login, Service,
//Broadcat, etc
"state":"ACTIVO/INACTIVO",
"description": String(255) //Poner un descripción de lo sucedido en el evento registrado*/
    private String env;
    private String type_events;
    private String state;
    private String description;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getType_events() {
        return type_events;
    }

    public void setType_events(String type_events) {
        this.type_events = type_events;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
