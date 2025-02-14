package javokhir.dev.currency_convertor.service;
import javokhir.dev.currency_convertor.constants.RestConstants;
import javokhir.dev.currency_convertor.entity.User;
import javokhir.dev.currency_convertor.feign.TelegramFeign;
import javokhir.dev.currency_convertor.repo.UserRepo;
import javokhir.dev.currency_convertor.repo.UserStateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static javokhir.dev.currency_convertor.payload.enums.UserStateNames.*;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepo userRepo;
    private final UserStateRepo userStateRepo;
    private final TelegramFeign telegramFeign;

    private final ReplyMarkup replyMarkup;
    private final UserService userService;

    public void throwToAdminCabinet(Update update) {
        User user = userService.getUserFromUpdate(update);
        if (update.getMessage().getText().equals(RestConstants.PASSWORD)) {
            user.setState(userStateRepo.findByUserState(THROW_TO_ADMIN_CABINET));
            user = userRepo.save(user);
            SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(), "Salom admin !!! ");
            sendMessage.setReplyMarkup(replyMarkup.inlineMarkup(user));
            try {
                telegramFeign.sendMessageToUser(sendMessage);
            } catch (Exception e) {
                return;
            }
        } else {
            SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(), "Parol xato ");
            user.setState(userStateRepo.findByUserState(ENTERED_WRONG_PASSWORD_FOR_ADMIN));
            user = userRepo.save(user);
            sendMessage.setReplyMarkup(replyMarkup.inlineMarkup(user));
            try {
                telegramFeign.sendMessageToUser(sendMessage);
            } catch (Exception e) {
                return;
            }
        }
    }

    public void sendMessageToUsers(String text) {
        List<User> userRepoAll = userRepo.findAll();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        for (User user : userRepoAll) {
            sendMessage.setChatId(user.getId().toString());
            try {
                telegramFeign.sendMessageToUser(sendMessage);
            } catch (Exception e) {
                return;
            }
        }
    }

    public void sendMessageToAdmin(Update update) {
        User userFromUpdate = userService.getUserFromUpdate(update);
        userFromUpdate.setState(userStateRepo.findByUserState(SEND_MESSAGE_TO_USERS));
        User save = userRepo.save(userFromUpdate);
        SendMessage sendMessage = new SendMessage(save.getId().toString(),
                "O'z xabaringizni kiriting ✍️: ");
        try {
            telegramFeign.sendMessageToUser(sendMessage);
        } catch (Exception e) {
            return;
        }
    }

    public void checkForAdmin(Update update) {
        User userFromUpdate = userService.getUserFromUpdate(update);
        userFromUpdate.setState(userStateRepo.findByUserState(ENTER_PASSWORD_FOR_ADMIN));
        User save = userRepo.save(userFromUpdate);
        SendMessage sendMessage = new SendMessage(save.getId().toString(),
                "Parolni kiriting ✍️: ");
        try {
            telegramFeign.sendMessageToUser(sendMessage);
        } catch (Exception e) {
            return;
        }
    }

    public void reenterPasswordOrMainMenu(Update update) {
        String data = update.getCallbackQuery().getData();
        if (data.equals("REENTER_PASSWORD")) {
            checkForAdmin(update);
        } else {
            userService.showMenu(update);
        }
    }

    public void getUserList(Update update) {
        User user = userService.getUserFromUpdate(update);
        List<User> users = userRepo.findAll();
        StringBuilder text = new StringBuilder();
        int i = 1;
        for (User user1 : users) {
            text.append(i).append(" . ").append(user1.getFirstName()).append("      ")
                    .append(user1.getLastName()).append("      ").append("@")
                    .append(user1.getUsername()).append("\n");
            i++;
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(user.getId().toString());
        sendMessage.setText(text.toString());
        try {
            telegramFeign.sendMessageToUser(sendMessage);
        } catch (Exception e) {
            return;
        }
    }
}
