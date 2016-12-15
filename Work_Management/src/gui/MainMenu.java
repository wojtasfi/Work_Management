package gui;

import controller.Controller;
import inboxes.AdminInbox;
import inboxes.InboxAnalyst;
import inboxes.InboxManager;
import inboxes.InboxSenior;
import inboxes.InboxSupervisor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

// a = a==0 ? 2 : 3
//jezeli a =0 to zwroic 2 a w przeciwnym razie 3
//python a = 2 if a == 0 else 3

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Person;

public class MainMenu extends Stage {

	private Person currentUser;
	private Alert alert;
	private InboxAnalyst inboxAnalyst;
	private InboxSenior inboxSenior;
	private InboxManager inboxManager;
	private InboxSupervisor inboxSupervisor;
	private Controller controller;
	private AdminInbox adminInbox;
	private Stage parent;

	public MainMenu(Person user, Controller controller) {

		this.currentUser = user;
		this.controller = controller;
		
		parent = this;

		GridPane grid = new GridPane();

		grid.setAlignment(Pos.CENTER);
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(10, 10, 10, 10));
		Scene scene = new Scene(grid, 520, 650);
		setScene(scene);
		setTitle("Main Menu");

		Button adminBtn = new Button("Admin");
		Button inboxBtn = new Button("Inbox");

		adminBtn.setStyle("-fx-font-size: 25pt;");
		inboxBtn.setStyle("-fx-font-size: 25pt;");

		grid.add(adminBtn, 1, 1);
		grid.add(inboxBtn, 0, 1);

		adminBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (currentUser.getJob().equals("Analyst") || currentUser.getJob().equals("Senior")) {
					alert = new Alert(AlertType.WARNING);

					alert.setContentText("Nie posiadasz uprawnień");
					alert.show();
				}else{
					adminInbox = new AdminInbox(currentUser, controller, parent);
					adminInbox.show();
					hide();
				}
			}

		});

		inboxBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				hide();

				if (currentUser.getJob().equals("Analyst")) {
					inboxAnalyst = new InboxAnalyst(currentUser, controller, parent);
					
					inboxAnalyst.show();
				} else if (currentUser.getJob().equals("Senior")) {
					inboxSenior = new InboxSenior(currentUser, controller, parent);
					inboxSenior.show();
				} else if (currentUser.getJob().equals("Manager")) {
					inboxManager = new InboxManager(currentUser, controller, parent);
					inboxManager.show();
				} else if (currentUser.getJob().equals("Supervisor")) {
					inboxSupervisor = new InboxSupervisor(currentUser, controller, parent);
					inboxSupervisor.show();
				}

			}

		});
		// qt tez jest dla javy
	}
}
