package app.socialhero.scraper.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class GsonConfiguration {

  @Bean
  @Primary
  Gson bindGson() {
    return new GsonBuilder()
      .disableHtmlEscaping()
      .setPrettyPrinting()
      .create();
  }
}
