package br.edu.monitoramentoapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest

class MonitoramentoApiApplicationTests {

	@Test
	void contextLoads() {

		Boolean bool = true;

		for (int i = 0; i < 10; i++) {
			bool = retorna(bool);

		}

	}

	private boolean retorna(Boolean bool){

		System.out.println("dentro " + bool);

		return !bool;

	}

}
