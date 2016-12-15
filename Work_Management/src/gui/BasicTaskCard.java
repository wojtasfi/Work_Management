package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import model.Person;
import model.Task;

public class BasicTaskCard extends TaskCard {

	public BasicTaskCard(Task task, Person currentUser) {
		super(task, currentUser);
		preparer = new Label(task.getPreparer());
		reviewer = new Label(task.getReviewer());
		deadline = new Label(task.getDeadline().toString());
		reviewed.setSelected(task.getReviewed());
		prc_status = new Label(Integer.toString((int) task.getPrc_status()) + "%");
		orderer = new Label(task.getOrderer());
		preparer.setPadding(insets);
		reviewer.setPadding(insets);
		deadline.setPadding(insets);
		prc_status.setPadding(insets);
		orderer.setPadding(insets);
		slider.setValue(task.getPrc_status());

		// Validation

		if (currentUser.getLogin().equals(task.getReviewer()) && (int) task.getPrc_status() == 100) {
			reviewed.setDisable(false);
		} else {
			reviewed.setDisable(true);
		}

		if (currentUser.getLogin().equals(task.getPreparer())) {
			slider.setDisable(false);
			saveBtn.setVisible(true);
		} else {
			slider.setDisable(true);
			saveBtn.setVisible(false);
		}

		row = 1;
		grid.add(nameLbl, 1, row++);
		grid.add(task_name, 1, row++);

		grid.add(prepLbl, 1, row++);
		grid.add(preparer, 1, row++);

		grid.add(revLbl, 1, row++);
		grid.add(reviewer, 1, row++);

		grid.add(deadlineLbl, 1, row++);
		grid.add(deadline, 1, row++);

		grid.add(hourLbl, 1, row++);
		grid.add(deadline_hour, 1, row++);

		grid.add(minLbl, 1, row++);
		grid.add(minutes, 1, row++);

		grid.add(saveBtn, 1, row++);

		row = 1;

		grid.add(typeLbl, 2, row++);
		grid.add(task_type, 2, row++);

		grid.add(freqLbl, 2, row++);
		grid.add(frequency, 2, row++);

		grid.add(statusLbl, 2, row++);
		grid.add(prc_status, 2, row++);
		grid.add(slider, 2, row++);

		grid.add(ordererLbl, 2, row++);
		grid.add(orderer, 2, row++);

		grid.add(catLbl, 2, row++);
		grid.add(category_1, 2, row++);

		grid.add(reviewedLbl, 2, row++);
		grid.add(reviewed, 2, row++);

		grid.add(commentLbl, 2, row++);
		grid.add(comment, 2, row++);

		saveBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				cardListener.taskStatusUpdated(taskID, (int) slider.getValue());
				cardListener.refreshTable();
			}

		});
		Scene scene = new Scene(grid);
		setScene(scene);
		show();

	}

}
