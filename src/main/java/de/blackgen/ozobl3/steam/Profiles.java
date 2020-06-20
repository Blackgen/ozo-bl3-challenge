package de.blackgen.ozobl3.steam;

import java.util.List;
import lombok.Data;

@Data
public class Profiles {

  private Response response;

  @Data
  public static class Response {

    private List<Player> players;

    @Data
    public static class Player {

      private String steamid;
      private String personaname;
      private String avatar;
    }

  }

}

