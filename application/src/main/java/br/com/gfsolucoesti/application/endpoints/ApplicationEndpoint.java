package br.com.gfsolucoesti.application.endpoints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationEndpoint {

  @RequestMapping(value = "/available")
  public String available() {
    return "Resource available";
  }

  @RequestMapping(value = "/checked-out")
  public String checkedOut() {
    return "Resource Checked OUT";
  }

}
