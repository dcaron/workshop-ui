package io.pivotal.workshop.ui;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class CompanyService {

  private final RestTemplate restTemplate;

  public CompanyService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @HystrixCommand(fallbackMethod = "defaultCompany")
  public String getCompany() {
    String fortune = restTemplate.getForObject("http://company-service/random", String.class);
    return fortune;
  }

  public String defaultCompany(){
    log.debug("Default fortune used.");
    return "AFKLM.";
  }

}
