package app.socialhero.apiserver.creator.tiktok;

import app.socialhero.apiserver.creator.Creator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TikTokAccount {
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  private int id;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "creator_id", nullable = false)
  private Creator creator;

  @Column(nullable = false, name = "account_name")
  private String name;

  @Builder.Default
  @Column(nullable = false)
  private Long followers = 0L;

  @Builder.Default
  @Column(nullable = false)
  private Long following = 0L;

  @Builder.Default
  @Column(nullable = false)
  private Long likes = 0L;
}
