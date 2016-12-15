package inboxes;

import controller.Controller;
import gui.TaskTable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Person;
import model.Task;



//Manager loguje task dla teamu- wybiera z comboboxa team a nie prepa. Dzieki temu powstanie task bez prepa i bedzie wiadomo ze jest to task
// "to pass" ktory pojawi sie w takiej zakladce u supa ktorego team zostal wybrany

public class InboxSupervisor extends InboxSenior {

	private ObservableList<Task> tasksToPass;
	private Button passBtn;
	private TaskTable passTasksTable;
	
	public InboxSupervisor(Person currentUser, Controller controller, Stage parent) {
		super(currentUser, controller, parent);
		
		tasksToPass = controller.loadTasksToPass(currentUser);
		passBtn = new Button("Tasks to pass");
		passTasksTable = new TaskTable(controller, tasksToPass, currentUser);
		
		passBtn.setPrefWidth(buttonsWidth);
		
		buttonsBox.getChildren().add(passBtn);
		passTasksTable.setDatePickerVisible(false);
		
		
		passBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				pane.getChildren().removeAll();
				
				pane.setCenter(passTasksTable);
			}
			
		});
		  
		
		
		
		
		
	}

}
