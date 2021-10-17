package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cargo {
    Distance distance;
    Profile profile;
    boolean fragile;
    WorkLoad loadFactor;
}
