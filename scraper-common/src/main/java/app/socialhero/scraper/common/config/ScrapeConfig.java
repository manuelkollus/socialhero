package app.socialhero.scraper.common.config;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScrapeConfig {
  private String name;
  private String mainBackendUrl;
  private String scrapeHost;
  private String secretKey;

  public static ScrapeConfig empty() {
    return new ScrapeConfig();
  }
}