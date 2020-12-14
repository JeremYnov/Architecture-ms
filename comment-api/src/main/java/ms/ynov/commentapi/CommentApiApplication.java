package ms.ynov.commentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CommentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentApiApplication.class, args);
	}

}
