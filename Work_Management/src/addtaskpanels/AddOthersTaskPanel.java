package addtaskpanels;

import java.time.LocalDate;
import java.time.LocalTime;

import controller.Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import model.Person;

public class AddOthersTaskPanel extends AddTaskPanel {
	private ComboBox<String> preparer;
	private DeputeListener deputeListener;
	private ObservableList<String> preps;
	
	public void setDeputeListener(DeputeListener listener){
		this.deputeListener = listener;
	}

	public AddOthersTaskPanel(ObservableList<String> categories, Person currentUser, Controller controller) {
		super(categories, currentUser, controller);
		
		preparer = new ComboBox<String>();
		
		row= 0;
		grid.add(new Label("Preparer"), 1, row);
		grid.add(preparer, 2, row);
		
		preps = controller.loadPreparers(currentUser);
		preparer.setItems(preps);
		
		
		
		addTask.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {

				try {
					int h = Integer.parseInt(hour.getText());
					int m = Integer.parseInt(min.getText());

					if (task_name.getText() == null || task_name.getText().trim().isEmpty()) {
						alert.setContentText("Task must have a name.");
						alert.show();

					} else if (category.getSelectionModel().getSelectedItem() == null
							|| category.getSelectionModel().getSelectedItem().trim().isEmpty()) {
						alert.setContentText("Choose category.");
						alert.show();
					} else if (deadline.getValue() == null) {
						alert.setContentText("Choose deadline.");
						alert.show();
					} else if (hour.getText() == null || hour.getText().trim().isEmpty() || min.getText() == null
							|| min.getText().trim().isEmpty()) {
						alert.setContentText("Choose hour deadline.");
						alert.show();
					} else if (h > 23 || h < 0 || m > 59 || m < 0) {
						alert.setContentText("Format of hour deadline is not valid.");
						alert.show();
					} else if (preparer == null){
						alert.setContentText("Pick a preparer.");
						alert.show();
					}

					// Validated- adding task
					else {
						
						String prep = preparer.getSelectionModel().getSelectedItem();
						String rev = currentUser.getLogin();
						String name = task_name.getText();
						String cat = category.getSelectionModel().getSelectedItem();
						int task_duration = (int) minutes.getValue();
						LocalDate localDate = deadline.getValue();
						java.sql.Date myDate = java.sql.Date.valueOf(localDate);
						java.sql.Date task_deadline = myDate;
						int hh = Integer.parseInt(hour.getText());
						int mm = Integer.parseInt(min.getText());
						LocalTime time = LocalTime.of(hh, mm, 0, 0);
						String comm = comment.getText();

						AddTaskEvent ev = new AddTaskEvent(name, prep,rev, task_deadline, time, task_duration, cat, comm);
						deputeListener.TaskDeputed(ev);

						alert.setContentText("Task has been added!");
						alert.show();

					}
				} catch (Exception e) {
					alert.setContentText("Format of hour deadline is not valid.");
					alert.show();
					e.printStackTrace();
					return;
				}
			}
			
		});
	
	}

}
