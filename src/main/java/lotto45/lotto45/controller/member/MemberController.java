package lotto45.lotto45.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.domain.member.Member;
import lotto45.lotto45.exception.SameLoginIdException;
import lotto45.lotto45.service.member.MemberService;
import lotto45.lotto45.service.member.MemberDTO;
import lotto45.lotto45.web.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    String addForm(@ModelAttribute("member") MemberDTO member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("member") MemberDTO form, BindingResult bindingResult, HttpServletRequest request) {

        if (!form.getPassword().equals(form.getPasswordVerify())) {
            bindingResult.addError(new FieldError("member", "password", form.getPassword(),
                    false, null, null, "비밀번호와 비밀번호 확인이 일치하지 않습니다."));
        }

        Member member = new Member();
        member.setLoginId(form.getLoginId());
        member.setName(form.getName());
        member.setPassword(form.getPassword());
        member.setEmail(form.getEmail());

        try {
            this.memberService.check(member);
        } catch (SameLoginIdException e) {
//            bindingResult.reject("notUniqueLoginId", new Object[]{member.getLoginId()},
//                    "이미 존재하는 아이디 입니다.");
            bindingResult.addError(new FieldError("member", "loginId", member.getLoginId(),
                    false, null, null, "이미 존재하는 아이디 입니다."));
            return "members/addMemberForm";
        }

        if (bindingResult.hasErrors()) {
//            log.info("bindingResult = {}", bindingResult);
            return "members/addMemberForm";
        }

        this.memberService.save(member);

        HttpSession session = request.getSession();
        if (session.getAttribute(SessionConst.LOGIN_MASTER_2ND_AUTH) != null) {
            return "redirect:/members";
        }

        return "redirect:/";
    }

    @GetMapping
    public String members(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);

        return "members/members";
    }

    @GetMapping("/{memberId}")
    public String memberEditForm(@PathVariable long memberId, Model model) {
        Member member = this.memberService.findById(memberId);
        MemberDTO memberDTO = new MemberDTO(member.getLoginId(),
                member.getName(), member.getPassword(), member.getPassword(), member.getEmail());

        model.addAttribute("member", memberDTO);
        model.addAttribute("sessionData", "master");
        return "members/editMember";
    }

    @PostMapping("/{memberId}")
    public String editMember(@PathVariable long memberId,
                             @Validated @ModelAttribute("member") MemberDTO form,
                             BindingResult bindingResult) {

        if (!form.getPassword().equals(form.getPasswordVerify())) {
            bindingResult.addError(new FieldError("member", "password", form.getPassword(),
                    false, null, null, "비밀번호와 비밀번호 확인이 일치하지 않습니다."));
        }
        // 일단 이전 비밀번호 그대로 유지 가능하게 만들자.
//        if (this.memberService.verifyPreviousPassword(memberId, form.getPassword())) {
//            bindingResult.addError(new FieldError("member", "password", form.getPassword(),
//                    false, null, null, "이전 비밀번호와 일치합니다!"));
//        }
        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "members/editMember";
        }

        this.memberService.update(memberId, form);

        return "redirect:/members";
    }

    @GetMapping("/{memberId}/delete")
    public String deleteMember(@PathVariable long memberId) {
        this.memberService.remove(memberId);

        return "redirect:/members";
    }

    @GetMapping("/edit/{memberId}")
    public String editMemberSelf(@PathVariable long memberId, Model model) {
        Member member = this.memberService.findById(memberId);
        MemberDTO memberDTO = new MemberDTO(member.getLoginId(),
                member.getName(), member.getPassword(), member.getPassword(), member.getEmail());

        model.addAttribute("member", memberDTO);
        model.addAttribute("sessionData", "member");
        return "members/editMember";
    }

    @PostMapping("/edit/{memberId}")
    public String editMemberSelf(@PathVariable long memberId,
                             @Validated @ModelAttribute("member") MemberDTO form,
                             BindingResult bindingResult) {

        if (!form.getPassword().equals(form.getPasswordVerify())) {
            bindingResult.addError(new FieldError("member", "password", form.getPassword(),
                    false, null, null, "비밀번호와 비밀번호 확인이 일치하지 않습니다."));
        }

        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "members/editMember";
        }

        this.memberService.update(memberId, form);

        return "redirect:/";
    }
}