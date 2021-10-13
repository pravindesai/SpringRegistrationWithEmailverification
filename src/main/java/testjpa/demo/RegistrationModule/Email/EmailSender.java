package testjpa.demo.RegistrationModule.Email;

public interface EmailSender {
    void sendMail(String to, String email);
}
