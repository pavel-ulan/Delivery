import com.google.inject.Inject;
import config.CostStepsModule;
import io.qameta.allure.Feature;
import lombok.SneakyThrows;
import model.Profile;
import model.WorkLoad;
import name.falgout.jeffrey.testing.junit.guice.IncludeModule;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import steps.CostSteps;

import java.util.List;
import java.util.stream.Stream;

@IncludeModule({CostStepsModule.class})
public class DeliveryTests {

    static Stream<List> validCargoDataListProvider() {
        return Stream.of(
                List.of(1, Profile.SMALL, true, WorkLoad.STANDARD, 450),
                List.of(5, Profile.SMALL, false, WorkLoad.INCREASED, 400),
                List.of(20, Profile.SMALL, true, WorkLoad.HIGH, 840),
                List.of(35, Profile.SMALL, false, WorkLoad.VERY_HIGH, 640),

                List.of(1, Profile.BIG, true, WorkLoad.INCREASED, 660),
                List.of(5, Profile.BIG, false, WorkLoad.STANDARD, 400),
                List.of(20, Profile.BIG, true, WorkLoad.VERY_HIGH, 1120),
                List.of(35, Profile.BIG, false, WorkLoad.HIGH, 700)
        );
    }

    static Stream<List> invalidCargoDataListProvider() {
        return Stream.of(
                List.of(35, Profile.SMALL, true, WorkLoad.STANDARD)
        );
    }

    static Stream<List> invalidRangeCargoDataListProvider() {
        return Stream.of(
                List.of(-1, Profile.SMALL, true, WorkLoad.STANDARD),
                List.of(0, Profile.SMALL, true, WorkLoad.INCREASED),
                List.of(40001, Profile.SMALL, false, WorkLoad.HIGH),
                List.of(Integer.MAX_VALUE, Profile.BIG, false, WorkLoad.VERY_HIGH)
        );
    }

    @Inject
    CostSteps costSteps;

    @ParameterizedTest
    @MethodSource("validCargoDataListProvider")
    @Feature("Check valid data")
    void deliveryCalcTest(List list) {
        costSteps.assertDeliveryPriceIsCorrect(list);
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("invalidRangeCargoDataListProvider")
    @Feature("Check range exception")
    void rangeExceptionCalcTest(List list) {
        costSteps.assertRangeException(list);
    }

    @ParameterizedTest
    @MethodSource("invalidCargoDataListProvider")
    @Feature("Check fragile exception")
    void fragileExceptionCalcTest(List list) {
        costSteps.assertFragileException(list);
    }
}
