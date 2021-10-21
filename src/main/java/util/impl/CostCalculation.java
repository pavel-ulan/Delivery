package util.impl;

import exeptions.FragileException;
import exeptions.RangeException;
import model.Cargo;
import model.Distance;
import model.Profile;
import model.WorkLoad;
import util.ICostCalculation;

import static config.Config.PRICES_CONFIG;

public class CostCalculation implements ICostCalculation {

    public int getFinalPrise(int range, Profile profile, boolean fragility, WorkLoad currentLoad) throws FragileException {
        float finalPrice = priceCalculation(loadCargoData(range, profile, fragility, currentLoad));
        return checkFinalPriceLimit(finalPrice);
    }

    private Cargo loadCargoData(int range, Profile profile, boolean fragility, WorkLoad currentLoad) {
        rangeValidation(range);
        if (range < 2) return new Cargo(Distance.LESS_2, profile, fragility, currentLoad);
        else {
            if (range < 10) return new Cargo(Distance.LESS_10, profile, fragility, currentLoad);
            else {
                if (range < 30) return new Cargo(Distance.LESS_30, profile, fragility, currentLoad);
            }
        }
        return new Cargo(Distance.MORE_30, profile, fragility, currentLoad);
    }

    private void checkDeliveryPossibilityIfFragile(Cargo cargo) throws FragileException {
        if (cargo.getDistance().equals(Distance.MORE_30) && cargo.isFragile())
            throw new FragileException("We cannot yet deliver the fragile cargo so far");
    }

    private int checkFinalPriceLimit(float price) {
        if (price < PRICES_CONFIG.minPrice()) return PRICES_CONFIG.minPrice();
        else return Math.round(price);
    }

    private void rangeValidation(int range) {
        if (range<=0||range>40000) throw new RangeException("It seems, that your destination isn't correct");
    }

    private float priceCalculation(Cargo cargo) throws FragileException {
        checkDeliveryPossibilityIfFragile(cargo);
        if (cargo.isFragile()) {
            return (cargo.getDistance().getDistanceCost()
                    + cargo.getProfile().getProfileCost()
                    + PRICES_CONFIG.cargoFragile())
                    * cargo.getLoadFactor().getLoadFactor();
        } else {
            return (cargo.getDistance().getDistanceCost()
                    + cargo.getProfile().getProfileCost())
                    * cargo.getLoadFactor().getLoadFactor();
        }
    }
}
