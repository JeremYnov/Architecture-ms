package ms.ynov.articleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ArticleApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleApiApplication.class, args);
	}

}
