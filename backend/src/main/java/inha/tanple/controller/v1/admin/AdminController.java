package inha.tanple.controller.v1.admin;

import inha.tanple.domain.Member;
import inha.tanple.repository.MemberRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Tag(name = "관리자 페이지")
public class AdminController {

    private final MemberRepository memberRepository;

    @Operation(summary = "관리자 페이지 - 홈", description = "관리자 홈페이지입니다.")
    @GetMapping("/admin")
    public String home(Model model) {

        model.addAttribute("myName", "관리자");
        // resources -> template를 추적해 home.html로 찾아감,
        // Thymeleaf 템플릿 엔진이 처리
        return "home";
        // link to resources:templates/home.html
    }

    @Operation(summary = "관리자 페이지 - 멤버 관리", description = "사용자를 관리하는 페이지입니다. ")
    @GetMapping("/admin/members")
    public String memberList(Model model) {

        List<Member> memberList = memberRepository.findAll();

        model.addAttribute("memberList", memberList);

        // resources -> template를 추적해 home.html로 찾아감,
        // Thymeleaf 템플릿 엔진이 처리
        return "members/memberList";
        // link to resources:templates/home.html
    }

    @Operation(summary = "관리자 페이지 - 물품 관리", description = "물품을 관리하는 페이지입니다. ")
    @GetMapping("/admin/products")
    public String productList(Model model) {

        // resources -> template를 추적해 home.html로 찾아감,
        // Thymeleaf 템플릿 엔진이 처리
        return "members/memberList";
        // link to resources:templates/home.html
    }

}
