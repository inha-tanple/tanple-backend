package inha.tanple.controller.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarbonNeutralController {

    @GetMapping("/CarbonNeutral")
    public String showCarbonNeutralPage() {
        return "CarbonNeutral"; // templates 폴더에 있는 carbon-neutral.html을 반환합니다.
    }
}
