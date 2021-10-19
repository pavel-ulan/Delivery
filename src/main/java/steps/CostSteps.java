package steps;

import exeptions.FragileException;
import exeptions.RangeException;
import io.qameta.allure.Step;
import model.Profile;
import model.WorkLoad;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static util.CostCalculation.getFinalPrise;

public class CostSteps {

    @Step("Check delivery price")
    public static void assertDeliveryPriceIsCorrect(List list) {
        assertThat(getFinalPrise((Integer) list.get(0), (Profile) list.get(1), (Boolean) list.get(2), (WorkLoad) list.get(3))).isEqualTo(list.get(4));
    }

    @Step("Check range exception")
    public static void assertRangeException(List list) {
        assertThatThrownBy(
                () -> getFinalPrise((Integer) list.get(0), (Profile) list.get(1), (Boolean) list.get(2), (WorkLoad) list.get(3)))
                .isInstanceOf(RangeException.class)
                .hasMessage("It seems, that your destination isn't correct");
    }

    @Step("Check fragile exception")
    public static void assertFragileException(List list) {
        assertThatThrownBy(
                () -> getFinalPrise((Integer) list.get(0), (Profile) list.get(1), (Boolean) list.get(2), (WorkLoad) list.get(3)))
                .isInstanceOf(FragileException.class)
                .hasMessage("We cannot yet deliver the fragile cargo so far");
    }
}
