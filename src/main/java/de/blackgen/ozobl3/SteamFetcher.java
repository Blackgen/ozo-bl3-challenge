package de.blackgen.ozobl3;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.ownedgames.Game;
import com.lukaspradel.steamapi.data.json.ownedgames.GetOwnedGames;
import com.lukaspradel.steamapi.data.json.playerstats.GetUserStatsForGame;
import com.lukaspradel.steamapi.data.json.playersummaries.GetPlayerSummaries;
import com.lukaspradel.steamapi.data.json.playersummaries.Player;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerSummariesRequest;
import com.lukaspradel.steamapi.webapi.request.GetUserStatsForGameRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;
import de.blackgen.ozobl3.data.UserProfile;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SteamFetcher {

  private final SteamWebApiClient client;

  public SteamFetcher() {
    String steamApiKey = System.getenv("STEAM_KEY");
    client = new SteamWebApiClient.SteamWebApiClientBuilder(Objects.requireNonNull(steamApiKey))
        .build();
  }

  public UserProfile fetch(String steamId) {
    GetPlayerSummariesRequest request = SteamWebApiRequestFactory.createGetPlayerSummariesRequest(
        Collections.singletonList(steamId));
    GetPlayerSummaries o = null;
    try {
      o = client.processRequest(request);
    } catch (SteamApiException e) {
      log.error("", e);
    }
    Player players = o.getResponse().getPlayers().get(0);
    return new UserProfile(players.getPersonaname(), players.getAvatar(), players.getSteamid());
  }

  public int getArchivementStats(int appID, String user) {
    GetUserStatsForGameRequest getUserStatsForGameRequest = SteamWebApiRequestFactory
        .createGetUserStatsForGameRequest(appID, user);
    GetUserStatsForGame o = null;
    try {
      o = client.processRequest(getUserStatsForGameRequest);
    } catch (SteamApiException e) {
      log.error("", e);
      return 0;
    }
    return o.getPlayerstats().getAchievements().size();
  }

  public Map<Integer, Integer> getPlaytimePerGameInMinutes(List<Integer> appIDs, String user) {
    GetOwnedGamesRequest getUserStatsForGameRequest = SteamWebApiRequestFactory
        .createGetOwnedGamesRequest(user, true, false, appIDs);
    GetOwnedGames o = null;
    try {
      o = client.processRequest(getUserStatsForGameRequest);
    } catch (SteamApiException e) {
      log.error("", e);
      return new HashMap<>();
    }
    return o.getResponse().getGames().stream()
        .collect(Collectors.toMap(Game::getAppid, Game::getPlaytimeForever));
  }

}
