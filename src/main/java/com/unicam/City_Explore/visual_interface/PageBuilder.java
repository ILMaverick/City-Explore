package com.unicam.City_Explore.visual_interface;

import java.util.Scanner;

public interface PageBuilder<T extends Page> {

	public Page buildPage(T toExecute, Scanner scanner);
}
