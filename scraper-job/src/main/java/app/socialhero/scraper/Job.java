package app.socialhero.scraper;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {
  private int id;
  private Map<String, String> context;
}
