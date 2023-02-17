package lotto45.lotto45.domain.member;

import lombok.*;
import lotto45.lotto45.domain.lotto.Lotto;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String name;
    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\w).{8,}$",
            message = "최소 8자, 숫자, 영문, 특수 문자가 모두 들어가야 합니다.")
    private String password;
    @NotEmpty
    @Email
    private String email;
    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Lotto> lottos = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Member member = (Member) o;
        return id != null && Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
