package model;

import lombok.Getter;

import static config.Config.PRICES_CONFIG;

public enum Distance {
    MORE_30 (PRICES_CONFIG.distanceMore30()),
    LESS_30 (PRICES_CONFIG.distanceLess30()),
    LESS_10 (PRICES_CONFIG.distanceLess10()),
    LESS_2 (PRICES_CONFIG.distanceLess2());

    @Getter
    private int distanceCost;

    Distance(int distanceCost) {this.distanceCost = distanceCost;}
}
