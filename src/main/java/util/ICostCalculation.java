package util;

import model.Profile;
import model.WorkLoad;

public interface ICostCalculation {

    int getFinalPrise(int range, Profile profile, boolean fragility, WorkLoad currentLoad);

}
