package javokhir.dev.currency_convertor.payload;

import lombok.Data;

@Data
public class ResultTelegram{
	private Result result;
	private boolean ok;
}