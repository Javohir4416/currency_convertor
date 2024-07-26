package javokhir.dev.currency_convertor.feign;
import javokhir.dev.currency_convertor.payload.ResultTelegram;
import javokhir.dev.currency_convertor.constants.RestConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@FeignClient(url = RestConstants.TELEGRAM_BASE_URL,name = "TelegramFeign")
public interface TelegramFeign {

    @PostMapping("/bot"+RestConstants.BOT_TOKEN+"/sendMessage")
    ResultTelegram sendMessageToUser(@RequestBody SendMessage sendMessage);
}
