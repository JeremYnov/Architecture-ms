package ms.ynov.webclient.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import ms.ynov.webclient.CustomProperties;

@Repository
public class GenericProxy {
	
    @Autowired
	protected CustomProperties props;
	
	@Autowired
	protected RestTemplate restTemplate;
}
