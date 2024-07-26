package javokhir.dev.currency_convertor.component;
import javokhir.dev.currency_convertor.component.payload.enums.UserStateNames;
import javokhir.dev.currency_convertor.entity.UserState;
import javokhir.dev.currency_convertor.repo.UserStateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserStateRepo stateRepo;
    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            List<UserState> userStates=new ArrayList<>();
            UserStateNames[] values = UserStateNames.values();
            for (UserStateNames value : values) {
                userStates.add(new UserState(value));
            }
            stateRepo.saveAll(userStates);
        }
    }
}
