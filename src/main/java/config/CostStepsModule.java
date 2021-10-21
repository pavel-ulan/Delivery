package config;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import steps.CostSteps;

public class CostStepsModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CostSteps.class).toProvider(CostStepsProvider.class);
    }

}
class CostStepsProvider implements Provider<CostSteps> {

    @Override
    public CostSteps get(){
        return new CostSteps();}
}
