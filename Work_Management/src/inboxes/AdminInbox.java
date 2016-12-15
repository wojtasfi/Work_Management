package inboxes;

import addtaskpanels.AddFixedTaskPanel;
import addtaskpanels.CreateFixedTaskListener;
import controller.Controller;
import gui.TaskTable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.AddFixedTaskEvent;
import model.Task;
import model.FixedTask;
import model.Person;
import model.Task;

public class AdminInbox extends Inbox {
	private AddFixedTaskPanel addFixedTaskPanel;
	private TaskTable fixedTasksTable;
private ObservableList<Task> fixedTasks;
	
	public AdminInbox(Person currentUser, Controller controller, Stage parent) {
		super(currentUser, controller, parent);

		addFixedTaskPanel = new AddFixedTaskPanel(controller.loadAllCategories(), currentUser, controller);
		
		fixedTasks = controller.loadFixedTasks();
		fixedTasksTable = new TaskTable(controller, fixedTasks, currentUser);

		addTaskBtn.setText("Add Fixed Task");
		tasksBtn.setText("Fixed Tasks");
		
		addTaskBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pane.getChildren().removeAll();
				pane.setCenter(addFixedTaskPanel);
			}

		});
		
		tasksBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {

				pane.getChildren().removeAll();
				fixedTasksTable.refreshTable();
				pane.setCenter(fixedTasksTable);
			}
			
		});
		
		
		
		addFixedTaskPanel.setListener(new CreateFixedTaskListener(){

			@Override
			public void CreateFixedTask(AddFixedTaskEvent ev) {
				controller.addFixedTask(ev);
			}
			
		});
		
		pane.getChildren().removeAll();
		pane.setCenter(fixedTasksTable);
		
		fixedTasksTable.setDatePickerVisible(false);		
	}

}
