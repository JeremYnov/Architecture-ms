package ms.ynov.articleapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ynov.ms")
public class CustomProperties {
	private String apiUrl;
	
	public String getApiUrl() {
		return apiUrl;
	}
	
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
}
