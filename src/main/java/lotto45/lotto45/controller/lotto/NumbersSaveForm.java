package lotto45.lotto45.controller.lotto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class NumbersSaveForm {

    @NotNull
    @Range(min = 1, max = 45)
    private Integer num1;
    @NotNull
    @Range(min = 1, max = 45)
    private Integer num2;
    @NotNull
    @Range(min = 1, max = 45)
    private Integer num3;
    @NotNull
    @Range(min = 1, max = 45)
    private Integer num4;
    @NotNull
    @Range(min = 1, max = 45)
    private Integer num5;
    @NotNull
    @Range(min = 1, max = 45)
    private Integer num6;
}
