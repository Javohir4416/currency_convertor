package javokhir.dev.currency_convertor.service;
import javokhir.dev.currency_convertor.entity.User;
import javokhir.dev.currency_convertor.repo.UserStateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static javokhir.dev.currency_convertor.payload.enums.UserStateNames.*;


@Service
@RequiredArgsConstructor
public class ReplyMarkup {


    private final UserStateRepo userStateRepo;

    public InlineKeyboardMarkup inlineMarkup(User user) {
        InlineKeyboardMarkup inlineMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton row1Button1 = new InlineKeyboardButton();
        if (user.getState().equals(userStateRepo.findByUserState(THROW_TO_ADMIN_CABINET))) {
            row1Button1.setText("Bot foydalanuvchilariga xabar jo'natish ✍️ ");
            row1Button1.setCallbackData("XABAR");
            row1.add(row1Button1);
            rowList.add(row1);
//            List<InlineKeyboardButton> row2 = new ArrayList<>();
//            InlineKeyboardButton row2Button1 = new InlineKeyboardButton();
//            row2Button1.setText("Bot foydalanuvchilariga reklama jo'natish ");
//            row2Button1.setCallbackData("REKLAMA");
//            row2.add(row2Button1);
//            rowList.add(row2);
            List<InlineKeyboardButton> row3 = new ArrayList<>();
            InlineKeyboardButton row3Button1 = new InlineKeyboardButton();
            row3Button1.setText("Bot foydalanuvchilari ro'yxati ");
            row3Button1.setCallbackData("LIST");
            row3.add(row3Button1);
            rowList.add(row3);
        } else if (user.getState().equals(userStateRepo.findByUserState(SHOW_MENU))) {
            row1Button1.setText("Jahon valyuta kurslarini " +
                    "o'zbek so'midagi qiymatini bilish ");
            row1Button1.setCallbackData("INFORMATION");
            row1.add(row1Button1);
            rowList.add(row1);
            List<InlineKeyboardButton> row2 = new ArrayList<>();
            InlineKeyboardButton row2Button1 = new InlineKeyboardButton();
            row2Button1.setText("Valyutalarni konvertatsiya qilish");
            row2Button1.setCallbackData("CHOOSE_CONVERTOR");
            row2.add(row2Button1);
            rowList.add(row2);
        }
        else if (user.getState().equals(userStateRepo.findByUserState(GET_INFORMATION))||
                 user.getState().equals(userStateRepo.findByUserState(CONVERTOR_TO_UZBEK))){
            row1Button1.setText("\uD83C\uDDFA\uD83C\uDDF8USD\uD83C\uDDFA\uD83C\uDDF8");
            row1Button1.setCallbackData("USD");
            InlineKeyboardButton row1Button2;
            row1Button2 = new InlineKeyboardButton();
            row1Button2.setText("\uD83C\uDDF7\uD83C\uDDFARUB\uD83C\uDDF7\uD83C\uDDFA");
            row1Button2.setCallbackData("RUB");
            InlineKeyboardButton row1Button3=new InlineKeyboardButton();
            row1Button3.setText("\uD83C\uDDEA\uD83C\uDDFAEUR\uD83C\uDDEA\uD83C\uDDFA");
            row1Button3.setCallbackData("EUR");
            List<InlineKeyboardButton> row2 = new ArrayList<>();
            InlineKeyboardButton row2Button1 = new InlineKeyboardButton();
            row2Button1.setText("Qolgan valyutalarni ro'yxati");
            row2Button1.setCallbackData("OTHERS");
            List<InlineKeyboardButton> row3 = new ArrayList<>();
            InlineKeyboardButton row3Button1 = new InlineKeyboardButton();
            row3Button1.setText("Back");
            if (user.getState().equals(userStateRepo.findByUserState(GET_INFORMATION))) {
                row3Button1.setCallbackData("BACK_TO_MENU");
            } else {
                row3Button1.setCallbackData("BACK_TO_CONVERTOR");
            }
            row1.add(row1Button1);
            row1.add(row1Button2);
            row1.add(row1Button3);
            rowList.add(row1);
            row2.add(row2Button1);
            rowList.add(row2);
            row3.add(row3Button1);
            rowList.add(row3);
        } else if (user.getState().equals(userStateRepo.findByUserState(ENTERED_WRONG_PASSWORD_FOR_ADMIN))) {
            row1Button1.setText("Qayta kod kiritish");
            row1Button1.setCallbackData("REENTER_PASSWORD");
            List<InlineKeyboardButton> row2 = new ArrayList<>();
            InlineKeyboardButton row2Button1 = new InlineKeyboardButton();
            row2Button1.setText("Bosh menu");
            row2Button1.setCallbackData("MAIN_MENU");
            row1.add(row1Button1);
            row2.add(row2Button1);
            rowList.add(row1);
            rowList.add(row2);

        }
        else if (user.getState().equals(userStateRepo.findByUserState(CHOOSE_CONVERTOR))) {
            row1Button1.setText("Jahon valyutalaridan o'zbek so'miga konvertatsiya");
            row1Button1.setCallbackData("CONVERTOR_TO_UZBEK");
            List<InlineKeyboardButton> row2 = new ArrayList<>();
            InlineKeyboardButton row2Button1 = new InlineKeyboardButton();
            row2Button1.setText("O'zbek so'midan jahon valyutalariga konvertatsiya");
            row2Button1.setCallbackData("CONVERTOR_FROM_UZBEK");
            List<InlineKeyboardButton> row3 = new ArrayList<>();
            InlineKeyboardButton row3Button1 = new InlineKeyboardButton();
            row3Button1.setText("Back");
            row3Button1.setCallbackData("BACK_TO_MENU");
            row1.add(row1Button1);
            row2.add(row2Button1);
            row3.add(row3Button1);
            rowList.add(row1);
            rowList.add(row2);
            rowList.add(row3);
        }
        else if (user.getState().equals(userStateRepo.findByUserState(CONVERTOR_FROM_UZBEK))) {
            row1Button1.setText("Jahon valyutalari qisqartmalari");
            row1Button1.setCallbackData("LIST_OF_CURRENCIES");
            List<InlineKeyboardButton> row2 = new ArrayList<>();
            InlineKeyboardButton row2Button1 = new InlineKeyboardButton();
            row2Button1.setText("Back");
            row2Button1.setCallbackData("BACK_TO_CONVERTOR");
            row1.add(row1Button1);
            row2.add(row2Button1);
            rowList.add(row1);
            rowList.add(row2);
        }
        inlineMarkup.setKeyboard(rowList);
        return inlineMarkup;
    }
}
