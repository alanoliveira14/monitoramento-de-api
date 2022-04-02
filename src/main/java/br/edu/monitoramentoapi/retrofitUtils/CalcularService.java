package br.edu.monitoramentoapi.retrofitUtils;

import br.edu.monitoramentoapi.DTO.HealthCheckDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface CalcularService {

    @GET("calcular/somasimples/{numero1}/{numero2}")
    Call<String> somaSimples(@Path("numero1") Integer numero1, @Path("numero2") Integer numero2);

    @GET("healthcheck/")
    Call<HealthCheckDTO> healthcheck();

}
