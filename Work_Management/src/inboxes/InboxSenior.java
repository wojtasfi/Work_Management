package inboxes;

import addtaskpanels.AddOthersTaskPanel;
import addtaskpanels.AddTaskEvent;
import addtaskpanels.DeputeListener;
import controller.Controller;
import gui.TaskTable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Person;
import model.Task;
import model.Task;

public class InboxSenior extends Inbox {

	private Button deputesTaskButton;
	private Button toReviewButton;
	private AddOthersTaskPanel deputeTask;
	private ObservableList<String> categoriesAll;
	private ObservableList<Task> tasksToReview;
	private TaskTable toReviewTable;

	public InboxSenior(Person currentUser, Controller controller, Stage parent) {
		super(currentUser, controller, parent);

		
		
		tasksToReview = controller.getTasksToReview();
		categoriesAll = controller.loadAllCategories();
		deputesTaskButton = new Button("Depute task");
		toReviewButton = new Button("To review");
		deputeTask = new AddOthersTaskPanel(categories, currentUser, controller);
		toReviewTable = new TaskTable(controller, tasksToReview, currentUser);

		toReviewTable.loadTasksToReview();
		
		deputesTaskButton.setPrefWidth(buttonsWidth);
		toReviewButton.setPrefWidth(buttonsWidth);

		buttonsBox.getChildren().add(deputesTaskButton);
		buttonsBox.getChildren().add(toReviewButton);
		
		
		
		//Listeners
		toReviewButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				pane.getChildren().removeAll();
				pane.setCenter(toReviewTable);
			}
			
		});
		
		deputeTask.setDeputeListener(new DeputeListener() {
			@Override
			public void TaskDeputed(AddTaskEvent ev) {
				controller.deputeTask(ev);
			}
		});
		
		deputesTaskButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				pane.getChildren().removeAll();
				pane.setCenter(deputeTask);

			}

		});
	}
}
