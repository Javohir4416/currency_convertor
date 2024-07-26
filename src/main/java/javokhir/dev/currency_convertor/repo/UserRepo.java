package javokhir.dev.currency_convertor.repo;
import javokhir.dev.currency_convertor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
