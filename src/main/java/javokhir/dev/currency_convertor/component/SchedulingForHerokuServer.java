package javokhir.dev.currency_convertor.component;
import javokhir.dev.currency_convertor.constants.RestConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;

@Slf4j
public class SchedulingForHerokuServer {

    @Scheduled(fixedRateString = "1000000") //20 minutes
    public void PingMe(){
        try {
            URL url = new URL("https://127.0.0.1:8080/ping");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            log.info("Ping{}, OK.response code{}", url.getHost(), connection.getResponseCode());
        }   catch (IOException e) {
            RestConstants.logger.log(Level.SEVERE,"An error occurred "+e.getMessage(),e);
        }
    }
}
