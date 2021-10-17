package config;

import org.aeonbits.owner.ConfigFactory;

/**
 * Stores access to configs.
 */
public class Config {

    public static final PricesConfig PRICES_CONFIG = ConfigFactory.create(PricesConfig.class);


}
