package javokhir.dev.currency_convertor.controller;

import java.util.List;

public class Category{
	private List<CategoryItem> category;

	public List<CategoryItem> getCategory(){
		return category;
	}

	public void setCategory(List<CategoryItem> category) {
		this.category = category;
	}
}