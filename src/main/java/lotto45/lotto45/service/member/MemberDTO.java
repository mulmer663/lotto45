package lotto45.lotto45.service.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class MemberDTO {

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String name;
    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\w).{8,}$",
            message = "최소 8자, 숫자, 영문, 특수 문자가 모두 들어가야 합니다.")
    private String password;
    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\w).{8,}$",
            message = "최소 8자, 숫자, 영문, 특수 문자가 모두 들어가야 합니다.")
    private String passwordVerify;
    @NotEmpty
    @Email
    private String email;
}
