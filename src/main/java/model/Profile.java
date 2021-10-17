package model;

import lombok.Getter;

public enum Profile {
    BIG (200),
    SMALL (100);

    @Getter
    private int profileCost;

    Profile(int profileCoast) {this.profileCost = profileCoast;}

}
