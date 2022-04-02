package br.edu.monitoramentoapi;

import br.edu.monitoramentoapi.DTO.HealthCheckDTO;
import br.edu.monitoramentoapi.retrofitUtils.CalculadoraRetrofitFactory;
import br.edu.monitoramentoapi.retrofitUtils.CalcularService;
import org.junit.Assert;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class RetrofitImplTest {

    @Test
    public void _1_testaRetrofit() throws IOException {

        CalcularService calcularService =  CalculadoraRetrofitFactory.FROM_URL("http://127.0.0.1:8080/");

        Call<HealthCheckDTO> call = calcularService.healthcheck();

        Response<HealthCheckDTO> response = call.execute();

        HealthCheckDTO healthCheckDTO = response.body();

        Assert.assertNotNull(healthCheckDTO);

        Assert.assertEquals("live", healthCheckDTO.getStatus());


    }


}
