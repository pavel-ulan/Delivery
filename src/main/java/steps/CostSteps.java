package steps;

import com.google.inject.Inject;
import exeptions.FragileException;
import exeptions.RangeException;
import io.qameta.allure.Step;
import lombok.NoArgsConstructor;
import model.Profile;
import model.WorkLoad;
import util.ICostCalculation;
import util.impl.CostCalculation;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@NoArgsConstructor(onConstructor_ = @Inject)
public class CostSteps {
    private ICostCalculation costCalculation = new CostCalculation();

    @Step("Check delivery price")
    public void assertDeliveryPriceIsCorrect(List list) {
        assertThat(costCalculation.getFinalPrise((Integer) list.get(0), (Profile) list.get(1), (Boolean) list.get(2), (WorkLoad) list.get(3))).isEqualTo(list.get(4));
    }

    @Step("Check range exception")
    public void assertRangeException(List list) {
        assertThatThrownBy(
                () -> costCalculation.getFinalPrise((Integer) list.get(0), (Profile) list.get(1), (Boolean) list.get(2), (WorkLoad) list.get(3)))
                .isInstanceOf(RangeException.class)
                .hasMessage("It seems, that your destination isn't correct");
    }

    @Step("Check fragile exception")
    public void assertFragileException(List list) {
        assertThatThrownBy(
                () -> costCalculation.getFinalPrise((Integer) list.get(0), (Profile) list.get(1), (Boolean) list.get(2), (WorkLoad) list.get(3)))
                .isInstanceOf(FragileException.class)
                .hasMessage("We cannot yet deliver the fragile cargo so far");
    }
}
