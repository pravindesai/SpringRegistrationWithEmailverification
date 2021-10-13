package testjpa.demo.RegistrationModule;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import testjpa.demo.RegistrationModule.Registration.RegistrationRequest;
import testjpa.demo.RegistrationModule.Registration.RegistrationService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;

    @PostMapping
    public String register(@Valid RegistrationRequest request){
        System.out.println("register");
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public ModelAndView confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }


}
