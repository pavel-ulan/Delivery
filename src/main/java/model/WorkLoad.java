package model;

import lombok.Getter;

public enum WorkLoad {
    VERY_HIGH (1.6f),
    HIGH (1.4f),
    INCREASED (1.2f),
    STANDARD (1);

    @Getter
    private float loadFactor;

    WorkLoad(float loadFactor) {this.loadFactor = loadFactor;}

}
