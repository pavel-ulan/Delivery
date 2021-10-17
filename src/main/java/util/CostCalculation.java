package util;

import exeptions.FragileException;
import model.Cargo;
import model.Distance;
import model.Profile;
import model.WorkLoad;

public class CostCalculation {
    public static int getFinalPrise(int range, Profile profile, boolean fragility, WorkLoad currentLoad) throws FragileException {
        float finalPrice = priceCalculation(loadCargoData(range, profile, fragility, currentLoad));
        return checkFinalPriceLimit(finalPrice);
    }

    private static Cargo loadCargoData(int range, Profile profile, boolean fragility, WorkLoad currentLoad) {
        if (range < 2) return new Cargo(Distance.LESS_2, profile, fragility, currentLoad);
        else {
            if (range < 10) return new Cargo(Distance.LESS_10, profile, fragility, currentLoad);
            else {
                if (range < 30) return new Cargo(Distance.LESS_30, profile, fragility, currentLoad);
            }
        }
        return new Cargo(Distance.MORE_30, profile, fragility, currentLoad);
    }

    private static void checkDeliveryPossibilityIfFragile(Cargo cargo) throws FragileException {
        if (cargo.getDistance().equals(Distance.MORE_30) && cargo.isFragile())
            throw new FragileException("We cannot yet deliver the fragile cargo so far");
    }

    private static int checkFinalPriceLimit(float price) {
        if (price < 400) return 400;
        else return Math.round(price);
    }

    private static float priceCalculation(Cargo cargo) throws FragileException {
        checkDeliveryPossibilityIfFragile(cargo);
        if (cargo.isFragile()) {
            return (cargo.getDistance().getDistanceCost()
                    + cargo.getProfile().getProfileCost()
                    + 300)
                    * cargo.getLoadFactor().getLoadFactor();
        } else {
            return (cargo.getDistance().getDistanceCost()
                    + cargo.getProfile().getProfileCost())
                    * cargo.getLoadFactor().getLoadFactor();
        }
    }
}
