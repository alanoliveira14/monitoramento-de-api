package br.edu.monitoramentoapi;

import br.edu.monitoramentoapi.service.MonitoramentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class MonitoramentoApiApplication {

	@Autowired
	MonitoramentoService monitoramentoService;

	public static void main(String[] args) {

		SpringApplication.run(MonitoramentoApiApplication.class, args);

	}


	@PostConstruct
	public void execute() throws InterruptedException {

		Boolean funcionouCorretamente = true;

		while (true) {

			funcionouCorretamente = monitoramentoService.execute(funcionouCorretamente);

			Thread.sleep(60000);

		}
	}

}
