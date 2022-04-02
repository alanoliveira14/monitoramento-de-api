package br.edu.monitoramentoapi.service;

import br.edu.monitoramentoapi.DTO.HealthCheckDTO;
import br.edu.monitoramentoapi.config.ApiValidateConfig;
import br.edu.monitoramentoapi.config.BotConfig;
import br.edu.monitoramentoapi.retrofitUtils.CalculadoraRetrofitFactory;
import br.edu.monitoramentoapi.retrofitUtils.CalcularService;
import com.github.baloise.rocketchatrestclient.RocketChatClient;
import com.github.baloise.rocketchatrestclient.model.Channel;
import com.github.baloise.rocketchatrestclient.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@Service
public class MonitoramentoService {

    private final String MENSAGEM_DE_SUCESSO = "API Online e funcionando corretamente";
    private final String MENSAGEM_DE_ERRO = "API Offline ou com problemas";
    private final String MENSAGEM_DE_NORMALIZACAO = "API voltou a responder corretamente";

    @Autowired
    public MonitoramentoService(BotConfig botConfig, ApiValidateConfig apiConfig) {
        this.botConfig = botConfig;
        this.apiConfig = apiConfig;
    }

    BotConfig botConfig;
    ApiValidateConfig apiConfig;

    public boolean execute(Boolean funcionouCorretamente) {

        log.info("Iniciando novo request de monitoramento da API [{}]", apiConfig.getUrlApi());

        Response<HealthCheckDTO> response = null;
        try {

            CalcularService calcularService = CalculadoraRetrofitFactory.FROM_URL(apiConfig.getUrlApi());

            Call<HealthCheckDTO> call = calcularService.healthcheck();

            response = call.execute();

            if (response.isSuccessful()) {

                if (!funcionouCorretamente) {
                    mandaMensagem(MENSAGEM_DE_NORMALIZACAO);
                    log.info("API voltou a responder!");
                } /*else {
                    mandaMensagem(MENSAGEM_DE_SUCESSO);
                    log.info("API Respondendo normalmente!");
                }*/

            } else {
                mandaMensagem(MENSAGEM_DE_ERRO);
                log.info("API não respondeu com sucesso!");
            }

            log.info("Finalizado request de status");

            return response.isSuccessful();

        } catch (Exception e) {
            log.error("[MonitamentoService][execute] Erro ao monitorar app", e);
            return false;
        }

    }

    private void mandaMensagem(String mensagem) throws IOException {

        log.info("Notificando no RocketChat [{}]", botConfig.getUrlBot());

        RocketChatClient rocketChatClient = new RocketChatClient(botConfig.getUrlBot(), botConfig.getUserBot(), botConfig.getPassBot());

        Channel[] list = rocketChatClient.getChannelsApi().list();

        System.out.println(list[0].getRoomId());

        Message message = new Message();

        message.setText(mensagem);

        rocketChatClient.getChatApi().postMessage(list[0], message);
    }

}
