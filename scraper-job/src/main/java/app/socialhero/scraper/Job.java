package app.socialhero.scraper;

import lombok.*;

import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {
  private int id;
  private Map<String, String> context;

  public Optional<String> find(String key) {
    return Optional.ofNullable(context.get(key));
  }
}
