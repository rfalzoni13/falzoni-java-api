package br.com.falzoni.falzoni_java_api;

import br.com.falzoni.falzoni_java_api.controllers.entries.CustomerController;
import br.com.falzoni.falzoni_java_api.controllers.security.AuthenticationController;
import br.com.falzoni.falzoni_java_api.controllers.stock.ProductController;
import br.com.falzoni.falzoni_java_api.services.entries.CustomerService;
import br.com.falzoni.falzoni_java_api.services.security.AuthenticationService;
import br.com.falzoni.falzoni_java_api.services.stock.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FalzoniJavaApiApplicationTests {

	// Controllers
	@Autowired
	private AuthenticationController authenticationController;

	@Autowired
	private CustomerController customerController;

	@Autowired
	private ProductController productController;
	// End Controllers

	// Services
	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;
	// End Services


	@Test
	void contextLoads() throws Exception {
		assertThat(authenticationController).isNotNull();
		assertThat(customerController).isNotNull();
		assertThat(productController).isNotNull();

		assertThat(authenticationService).isNotNull();
		assertThat(customerService).isNotNull();
		assertThat(productService).isNotNull();
	}

}
