package inha.tanple.controller.v1.admin;


import inha.tanple.domain.User;
import inha.tanple.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;


    @Operation(summary = "어드민 홈페이지입니다.")
    @GetMapping
    public String home(Model model) {

        model.addAttribute("myName", "관리자");
        // resources -> template를 추적해 home.html로 찾아감,
        // Thymeleaf 템플릿 엔진이 처리
        return "home";
        // link to resources:templates/home.html
    }

    @Operation(summary = "사용자를 관리하는 페이지입니다. ")
    @GetMapping("/userList")
    public String list(Model model) {

        List<User> userList = userRepository.findAll();

        model.addAttribute("userList", userList);

        // resources -> template를 추적해 home.html로 찾아감,
        // Thymeleaf 템플릿 엔진이 처리
        return "users/userList";
        // link to resources:templates/home.html
    }


}
