package gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import model.Person;
import model.Task;

public class PassTaskCard extends TaskCard {

	private ComboBox<String> rev;
	private ComboBox<String> prep;
	private Button passBtn;
	private Alert alert;

	public PassTaskCard(Task task, Person currentUser, ObservableList<String> preps, ObservableList<String> revs) {
		super(task, currentUser);

		prep = new ComboBox<String>();
		rev = new ComboBox<String>();
		passBtn = new Button("Pass task");

		Label prepLbl2 = new Label("Choose preparer");
		Label revLbl2 = new Label("Choose reviewer");

		prepLbl2.setStyle(lblStyle);
		revLbl2.setStyle(lblStyle);

		prep.setItems(preps);
		rev.setItems(revs);
		
		prepLbl2.setPadding(new Insets(0,25,0,0));
		revLbl2.setPadding(new Insets(0,25,0,0));
		
		prep.setPadding(new Insets(0,25,0,0));
		rev.setPadding(new Insets(0,25,0,0));

		
		prep.setPrefWidth(120);
		rev.setPrefWidth(120);
		row = 1;
		grid.add(nameLbl, 1, row++);
		grid.add(task_name, 1, row++);

		grid.add(prepLbl2, 1, row++);
		grid.add(prep, 1, row++);

		grid.add(revLbl2, 1, row++);
		grid.add(rev, 1, row++);

		if (task.isReview_required()) {
			rev.setDisable(false);
		} else {
			rev.setDisable(true);
		}

		grid.add(deadlineLbl, 1, row++);
		grid.add(deadline, 1, row++);

		grid.add(hourLbl, 1, row++);
		grid.add(deadline_hour, 1, row++);

		grid.add(minLbl, 1, row++);
		grid.add(minutes, 1, row++);

		grid.add(passBtn, 1, row++);

		row = 1;

		grid.add(typeLbl, 2, row++);
		grid.add(task_type, 2, row++);

		grid.add(freqLbl, 2, row++);
		grid.add(frequency, 2, row++);

		grid.add(ordererLbl, 2, row++);
		grid.add(orderer, 2, row++);

		grid.add(catLbl, 2, row++);
		grid.add(category_1, 2, row++);

		grid.add(commentLbl, 2, row++);
		grid.add(comment, 2, row++);

		passBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				String choosenRev = null;
				alert = new Alert(AlertType.INFORMATION);

				if (rev.isDisable())
					choosenRev = null;
				else if (rev.isDisable() == false && rev.getSelectionModel().getSelectedItem() == null) {
					alert.setContentText("Pick reviewer!");
					alert.show();
					return;

				} else {
					choosenRev = rev.getSelectionModel().getSelectedItem().toString();
				}

				if (prep.getSelectionModel().getSelectedItem() == null) {
					alert.setContentText("Pick preparer!");
					alert.show();
					return;
				} else {

					String choosenPrep = prep.getSelectionModel().getSelectedItem().toString();

					cardListener.taskPassed(taskID, choosenPrep, choosenRev);
					cardListener.refreshPassTable();
					alert.setContentText("Task passed!");
					alert.show();
					close();
				}
			}

		});
		Scene scene = new Scene(grid);
		setScene(scene);
		show();
	}

}
