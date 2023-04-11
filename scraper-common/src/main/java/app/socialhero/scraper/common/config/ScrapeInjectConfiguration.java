package app.socialhero.scraper.common.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ScrapeInjectConfiguration {
  @Bean
  @Primary
  ScrapeConfigFactory bindConfigFactory(Gson gson) {
    return ConfiguredScrapeConfigFactory.withGson(gson);
  }
}
