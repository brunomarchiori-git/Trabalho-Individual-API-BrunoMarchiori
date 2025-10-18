package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

	@Bean
	public OpenAPI myOpenAPI() {
		Server devServer = new Server();
		devServer.setUrl("http://localhost:8080");
		devServer.setDescription("Servidor Local");
		
		Contact contato = new Contact();
		contato.setEmail("bruno@email.com");
		contato.setName("Bruno Marchiori");
		
		License apacheLicense = new License().name("Apache License 2.0").url("https://www.apache.org/licenses/LICENSE-2.0");
		
		Info info = new Info()
		.title("API de musica - Trabalho individual Bruno Marchiori")
		.version("1.0")
		.contact(contato)
		.description("API de musicas desenvolvida para o Trabalho Individual de API")
		.termsOfService("https://www.serratec.org.br/")
		.license(apacheLicense);
		
		return new OpenAPI().info(info).servers(List.of(devServer));
	}
	
}
