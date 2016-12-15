package addtaskpanels;

import controller.Controller;
import javafx.collections.ObservableList;
import model.Person;

public class AddMyTaskPanel extends AddTaskPanel {

	public AddMyTaskPanel(ObservableList<String> categories, Person currentUser, Controller controller) {
		super(categories, currentUser, controller);

	
	}

}
