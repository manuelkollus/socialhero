package app.socialhero.scraper.common;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SessionId {
  private UUID sessionId;

  public UUID getSessionId() {
    if (sessionId == null) sessionId = UUID.randomUUID();
    return sessionId;
  }
}