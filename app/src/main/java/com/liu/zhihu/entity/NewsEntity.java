package com.liu.zhihu.entity;

import java.util.List;

/**
 * Created by Ming on 2016/3/20.
 */
public class NewsEntity extends BaseEntity {
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Story> getStories() {
		return stories;
	}

	public void setStories(List<Story> stories) {
		this.stories = stories;
	}

	public List<TopStory> getTop_stories() {
		return top_stories;
	}

	public void setTop_stories(List<TopStory> top_stories) {
		this.top_stories = top_stories;
	}

	private String date;
	private List<Story> stories;
	private List<TopStory> top_stories;

	public class TopStory {
		public String getGa_prefix() {
			return ga_prefix;
		}

		public void setGa_prefix(String ga_prefix) {
			this.ga_prefix = ga_prefix;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		private String image;
		private int type;
		private int id;
		private String ga_prefix;
		private String title;
	}

	public class Story {
		public String getGa_prefix() {
			return ga_prefix;
		}

		public void setGa_prefix(String ga_prefix) {
			this.ga_prefix = ga_prefix;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public List<String> getImages() {
			return images;
		}

		public void setImages(List<String> images) {
			this.images = images;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		private List<String> images;
		private int type;
		private int id;
		private String ga_prefix;
		private String title;
	}
}
