package br.edu.utfpr.cp.espjava.crudcidades;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.exist;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrudCidadesApplicationTests {
	
	@LocalServerPort
	private Integer port;

	@Test
	public void testaCRUDCidade() {
		open(String.format("http://localhost:%d/", port));

		$(By.name("nome")).setValue("Curitiba");
		$(By.name("estado")).setValue("PR");
		$("#submit-criar").click();

		$(By.linkText("ALTERAR")).click();
		$("#nome").setValue("São Paulo");
		$("#submit-alterar").click();
		$(By.tagName("td")).shouldHave(text("São Paulo")); 

		$(By.linkText("ALTERAR")).click();
		$("#estado").setValue("SP");
		$("#submit-alterar").click();
		$(By.tagName("tbody")).shouldHave(text("SP")); 

		$(By.linkText("EXCLUIR")).click();
		$(By.tagName("td")).shouldNot(exist);
	}

}
