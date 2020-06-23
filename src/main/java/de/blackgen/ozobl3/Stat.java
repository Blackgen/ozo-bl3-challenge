package de.blackgen.ozobl3;

import java.text.DecimalFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stat {

  private String name;
  private int yours;
  private int mine;
  private int total;
  private int yourPlaytime;
  private int myPlaytime;

  public int getYoursPercent() {
    return (int) (((float) yours / total) * 100);
  }

  public int getMinePercent() {
    return (int) (((float) mine / total) * 100);
  }

  public String getYourPlaytimeInHours() {
    float y1 = (float) yourPlaytime / 60.0f;
    DecimalFormat df = new DecimalFormat("#.00");
    return df.format(y1);
  }

  public String getMyPlaytimeInHours() {
    float y1 = (float) myPlaytime / 60.0f;
    DecimalFormat df = new DecimalFormat("#.00");
    return df.format(y1);
  }
}
