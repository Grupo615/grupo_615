package RetrofitPackage;

public class ResponseRegistro {
    public String state;
    public String env;
    /* public PostRegistroLogin user;

     public PostRegistroLogin getUser() {
         return user;
     }

     public void setUser(PostRegistroLogin user) {
         this.user = user;
     }*/
    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
