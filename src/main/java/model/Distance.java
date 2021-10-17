package model;

import lombok.Getter;

public enum Distance {
    MORE_30 (300),
    LESS_30 (200),
    LESS_10 (100),
    LESS_2 (50);

    @Getter
    private int distanceCost;

    Distance(int distanceCost) {this.distanceCost = distanceCost;}
}
