package unit;

import exeptions.FragileException;
import lombok.SneakyThrows;
import model.Profile;
import model.WorkLoad;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static util.CostCalculation.getFinalPrise;

class CostCalculationTest {

    static Stream<List> invalidCargoDataListProvider() {
        return Stream.of(
                List.of(35, Profile.SMALL, true, WorkLoad.STANDARD),
                List.of(35, Profile.SMALL, true, WorkLoad.INCREASED),
                List.of(35, Profile.SMALL, true, WorkLoad.HIGH),
                List.of(35, Profile.SMALL, true, WorkLoad.VERY_HIGH),
                List.of(35, Profile.BIG, true, WorkLoad.STANDARD),
                List.of(35, Profile.BIG, true, WorkLoad.INCREASED),
                List.of(35, Profile.BIG, true, WorkLoad.HIGH),
                List.of(35, Profile.BIG, true, WorkLoad.VERY_HIGH)
        );
    }

    static Stream<List> validCargoDataListProvider() {
        return Stream.of(
                List.of(1, Profile.SMALL, false, WorkLoad.STANDARD, 400),
                List.of(5, Profile.SMALL, false, WorkLoad.STANDARD, 400),
                List.of(20, Profile.SMALL, false, WorkLoad.STANDARD, 400),
                List.of(35, Profile.SMALL, false, WorkLoad.STANDARD, 400),

                List.of(1, Profile.BIG, false, WorkLoad.STANDARD, 400),
                List.of(5, Profile.BIG, false, WorkLoad.STANDARD, 400),
                List.of(20, Profile.BIG, false, WorkLoad.STANDARD, 400),
                List.of(35, Profile.BIG, false, WorkLoad.STANDARD, 500),

                List.of(1, Profile.SMALL, true, WorkLoad.STANDARD, 450),
                List.of(5, Profile.SMALL, true, WorkLoad.STANDARD, 500),
                List.of(20, Profile.SMALL, true, WorkLoad.STANDARD, 600),

                List.of(1, Profile.BIG, true, WorkLoad.STANDARD, 550),
                List.of(5, Profile.BIG, true, WorkLoad.STANDARD, 600),
                List.of(20, Profile.BIG, true, WorkLoad.STANDARD, 700),

                List.of(1, Profile.SMALL, false, WorkLoad.INCREASED, 400),
                List.of(5, Profile.SMALL, false, WorkLoad.INCREASED, 400),
                List.of(20, Profile.SMALL, false, WorkLoad.INCREASED, 400),
                List.of(35, Profile.SMALL, false, WorkLoad.INCREASED, 480),

                List.of(1, Profile.BIG, false, WorkLoad.INCREASED, 400),
                List.of(5, Profile.BIG, false, WorkLoad.INCREASED, 400),
                List.of(20, Profile.BIG, false, WorkLoad.INCREASED, 480),
                List.of(35, Profile.BIG, false, WorkLoad.INCREASED, 600),

                List.of(1, Profile.SMALL, false, WorkLoad.HIGH, 400),
                List.of(5, Profile.SMALL, false, WorkLoad.HIGH, 400),
                List.of(20, Profile.SMALL, false, WorkLoad.HIGH, 420),
                List.of(35, Profile.SMALL, false, WorkLoad.HIGH, 560),

                List.of(1, Profile.BIG, false, WorkLoad.HIGH, 400),
                List.of(5, Profile.BIG, false, WorkLoad.HIGH, 420),
                List.of(20, Profile.BIG, false, WorkLoad.HIGH, 560),
                List.of(35, Profile.BIG, false, WorkLoad.HIGH, 700),

                List.of(1, Profile.SMALL, false, WorkLoad.VERY_HIGH, 400),
                List.of(5, Profile.SMALL, false, WorkLoad.VERY_HIGH, 400),
                List.of(20, Profile.SMALL, false, WorkLoad.VERY_HIGH, 480),
                List.of(35, Profile.SMALL, false, WorkLoad.VERY_HIGH, 640),

                List.of(1, Profile.BIG, false, WorkLoad.VERY_HIGH, 400),
                List.of(5, Profile.BIG, false, WorkLoad.VERY_HIGH, 480),
                List.of(20, Profile.BIG, false, WorkLoad.VERY_HIGH, 640),
                List.of(35, Profile.BIG, false, WorkLoad.VERY_HIGH, 800),

                List.of(1, Profile.SMALL, true, WorkLoad.INCREASED, 540),
                List.of(5, Profile.SMALL, true, WorkLoad.INCREASED, 600),
                List.of(20, Profile.SMALL, true, WorkLoad.INCREASED, 720),

                List.of(1, Profile.BIG, true, WorkLoad.INCREASED, 660),
                List.of(5, Profile.BIG, true, WorkLoad.INCREASED, 720),
                List.of(20, Profile.BIG, true, WorkLoad.INCREASED, 840),

                List.of(1, Profile.SMALL, true, WorkLoad.HIGH, 630),
                List.of(5, Profile.SMALL, true, WorkLoad.HIGH, 700),
                List.of(20, Profile.SMALL, true, WorkLoad.HIGH, 840),

                List.of(1, Profile.BIG, true, WorkLoad.HIGH, 770),
                List.of(5, Profile.BIG, true, WorkLoad.HIGH, 840),
                List.of(20, Profile.BIG, true, WorkLoad.HIGH, 980),

                List.of(1, Profile.SMALL, true, WorkLoad.VERY_HIGH, 720),
                List.of(5, Profile.SMALL, true, WorkLoad.VERY_HIGH, 800),
                List.of(20, Profile.SMALL, true, WorkLoad.VERY_HIGH, 960),

                List.of(1, Profile.BIG, true, WorkLoad.VERY_HIGH, 880),
                List.of(5, Profile.BIG, true, WorkLoad.VERY_HIGH, 960),
                List.of(20, Profile.BIG, true, WorkLoad.VERY_HIGH, 1120)
        );
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("validCargoDataListProvider")
    void deliveryCalcTest(List list) {
        assertThat(getFinalPrise((Integer) list.get(0), (Profile) list.get(1), (Boolean) list.get(2), (WorkLoad) list.get(3))).isEqualTo(list.get(4));
    }

    @ParameterizedTest
    @MethodSource("invalidCargoDataListProvider")
    void exceptionCalcTest(List list) {
        assertThatThrownBy(
                () -> getFinalPrise((Integer) list.get(0), (Profile) list.get(1), (Boolean) list.get(2), (WorkLoad) list.get(3)))
                .isInstanceOf(FragileException.class)
                .hasMessage("We cannot yet deliver the fragile cargo so far");
    }
}