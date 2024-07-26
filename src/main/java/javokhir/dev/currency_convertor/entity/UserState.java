package javokhir.dev.currency_convertor.entity;

import javokhir.dev.currency_convertor.component.payload.enums.UserStateNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserState {
    @Id
    @GeneratedValue
    private Long state_id;
    @Enumerated(EnumType.STRING)
    private UserStateNames userState;
    public UserState(UserStateNames userState) {
        this.userState = userState;
    }
}
