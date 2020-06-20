package de.blackgen.ozobl3;

import de.blackgen.ozobl3.steam.Profiles.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SteamApi {

  @GET("/ISteamUser/GetPlayerSummaries/v0002/")
  Call<Response> getProfiles(
      @Query("key") String apiKey,
      @Query("steamids") String profile);

}
