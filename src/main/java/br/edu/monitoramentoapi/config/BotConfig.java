package br.edu.monitoramentoapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("bot")
public class BotConfig {

    private String urlBot;
    private String userBot;
    private String passBot;

}
