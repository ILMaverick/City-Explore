package visual_interface;

import org.springframework.stereotype.Component;

@Component
public abstract class Page {

	private String title;
	
	public Page() {
		
	}

	public Page(String title) {
		this.title = title;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
