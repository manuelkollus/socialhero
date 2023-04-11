package app.socialhero.scraper;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class JobFinishEvent extends ApplicationEvent {
  public enum Status {
    SUCCESSFUL,
    FAILED
  }

  private final int jobId;
  private final Status status;

  public JobFinishEvent(int jobId, Status status, Object source) {
    super(source);
    this.jobId = jobId;
    this.status = status;
  }

  public static JobFinishEvent of(int jobId, Status status, Object source) {
    return new JobFinishEvent(jobId, status, source);
  }
}
