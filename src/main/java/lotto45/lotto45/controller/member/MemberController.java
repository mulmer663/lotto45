package lotto45.lotto45.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.domain.member.Member;
import lotto45.lotto45.exception.SameLoginIdException;
import lotto45.lotto45.service.member.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    String addForm(@ModelAttribute("member") Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult) {

        try {
            if (bindingResult.hasErrors()) {
                // 해당 예외는 아니지만 아이디와 비번 검증 상황 동시에 보여주기 위해 던짐
                throw new SameLoginIdException();
            } else {
                this.memberService.save(member);
            }

        } catch (SameLoginIdException e) {
//            bindingResult.reject("notUniqueLoginId", new Object[]{member.getLoginId()},
//                    "이미 존재하는 아이디 입니다.");
            bindingResult.addError(new FieldError("member", "loginId", member.getLoginId(),
                    false, null, null, "이미 존재하는 아이디 입니다."));
        }

        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "members/addMemberForm";
        }

        return "redirect:/";
    }
}