package inboxes;

import addtaskpanels.AddTaskEvent;
import addtaskpanels.CreateTaskListener;
import addtaskpanels.CreateTaskPanel;
import controller.Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Person;

public class InboxManager extends Inbox {

	private Button createTask;
	private CreateTaskPanel createPanel;
	private ObservableList<String> categories;

	public InboxManager(Person currentUser, Controller controller,Stage parent) {
		super(currentUser, controller, parent);
		categories = controller.loadAllCategories();
		buttonsBox.getChildren().removeAll();

		createTask = new Button("Create Task");
		createTask.setPrefWidth(buttonsWidth);
		
		createPanel = new CreateTaskPanel(categories, currentUser, controller);

		buttonsBox.getChildren().add(createTask);

		createPanel.setCreateTaskListener(new CreateTaskListener() {
			public void TaskCreated(AddTaskEvent ev) {
				controller.createTask(ev);
			}

		});

		createTask.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				pane.getChildren().removeAll();
				pane.setCenter(createPanel);
			}

		});

	}

}
