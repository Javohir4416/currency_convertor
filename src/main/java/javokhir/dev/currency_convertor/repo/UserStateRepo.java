package javokhir.dev.currency_convertor.repo;
import javokhir.dev.currency_convertor.payload.enums.UserStateNames;
import javokhir.dev.currency_convertor.entity.UserState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStateRepo extends JpaRepository<UserState,Long> {
    UserState findByUserState(UserStateNames userState);
}
