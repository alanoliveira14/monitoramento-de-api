package br.edu.monitoramentoapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("api")
public class ApiValidateConfig {

    private String urlApi;

}

