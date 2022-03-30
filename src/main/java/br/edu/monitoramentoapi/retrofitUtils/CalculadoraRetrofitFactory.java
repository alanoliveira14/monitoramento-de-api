package br.edu.monitoramentoapi.retrofitUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CalculadoraRetrofitFactory {

    public static CalcularService FROM_URL(String url){

        return new Retrofit.Builder()
                .baseUrl(url)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CalcularService.class);

    }

}
