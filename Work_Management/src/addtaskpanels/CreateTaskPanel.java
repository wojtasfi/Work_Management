package addtaskpanels;

import java.time.LocalDate;
import java.time.LocalTime;

import controller.Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import model.Person;

public class CreateTaskPanel extends AddTaskPanel {

	private ComboBox<String> team;
	private CreateTaskListener createTaskListener;
	private ObservableList<String> sups;
	private CheckBox revReq;
	
	public void setCreateTaskListener(CreateTaskListener listener){
		this.createTaskListener = listener;
	}
	
	public CreateTaskPanel(ObservableList<String> categories, Person currentUser, Controller controller) {
		super(categories, currentUser, controller);

		team = new ComboBox<String>();
		revReq = new CheckBox();
		
		grid.add(new Label("Review required"), 1, row+=2);
		grid.add(revReq, 2, row);
		
		row = 0;
		grid.add(new Label("Team"), 1, row);
		grid.add(team, 2, row);

		sups = controller.loadPreparers(currentUser);
		team.setItems(sups);

		addTask.setOnAction(new EventHandler<ActionEvent>() {

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
					} else if (team == null) {
						alert.setContentText("Pick a preparer.");
						alert.show();
					}

					// Validated- adding task
					else {

						String prep = team.getSelectionModel().getSelectedItem();
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
						boolean revRequired = revReq.isSelected();

						AddTaskEvent ev = new AddTaskEvent(name, prep, rev, task_deadline, time, task_duration, cat,
								comm, revRequired);
						
						
						 
						createTaskListener.TaskCreated(ev);

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
