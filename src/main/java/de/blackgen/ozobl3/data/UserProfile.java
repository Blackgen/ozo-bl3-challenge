package de.blackgen.ozobl3.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfile {

  private String name;
  private String avatarIcon;
  private String steamId;
}
