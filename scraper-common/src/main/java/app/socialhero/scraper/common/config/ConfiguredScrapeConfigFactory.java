package app.socialhero.scraper.common.config;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RequiredArgsConstructor(staticName = "withGson")
public class ConfiguredScrapeConfigFactory implements ScrapeConfigFactory {

  private final Gson gson;

  @Override
  public ScrapeConfig createConfig() {
    ensureIsConfigPresent();
    return tryReadConfig();
  }

  private static final Path CONFIG_PATH = Paths.get("config.json");

  private void ensureIsConfigPresent() {
    if (Files.notExists(CONFIG_PATH)) {
      log.warn("Could not find config.json - creating new empty config");
      setupEmptyConfig();
    }
  }

  private void setupEmptyConfig() {
    ScrapeConfig scrapeConfig = ScrapeConfig.empty();
    try {
      Files.writeString(CONFIG_PATH, gson.toJson(scrapeConfig), StandardCharsets.UTF_8);
    } catch (IOException exception) {
      log.error("Could not write config.json - please check the file access");
    }
  }

  private ScrapeConfig tryReadConfig() {
    try {
      String configFileContent = Files.readString(CONFIG_PATH, StandardCharsets.UTF_8);
      return gson.fromJson(configFileContent, ScrapeConfig.class);
    } catch (IOException exception) {
      log.error(exception.getMessage());
    }
    return ScrapeConfig.empty();
  }
}
