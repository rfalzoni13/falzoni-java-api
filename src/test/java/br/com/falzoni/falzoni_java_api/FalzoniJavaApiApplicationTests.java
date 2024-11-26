package br.com.falzoni.falzoni_java_api;

import br.com.falzoni.falzoni_java_api.controllers.register.CustomerController;
import br.com.falzoni.falzoni_java_api.controllers.security.AuthenticationController;
import br.com.falzoni.falzoni_java_api.controllers.stock.ProductController;
import br.com.falzoni.falzoni_java_api.services.classes.security.AuthenticationService;
import br.com.falzoni.falzoni_java_api.services.interfaces.register.CustomerService;
import br.com.falzoni.falzoni_java_api.services.interfaces.stock.ProductService;
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
		assertThat(this.authenticationController).isNotNull();
		assertThat(this.customerController).isNotNull();
		assertThat(this.productController).isNotNull();

		assertThat(this.authenticationService).isNotNull();
		assertThat(this.customerService).isNotNull();
		assertThat(this.productService).isNotNull();
	}

}
