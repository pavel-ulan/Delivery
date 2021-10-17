package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:prices.properties")
public interface PricesConfig extends Config {

    @Key("distance.less.2")
    int distanceLess2();

    @Key("distance.less.10")
    int distanceLess10();

    @Key("distance.less.30")
    int distanceLess30();

    @Key("distance.more.30")
    int distanceMore30();

    @Key("small.size")
    int cargoSizeSmall();

    @Key("big.size")
    int cargoSizeBig();

    @Key("fragile.cargo")
    int cargoFragile();

    @Key("not.fragile.cargo")
    int cargoNotFragile();

    @Key("very.high.workload")
    float veryHighWorkload();

    @Key("high.workload")
    float highWorkload();

    @Key("increased.workload")
    float increasedWorkload();

    @Key("standard.workload")
    float standardWorkload();

    @Key("min.cargo.price")
    int minPrice();
}
