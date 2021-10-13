package testjpa.demo.RegistrationModule.AppUser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import testjpa.demo.RegistrationModule.Registration.Token.ConfirmationToken;
import testjpa.demo.RegistrationModule.Registration.Token.ConfirmationTokenService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service @AllArgsConstructor
public class AppUserService implements UserDetailsService {


    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() ->new UsernameNotFoundException("Username email not found"));

    }

    public String signUpUser(AppUser appUser){
        boolean user_Exists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if(user_Exists){

            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            AppUser tempAppUser = appUserRepository.findByEmail(appUser.getEmail()).get();
            boolean is_Email_Confirmed = tempAppUser.isEnabled();
            if (!is_Email_Confirmed){

                confirmationTokenService.deleteTokenForUserId(tempAppUser.getId());

                appUserRepository.delete(tempAppUser);
                appUserRepository.save(appUser);

                return sendConfirmationLink(appUser);
            }

            System.out.println("**Email already taken**");
            return "";
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);

        //TODO send confirmation OTP
        return sendConfirmationLink(appUser);

    }

    private String sendConfirmationLink(AppUser appUser) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                null,
                appUser);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
