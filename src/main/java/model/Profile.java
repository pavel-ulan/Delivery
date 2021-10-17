package model;

import lombok.Getter;

import static config.Config.PRICES_CONFIG;

public enum Profile {
    BIG (PRICES_CONFIG.cargoSizeBig()),
    SMALL (PRICES_CONFIG.cargoSizeSmall());

    @Getter
    private int profileCost;

    Profile(int profileCoast) {this.profileCost = profileCoast;}

}
