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
import model.AddFixedTaskEvent;
import model.FixedTask;
import model.Person;

public class AddFixedTaskPanel extends AddMyTaskPanel {

	private ComboBox<String> frequency;
	private ComboBox<String> team;
	private ObservableList<String> frequencies;
	private ObservableList<String> teams;
	private CreateFixedTaskListener listener;
	private CheckBox reviewReq;

	public AddFixedTaskPanel(ObservableList<String> categories, Person currentUser, Controller controller) {
		super(categories, currentUser, controller);

		reviewReq = new CheckBox();
		frequency = new ComboBox<String>();
		team = new ComboBox<String>();
		frequencies = controller.loadFrequencies();
		teams = controller.loadTeams();

		frequency.setItems(frequencies);
		team.setItems(teams);

		row -= 11;
		grid.add(new Label("Frequency"), 1, row);
		grid.add(frequency, 2, row);

		row += 2;
		grid.add(new Label("Team"), 1, row);
		grid.add(team, 2, row);

		row += 2;
		grid.add(new Label("Review required"), 1, row);
		grid.add(reviewReq, 2, row);

		deadline.setVisible(false);
		

		addTask.setOnAction(new EventHandler<ActionEvent>() {

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
					} else if (hour.getText() == null || hour.getText().trim().isEmpty() || min.getText() == null
							|| min.getText().trim().isEmpty()) {
						alert.setContentText("Choose hour deadline.");
						alert.show();
					} else if (h > 23 || h < 0 || m > 59 || m < 0) {
						alert.setContentText("Format of hour deadline is not valid.");
						alert.show();
					} else {

						// Validated- adding task

						String name = task_name.getText();
						String myTeam = team.getSelectionModel().getSelectedItem();
						String freq = frequency.getSelectionModel().getSelectedItem();
						String cat = category.getSelectionModel().getSelectedItem();
						int task_duration = (int) minutes.getValue();
						String comm = comment.getText();
						boolean revReq = reviewReq.isSelected();
						int hh = Integer.parseInt(hour.getText());
						int mm = Integer.parseInt(min.getText());
						LocalTime time = LocalTime.of(hh, mm);

						AddFixedTaskEvent fixed = new AddFixedTaskEvent(name, task_duration, "Fixed", freq, comm,
								revReq, cat, myTeam, time);
						listener.CreateFixedTask(fixed);

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

	public void setListener(CreateFixedTaskListener listener) {
		this.listener = listener;
	}

}
