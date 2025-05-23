package fakerest.steps;

import java.io.IOException;

import fakerest.pages.fakeRestPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class fakerestSteps {
	
	fakeRestPage fake = new fakeRestPage();

	@Given("que acesso a API {string}")
	public void que_acesso_a_api(String url) {
	    fake.accessApi(url);
	}

	@When("realizo uma request GET para {string}")
	public void realizo_uma_request_get_para(String endpoint) throws IOException {
	    fake.sendRequestGetForEndpoint(endpoint);
	}

	@Then("eu valido a resposta com a lista completa de livros")
	public void eu_valido_a_resposta_com_a_lista_completa_de_livros() throws IOException {
	    fake.validateResponseBookListComplete();
	}

	@When("realizo uma request GET para {string} e id")
	public void realizo_uma_request_get_para_e_id(String endpoint) throws IOException {
	    fake.sendRequestGetForSpecificId(endpoint);
	}

	@Then("eu valido os dados do livro específico")
	public void eu_valido_os_dados_do_livro_específico() throws IOException {
	    fake.validateResponseSpecificBookId();
	}

	@Then("eu valido que o erro retornado tem o status code {string}")
	public void eu_valido_que_o_erro_retornado_tem_o_status_code(String string) {
		   throw new io.cucumber.java.PendingException();
	}
	
	
	@When("realizo uma request POST para {string}")
	public void realizo_uma_request_post_para(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido que a criação foi bem-sucedida")
	public void eu_valido_que_a_criação_foi_bem_sucedida() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("realizo uma request POST para {string} com dados inválidos")
	public void realizo_uma_request_post_para_com_dados_inválidos(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido o erro retornado")
	public void eu_valido_o_erro_retornado() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("realizo uma request PUT para {string} com novos dados")
	public void realizo_uma_request_put_para_com_novos_dados(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido que os dados foram atualizados corretamente com status {string}")
	public void eu_valido_que_os_dados_foram_atualizados_corretamente_com_status(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("realizo uma request DELETE para {string}")
	public void realizo_uma_request_delete_para(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido o status de resposta {string}")
	public void eu_valido_o_status_de_resposta(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido a resposta com a lista completa de atividades")
	public void eu_valido_a_resposta_com_a_lista_completa_de_atividades() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido os dados da atividade específica")
	public void eu_valido_os_dados_da_atividade_específica() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido a resposta com a lista completa de autores")
	public void eu_valido_a_resposta_com_a_lista_completa_de_autores() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido os dados do autor específico")
	public void eu_valido_os_dados_do_autor_específico() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido os autores relacionados ao livro")
	public void eu_valido_os_autores_relacionados_ao_livro() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido a resposta com a lista completa de usuários")
	public void eu_valido_a_resposta_com_a_lista_completa_de_usuários() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido os dados do usuário específico")
	public void eu_valido_os_dados_do_usuário_específico() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido a resposta com a lista completa de capas")
	public void eu_valido_a_resposta_com_a_lista_completa_de_capas() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido os dados da capa específica")
	public void eu_valido_os_dados_da_capa_específica() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("eu valido a imagem de capa do livro")
	public void eu_valido_a_imagem_de_capa_do_livro() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
