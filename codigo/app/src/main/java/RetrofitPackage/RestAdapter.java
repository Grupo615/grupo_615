package RetrofitPackage;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAdapter {
    public InterfazRestApi conectar() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://so-unlam.net.ar/api/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(InterfazRestApi.class);
    }
}
