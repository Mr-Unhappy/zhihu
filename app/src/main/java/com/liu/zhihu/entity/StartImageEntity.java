package com.liu.zhihu.entity;

/**
 * Created by Ming on 2016/3/20.
 */
public class StartImageEntity extends BaseEntity{
	public String getImg() {
		return img;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setImg(String img) {
		this.img = img;
	}

	private String text;
	private String img;

}
