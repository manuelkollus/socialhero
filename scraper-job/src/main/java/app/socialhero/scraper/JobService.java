package app.socialhero.scraper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class JobService {
  private final Queue<Job> queue = new ConcurrentLinkedQueue<>();
  private final ApplicationEventPublisher eventPublisher;
  private final AsyncTaskExecutor asyncTaskExecutor;
  private final AtomicInteger numRunningEvents = new AtomicInteger(0);

  public synchronized void add(Job job) {
    log.info("Storing job {} in queue", job.getId());
    queue.add(job);
    checkQueue();
  }

  private void checkQueue() {
    int numEventsToExecute = computeNumberEventsToExecute();
    log.info("Processing job queue, numRunningEvents={}, numEventsToExecute={}", numRunningEvents.get(), numEventsToExecute);
    while (numEventsToExecute > 0 && !queue.isEmpty()) {
      Job job = queue.poll();

      // Process the job here...
      log.info("Processing job {}", job.getId());

      asyncTaskExecutor.execute(() -> triggerJobCreated(job));
    }
  }

  private static final int MAX_CONCURRENT_EVENTS = 15;

  private int computeNumberEventsToExecute() {
    return Math.max(0, MAX_CONCURRENT_EVENTS - numRunningEvents.get());
  }

  private void triggerJobCreated(Job job) {
    numRunningEvents.incrementAndGet();

    JobCreatedEvent event = JobCreatedEvent.of(this, job);
    eventPublisher.publishEvent(event);
  }

  @EventListener(classes = JobFinishEvent.class)
  public void finishEvent(JobFinishEvent finishEvent) {
    log.info("Finished job, jobId={}, status={}", finishEvent.getJobId(), finishEvent.getStatus());
    numRunningEvents.decrementAndGet();
  }
}
