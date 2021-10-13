package testjpa.demo.RegistrationModule.Registration.Token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import testjpa.demo.RegistrationModule.AppUser.AppUser;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter @NoArgsConstructor
public class ConfirmationToken {

    @SequenceGenerator(name = "conformation_token_sequence",
                        sequenceName = "conformation_token_sequence", allocationSize = 1)
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conformation_token_sequence")
    private long id;

    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    private LocalDateTime confirmedAt;

    @ManyToOne @JoinColumn(nullable = false)
    private AppUser appUser;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt, LocalDateTime confirmedAt, AppUser appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.confirmedAt = confirmedAt;
        this.appUser = appUser;
    }


}

