package RetrofitPackage;

public class ResponseEventoArray {
    /* "type_events": "Broadcast Receiver",
            "state": "Activo",
            "description": "Broadcast que detecta la desconexión 3G",
            "group": 123*/
    private String type_events;
    private String state;
    private String description;

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
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

    public int group;
}
