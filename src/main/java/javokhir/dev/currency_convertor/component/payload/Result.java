package javokhir.dev.currency_convertor.component.payload;

import lombok.Data;

@Data
public class Result{
	private int date;
	private Chat chat;
	private int messageId;
	private From from;
	private String text;
}