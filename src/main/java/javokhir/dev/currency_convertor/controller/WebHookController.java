package javokhir.dev.currency_convertor.controller;
import javokhir.dev.currency_convertor.service.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/telegram")
public class WebHookController {

    private final TelegramService telegramService;


    @PostMapping
    public void getUpdates(@RequestBody Update update)  {
        try {
            telegramService.getUpdates(update);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
