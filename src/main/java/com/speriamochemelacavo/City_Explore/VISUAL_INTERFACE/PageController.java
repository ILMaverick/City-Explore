package VISUAL_INTERFACE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageController {

	@Autowired
	private PageDisplay viewer;
	
	private Page pointerPage = new MainPage();
	
	public PageController() {
		
	}

	public void start() {
		this.show();
	}
	
	private void show() {
		this.viewer.display(pointerPage);
	}
	
	public void next(Page toShow) {
		this.pointerPage = toShow;
	}
	
	private void close() {
		
	}
	
}
