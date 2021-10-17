package model;

import lombok.Getter;

import static config.Config.PRICES_CONFIG;

public enum WorkLoad {
    VERY_HIGH (PRICES_CONFIG.veryHighWorkload()),
    HIGH (PRICES_CONFIG.highWorkload()),
    INCREASED (PRICES_CONFIG.increasedWorkload()),
    STANDARD (PRICES_CONFIG.standardWorkload());

    @Getter
    private float loadFactor;

    WorkLoad(float loadFactor) {this.loadFactor = loadFactor;}

}
