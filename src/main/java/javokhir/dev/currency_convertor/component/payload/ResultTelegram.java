package javokhir.dev.currency_convertor.component.payload;

import lombok.Data;

@Data
public class ResultTelegram{
	private Result result;
	private boolean ok;
}