package RetrofitPackage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface InterfazRestApi {
    @POST("register")
    Call<ResponseRegistro> registarUsuario(@Body PostRegistroLogin postRegistroLogin);

    @POST("login")
    Call<ResponseLogin> logearse(@Body PostRegistroLogin postRegistroLogin);


    @POST("event")
    Call<ResponseEvento> registrarEvento(@Header("token") String token, @Body PostEvento postEvento);
}
