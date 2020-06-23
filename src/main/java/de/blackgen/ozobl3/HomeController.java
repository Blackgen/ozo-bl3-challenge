package de.blackgen.ozobl3;

import de.blackgen.ozobl3.data.UserProfile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  public static final int BL1 = 729040;
  public static final int BL2 = 49520;
  public static final int BLP = 261640;

  @Inject
  private SteamFetcher steamFetcher;

  @GetMapping("/")
  public String hello(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession();
    Object user = session.getAttribute("user");
    Object books = session.getAttribute("books");
    if (user != null && books == null) {
      UserProfile u = (UserProfile) user;
      ArrayList<Stat> stats = new ArrayList<>();
      int bl1 = steamFetcher.getArchivementStats(BL1, u.getSteamId());
      int bl2 = steamFetcher.getArchivementStats(BL2, u.getSteamId());
      int blp = steamFetcher.getArchivementStats(BLP, u.getSteamId());
      // My stats are hardcoded.. But I don't expect them to change during the next days
      Map<Integer, Integer> yourPlaytime = steamFetcher
          .getPlaytimePerGameInMinutes(Arrays.asList(BL1, BL2, BLP), u.getSteamId());
//      Map<Integer, Integer> myPlaytime = steamFetcher
//          .getPlaytimePerGameInMinutes(Arrays.asList(BL1, BL2, BLP), u.getSteamId());
      stats.add(new Stat("Borderlands (GOTY) enhanced", bl1, 39, 80, yourPlaytime.get(BL1),
          1284));
      stats.add(new Stat("Borderlands 2", bl2, 46, 75, yourPlaytime.get(BL2),
          6680));
      stats.add(new Stat("Borderlands Pre-Sequel", blp, 34, 63, yourPlaytime.get(BLP),
          3569));
      session.setAttribute("books", stats);
      model.addAttribute("user", user);
      model.addAttribute("books", stats);
    } else {
      model.addAttribute("user", user);
      model.addAttribute("books", books);
    }

    return "index";
  }
}
