package app.socialhero.scraper;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class JobCreatedEvent extends ApplicationEvent {
  @Getter
  private final Job job;

  public JobCreatedEvent(Object source, Job job) {
    super(source);
    this.job = job;
  }

  public static JobCreatedEvent of(Object source, Job job) {
    return new JobCreatedEvent(source, job);
  }
}