package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;

public class View {

	private TextField tfAddress; // tf = text field
	private TextField tfTypeBallotBox;

	private TextField tfNameVoterText;
	private TextField tfBirthDateVoter;
	private TextField tfIDVoterText;
	private TextField tfSickVoter;
	private TextField tfKalfiNum;

	private TextField tfPartyName;
	private TextField tfPartyDate;
	private TextField tfPartyFaction;

	private TextField tfNameCandidate;
	private TextField tfBirthDateCandidate;
	private TextField tfIDCandidateText;
	private TextField tfKalfiNumCandidate;

	private TextField tfSickCandidate;
	private TextField tfPartyNum;

	private Button btnSubmitBallotBox;
	private Button btnSubmitVoter;
	private Button btnSubmitCandidate;
	private Button btnSubmitParty;
	private Button btnShowBallotBox;
	private Button btnShowVoters;
	private Button btnShowParties;
	private Button btnShowCandidates;
	private Button btnShowResualts;
	private Button btnStartElections;

	private TextField tfPrimeries;
	private ChoiceBox<String> choiceBox;

	public View(Stage primaryStage) {

		// # ADD Ballot Box

		Text BallotBoxText = new Text("Add Ballot-Box: ");
		Text addressText = new Text("Address: ");
		tfAddress = new TextField();
		Text typeBallotBoxText = new Text("Type BallotBox: ");
		choiceBox = new ChoiceBox<String>();
		choiceBox.getItems().add("1. Corona");
		choiceBox.getItems().add("2. Army");
		choiceBox.getItems().add("3. SickSoldier");
		choiceBox.getItems().add("4. Regular");
		btnSubmitBallotBox = new Button("Submit");

		HBox hb = new HBox();
		hb.getChildren().addAll(BallotBoxText, addressText, tfAddress);
		hb.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(addressText, new Insets(0, 0, 0, 10));
		HBox.setMargin(tfAddress, new Insets(0, 0, 0, 10));

		HBox hb1 = new HBox();
		hb1.getChildren().addAll(typeBallotBoxText, choiceBox);
		hb1.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(typeBallotBoxText, new Insets(0, 0, 0, 10));
		HBox.setMargin(choiceBox, new Insets(0, 0, 0, 10));

		// # ADD Voter

		Text VoterText = new Text("Add Voter ");
		Text NameVoter = new Text("Name: ");
		tfNameVoterText = new TextField();
		Text IDVoter = new Text("ID: ");
		tfIDVoterText = new TextField();
		Text BirthDateText = new Text("Birth Date(Days/months/years): ");
		tfBirthDateVoter = new TextField();
		Text KalfiNumText = new Text("Which Ballot Box u wanna vote in ? (insert it's number):");
		tfKalfiNum = new TextField();
		btnSubmitVoter = new Button("Submit");

		HBox hb2 = new HBox();
		hb2.getChildren().addAll(VoterText, NameVoter, tfNameVoterText);
		hb2.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(NameVoter, new Insets(0, 0, 0, 10));
		HBox.setMargin(tfNameVoterText, new Insets(0, 0, 0, 10));

		HBox hb3 = new HBox();
		hb3.getChildren().addAll(IDVoter, tfIDVoterText);
		hb3.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(IDVoter, new Insets(0, 0, 0, 10));
		HBox.setMargin(tfIDVoterText, new Insets(0, 0, 0, 10));

		HBox hb4 = new HBox();
		hb4.getChildren().addAll(BirthDateText, tfBirthDateVoter);
		hb4.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(BirthDateText, new Insets(0, 0, 0, 10));
		HBox.setMargin(tfBirthDateVoter, new Insets(0, 0, 0, 10));

		HBox hb5 = new HBox();
		hb5.getChildren().addAll(KalfiNumText, tfKalfiNum);
		hb5.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(KalfiNumText, new Insets(0, 0, 0, 10));
		HBox.setMargin(tfKalfiNum, new Insets(0, 0, 0, 10));

		// # ADD Party

		Text PartyText = new Text("Add Party ");
		Text PartyName = new Text("Name: ");
		tfPartyName = new TextField();
		Text PartyDate = new Text("Date(Days/months/years): ");
		tfPartyDate = new TextField();
		Text PartyFaction = new Text("Faction(LEFT,RIGHT,CENTER): ");
		tfPartyFaction = new TextField();
		btnSubmitParty = new Button("Submit");

		HBox hb6 = new HBox();
		hb6.getChildren().addAll(PartyText, PartyName, tfPartyName);
		hb6.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(PartyName, new Insets(0, 0, 0, 10));
		HBox.setMargin(tfPartyName, new Insets(0, 0, 0, 10));

		HBox hb7 = new HBox();
		hb7.getChildren().addAll(PartyDate, tfPartyDate);
		hb7.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(PartyDate, new Insets(0, 0, 0, 10));
		HBox.setMargin(tfPartyDate, new Insets(0, 0, 0, 10));

		HBox hb8 = new HBox();
		hb8.getChildren().addAll(PartyFaction, tfPartyFaction);
		hb8.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(PartyFaction, new Insets(0, 0, 0, 10));
		HBox.setMargin(tfPartyFaction, new Insets(0, 0, 0, 10));

		// # ADD Candidate

		Text CandidateText = new Text("Add Candidate ");
		Text CanditateName = new Text("Name: ");
		tfNameCandidate = new TextField();
		Text IDCandidate = new Text("ID: ");
		tfIDCandidateText = new TextField();
		Text BirthDateText2 = new Text("Birth Date (Days/months/years): ");
		tfBirthDateCandidate = new TextField();
		Text PartyNum = new Text("Party Number:");
		tfPartyNum = new TextField();
		Text PrimariesElectionNum = new Text("Primaries Number:");
		tfPrimeries = new TextField();
		Text KalfiNumText1 = new Text("Which Ballot Box u wanna vote in ? (insert it's number):");
		tfKalfiNumCandidate = new TextField();
		btnSubmitCandidate = new Button("Submit");

		HBox hb9 = new HBox();
		hb9.getChildren().addAll(CandidateText, CanditateName, tfNameCandidate);
		hb9.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(CanditateName, new Insets(0, 0, 0, 10));
		HBox.setMargin(tfNameCandidate, new Insets(0, 0, 0, 10));

		HBox hb10 = new HBox();
		hb10.getChildren().addAll(IDCandidate, tfIDCandidateText);
		hb10.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(IDCandidate, new Insets(0, 0, 0, 10));
		HBox.setMargin(tfIDCandidateText, new Insets(0, 0, 0, 10));

		HBox hb11 = new HBox();
		hb11.getChildren().addAll(BirthDateText2, tfBirthDateCandidate);
		hb11.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(BirthDateText2, new Insets(0, 0, 0, 10));
		HBox.setMargin(tfBirthDateCandidate, new Insets(0, 0, 0, 10));

		HBox hb12 = new HBox();
		hb12.getChildren().addAll(PartyNum, tfPartyNum);
		hb12.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(PartyNum, new Insets(0, 0, 0, 10));
		HBox.setMargin(tfPartyNum, new Insets(0, 0, 0, 10));

		HBox hb13 = new HBox();
		hb13.getChildren().addAll(PrimariesElectionNum, tfPrimeries);
		hb13.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(PrimariesElectionNum, new Insets(0, 0, 0, 10));
		HBox.setMargin(tfPrimeries, new Insets(0, 0, 0, 10));

		HBox hb14 = new HBox();
		hb14.getChildren().addAll(KalfiNumText1, tfKalfiNumCandidate);
		hb14.setAlignment(Pos.TOP_LEFT);
		HBox.setMargin(KalfiNumText1, new Insets(0, 0, 0, 10));
		HBox.setMargin(tfKalfiNumCandidate, new Insets(0, 0, 0, 10));

		btnShowBallotBox = new Button("Show-BallotBox");
		btnShowVoters = new Button("Show-Voters");
		btnShowParties = new Button("Show-Parties");
		btnShowCandidates = new Button("Show-Candidates");
		HBox hb15 = new HBox(btnShowBallotBox, btnShowVoters, btnShowParties, btnShowCandidates);

		btnShowResualts = new Button("Show-Resualts Election");
		btnStartElections = new Button("Start Elections");
		HBox hb16 = new HBox(btnShowResualts, btnStartElections);

		VBox vb = new VBox(6);
		vb.getChildren().addAll(hb, hb1, getBtnSubmitBallotBox(), hb2, hb3, hb4, hb5, getBtnSubmitVoter(), hb6, hb7,
				hb8, getBtnSubmitParty(), hb9, hb10, hb11, hb14, hb12, hb13, getBtnSubmitCandidate(), hb15, hb16);
		vb.setAlignment(Pos.TOP_LEFT);

		// New SCENE

		Scene scene = new Scene(vb, 800, 700);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public Button getBtnStartElections() {
		return btnStartElections;
	}

	public Button getBtnShowBallotBox() {
		return btnShowBallotBox;
	}

	public Button getBtnShowVoters() {
		return btnShowVoters;
	}

	public Button getBtnShowParties() {
		return btnShowParties;
	}

	public Button getBtnShowCandidates() {
		return btnShowCandidates;
	}

	public Button getBtnShowResaults() {
		return btnShowResualts;
	}

	public String getChoiceBox() {
		String valueChoiceBox = (String) choiceBox.getValue();
		return valueChoiceBox;
	}

	public String getTfAddress() {
		return tfAddress.getText();
	}

	public String getTfTypeBallotBox() {
		return tfTypeBallotBox.getText();
	}

	public String getTfBirthDateVoter() {
		return tfBirthDateVoter.getText();
	}

	public String getTfSickVoter() {
		return tfSickVoter.getText();
	}

	public int getTfVoterKalfiNum() {
		return Integer.parseInt(tfKalfiNum.getText());
	}

	public String getTfPartyName() {
		return tfPartyName.getText();
	}

	public String getTfPartyDate() {
		return tfPartyDate.getText();
	}

	public String getTfPartyFaction() {
		return tfPartyFaction.getText();
	}

	public String getTfNameCandidate() {
		return tfNameCandidate.getText();
	}

	public String getTfBirthDateCandidate() {
		return tfBirthDateCandidate.getText();
	}

	public String getTfIDCandidateText() {
		return tfIDCandidateText.getText();
	}

	public int getTfKalfiNumCandidate() {
		return Integer.parseInt(tfKalfiNumCandidate.getText());
	}

	public String getTfSickCandidate() {
		return tfSickCandidate.getText();
	}

	public int getTfPartyNum() {
		return Integer.parseInt(tfPartyNum.getText());
	}

	public int getTfPrimeries() {
		return Integer.parseInt(tfPrimeries.getText());
	}

	public String getTfNameVoterText() {
		return tfNameVoterText.getText();
	}

	public String getTfBirthDate() {
		return tfBirthDateVoter.getText();
	}

	public String getTfIDVoterText() {
		return tfIDVoterText.getText();
	}

	public String getTfSickVoter1() {
		return tfSickVoter.getText();
	}

	public String getAddress() {
		return tfAddress.getText();
	}

	public String getTypeBallotBox() {
		return tfTypeBallotBox.getText();
	}

	public void addEventHandlerToSubmitButton(EventHandler<ActionEvent> event) {
		getBtnSubmitBallotBox().setOnAction(event);
		getBtnSubmitVoter().setOnAction(event);
		getBtnSubmitParty().setOnAction(event);
		getBtnSubmitCandidate().setOnAction(event);
	}

	public void addEventToSubmit(EventHandler<ActionEvent> event, Button bot) {
		bot.setOnAction(event);
	}

	public Button getBtnSubmitBallotBox() {
		return btnSubmitBallotBox;
	}

	public Button getBtnSubmitVoter() {
		return btnSubmitVoter;
	}

	public Button getBtnSubmitCandidate() {
		return btnSubmitCandidate;
	}

	public Button getBtnSubmitParty() {
		return btnSubmitParty;
	}

}