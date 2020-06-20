package de.blackgen.ozobl3;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stat {

  private String name;
  private int yours;
  private int mine;
  private int total;

  public int getYoursPercent() {
    return (int) (((float) yours / total) * 100);
  }

  public int getMinePercent() {
    return (int) (((float) mine / total) * 100);
  }
}
