package br.com.pedrohenriquemoura.conhecendoretrofit2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Services {

    @GET("{cep}")
    Call<Cep> getAddress(@Path("cep") String cep);
}
