package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.BallotBox;
import model.Candidate;
import model.Model;
import model.MyDate;
import model.Party;
import model.Registry;
import model.UserExceptions;
import model.Voter;
import view.Message;
import view.View;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

import id316522341_id315855478.HelpDesk;

public class Controller {

	private final static int ID_MAX_LENGTH = 999999999;
	private final static int ID_MAX_NUMS = 9;
	public static Model theModel;
	public static Registry theReg;
	public static View theView;
	private Alert succes;
	private static Alert choice;
	private static Alert err;
	private static TextInputDialog question;
	private static TextInputDialog elect;

	public Controller(Model theModel, View theView, Registry theReg) throws UserExceptions {
		Controller.theModel = theModel;
		Controller.theView = theView;
		Controller.theReg = theReg;
		Message msg = new Message(new Stage());
		succes = new Alert(AlertType.INFORMATION, "Added Succesfully", ButtonType.OK);
		err = new Alert(AlertType.ERROR, "", ButtonType.OK);
		choice = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
		question = new TextInputDialog("dd/mm/yyyy");
		elect = new TextInputDialog("");
		elect.setContentText("Choose Wisely! Party Number : ");

		EventHandler<ActionEvent> eventSubmitBallot = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					addBallotBox();
					succes.show();
					msg.addMessage("" + theModel.getBallot());
				} catch (Exception e) {
					BallotBox ballot=new BallotBox(theView.getAddress());
					theReg.regBallotBox(ballot);
					err.setContentText("Ballot type hasn't been chosen so we made a default type");
					err.show();
					theModel.setBallot(ballot);
					msg.addMessage("" + theModel.getBallot());
				}
			}

		};

		// attach eventToSubmitButton to button itself
		theView.addEventToSubmit(eventSubmitBallot, theView.getBtnSubmitBallotBox());

		EventHandler<ActionEvent> eventSubmitVoter = new EventHandler<ActionEvent>() { // add voter

			@Override
			public void handle(ActionEvent event) {
				try {
					if (addVoter()) {
						succes.show();
						msg.addMessage("" + theModel.getVoter());
					}
				} catch (UserExceptions e) {
					err.show();
				}
			}
		};
		theView.addEventToSubmit(eventSubmitVoter, theView.getBtnSubmitVoter());

		EventHandler<ActionEvent> eventSubmitCandidate = new EventHandler<ActionEvent>() { // add Candidate

			@Override
			public void handle(ActionEvent event) {
				try {
					if (addCandidate()) {
						succes.show();
						msg.addMessage("" + theModel.getCandidate());
					}
				} catch (UserExceptions e) {
					err.show();
				}
			}
		};
		theView.addEventToSubmit(eventSubmitCandidate, theView.getBtnSubmitCandidate());

		EventHandler<ActionEvent> eventSubmitParty = new EventHandler<ActionEvent>() { // add party

			@Override
			public void handle(ActionEvent event) {
				try {
					if (addParty()) {
						succes.show();
						msg.addMessage("" + theModel.getParty());
					}
				} catch (UserExceptions e) {
					err.show();
				}
			}
		};
		theView.addEventToSubmit(eventSubmitParty, theView.getBtnSubmitParty());

		EventHandler<ActionEvent> eventShowVoters = new EventHandler<ActionEvent>() {// Show Voters Button
			public void handle(ActionEvent event) {
				msg.addMessage("" + theReg.showAllCitizen());
			}
		};
		theView.addEventToSubmit(eventShowVoters, theView.getBtnShowVoters());

		EventHandler<ActionEvent> eventShowBallotBoxes = new EventHandler<ActionEvent>() {// Show Kalfi Button
			public void handle(ActionEvent event) {
				msg.addMessage("" + theReg.showBallotBox());
			}
		};
		theView.addEventToSubmit(eventShowBallotBoxes, theView.getBtnShowBallotBox());

		EventHandler<ActionEvent> eventShowParties = new EventHandler<ActionEvent>() { // Show Parties Button
			public void handle(ActionEvent event) {
				msg.addMessage("" + theReg.showAllParties());
			}
		};
		theView.addEventToSubmit(eventShowParties, theView.getBtnShowParties());

		EventHandler<ActionEvent> eventShowResaults = new EventHandler<ActionEvent>() {// Show Resaults Button
			public void handle(ActionEvent event) {
				msg.addMessage("" + theReg.showElectionReasult());
			}
		};
		theView.addEventToSubmit(eventShowResaults, theView.getBtnShowResaults());

		EventHandler<ActionEvent> eventElections = new EventHandler<ActionEvent>() {// Election Button
			public void handle(ActionEvent event) {
				try {
					elections();
				} catch (UserExceptions e) {
					err.show();
				}
			}
		};
		theView.addEventToSubmit(eventElections, theView.getBtnStartElections());

	}

	private static MyDate insertVoterDate() {
		MyDate bDay;
		String[] str = theView.getTfBirthDateVoter().split("/");
		if (str.length == 3) {
			bDay = new MyDate(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
			return bDay;
		} else {
			err.setContentText("Date insertion was invalid, please try again according to the instructions.");
			err.show();
			return null;
		}
	}

	private static MyDate insertPartyDate() {
		MyDate bDay;
		String[] str = theView.getTfPartyDate().split("/");
		if (str.length == 3) {
			bDay = new MyDate(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
			return bDay;
		} else {
			err.setContentText("Date insertion was invalid, please try again according to the instructions.");
			err.show();
			return null;
		}
	}

	private static MyDate insertCandidateDate() {
		MyDate bDay;
		String[] str = theView.getTfBirthDateCandidate().split("/");
		if (str.length == 3) {
			bDay = new MyDate(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
			return bDay;
		} else {
			err.setContentText("Date insertion was invalid, please try again according to the instructions.");
			err.show();
			return null;
		}
	}

	private static int insertId(int getID) {
		int id = getID;
		int counter = 0;
		int checker = id;
		while (checker > 0) {
			checker = checker / 10;
			counter++;
		}
		if (id <= 0 || id > ID_MAX_LENGTH || counter < ID_MAX_NUMS) {
			err.setContentText("ID Should have excaly 9 Numbers in it.");
			err.show();
			return -1;
		}
		for (int i = 0; i < theReg.getVoterList().size(); i++) {
			if (id == theReg.getVoter(i).getId()) {
				err.setContentText("This Voter is allready Registered");
				err.show();
				return -1;
			}
		}

		return id;
	}

	public void addBallotBox() {
		int typeBallotBox = Integer.parseInt("" + theView.getChoiceBox().charAt(0));
		theReg.regBallotBox(theView.getAddress(), typeBallotBox);
		theModel.setBallot(theReg.getBallotBoxList().get(theReg.getBallotBoxList().size() - 1));

	}

	private static boolean addVoter() throws UserExceptions {
		boolean carryGun = false;
		boolean isSick = false;
		if (insertVoterDate() != null && insertId(Integer.parseInt(theView.getTfIDVoterText())) != -1) {
			MyDate date = insertVoterDate();
			int id = insertId(Integer.parseInt(theView.getTfIDVoterText()));
			if (date.yearsValue() >= 18 && date.yearsValue() <= 21) {
				choice.setContentText("Are you carrying a weapon?");
				choice.showAndWait();
				if (choice.getResult() == ButtonType.YES)
					carryGun = true;
			}
			choice.setContentText("Are you Sick?");
			choice.showAndWait();
			if (choice.getResult() == ButtonType.YES)
				isSick = true;
			Voter vot = new Voter(theView.getTfNameVoterText(), id, date, isSick, theView.getTfVoterKalfiNum());
			theReg.regVoter(vot);
			theReg.getVoter(theReg.getVoterList().size() - 1).setCarryAGun(carryGun);
			theModel.setVoter(vot);
			return true;
		}
		return false;
	}

	public boolean addCandidate() throws UserExceptions {
		Voter voter;
		boolean carryGun = false;
		boolean isSick = false;
		if (insertCandidateDate() != null && insertId(Integer.parseInt(theView.getTfIDCandidateText())) != -1) {
			MyDate date = insertCandidateDate();
			int id = insertId(Integer.parseInt(theView.getTfIDCandidateText()));
			if (date.yearsValue() >= 18 && date.yearsValue() <= 21) {
				choice.setContentText("Are you carrying a weapon?");
				choice.showAndWait();
				if (choice.getResult() == ButtonType.YES)
					carryGun = true;
			}
			choice.setContentText("Are you Sick?");
			choice.showAndWait();
			if (choice.getResult() == ButtonType.YES)
				isSick = true;
			voter = new Voter(theView.getTfNameCandidate(), id, date, isSick, theView.getTfKalfiNumCandidate());
			Candidate cand = new Candidate(voter, theView.getTfPartyNum(), theView.getTfPrimeries());
			theReg.regCandidate(cand);
			theReg.getVoter(theReg.getVoterList().size() - 1).setCarryAGun(carryGun);
			theModel.setCandidate(cand);
			return true;
		}
		return false;

	}

	public boolean addParty() throws UserExceptions {
		if (insertPartyDate() != null) {
			MyDate date = insertPartyDate();
			Party party;
			try {
				party = new Party(theView.getTfPartyName(), date, theView.getTfPartyFaction());
				theReg.regParty(party);
				theModel.setParty(party);
				return true;
			} catch (Exception e) {
				err.setContentText("Faction Does not exists");
				err.show();
			}
		}
		return false;
	}

	public void elections() throws UserExceptions {
		theReg.startElections();
		int numberOfPartyChoice = 0;
		for (int i = 0; i < theReg.getVoterNum(); i++) {
			choice.setHeaderText("" + theReg.getVoter(i));
			choice.setContentText("Do you want to vote?");
			choice.showAndWait();
			if (choice.getResult() == ButtonType.YES) {
				if (question(i)) { // function that questions the voter by his condition according to his ballotbox
									// type
					Optional<String> resault = elect.showAndWait();
					if (resault.isPresent()) {
						numberOfPartyChoice = Integer.parseInt(resault.get());
						if (numberOfPartyChoice <= theReg.getPartyNum())
							theReg.regElections(numberOfPartyChoice, theReg.getVoter(i));
					}
				}

			}
		}
	}

	private boolean questionSick(int voterNum) throws UserExceptions {
		MyDate sDate;
		question.setTitle("Small Question:");
		question.setContentText("Enter your Date");
		Optional<String> resault = question.showAndWait();
		if (resault.isPresent()) {
			String[] date = resault.get().split("/");
			sDate = new MyDate(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
		} else
			sDate = new MyDate();
		theReg.getVoter(voterNum).setSicknessTime(sDate);
		return true;
	}

	private boolean questionSoldier(int voterNum) {
		boolean carryGun = false;
		choice.setContentText("Are you carrying a weapon?");
		choice.showAndWait();
		if (choice.getResult() == ButtonType.YES)
			carryGun = true;
		theReg.getVoter(voterNum).setCarryAGun(carryGun);
		return !carryGun;

	}

	private boolean questionSickSoldier(int voterNum) throws UserExceptions {
		if (questionSoldier(voterNum) && questionSick(voterNum))
			return true;
		return false;
	}

	private boolean question(int voterNum) throws UserExceptions {
		int ballotType = theReg.getBallotBoxList().get(theReg.getVoterKalfi(voterNum)).type();
		switch (ballotType) {
		case 1:
			return questionSick(voterNum);
		case 2:
			return questionSoldier(voterNum);
		case 3:
			return questionSickSoldier(voterNum);
		default:
			return true;
		}
	}

}
