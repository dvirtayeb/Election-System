package program;

import java.util.Scanner;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Candidate;
import model.Model;
import model.MyDate;
import model.Party;
import model.Registry;
import model.UserExceptions;
import model.Voter;
import view.View;




public class Program extends Application{
	private static Registry reg=new Registry();
/*	public static final int ADD_BALLOT_BOX = 1;
	public static final int ADD_CITIZEN = 2;
	public static final int ADD_PARTY = 3;
	public static final int ADD_CANDIDATE = 4;
	public static final int SHOW_BALLOT_BOX = 5;
	public static final int SHOW_ALL_CITIZEN = 6;
	public static final int SHOW_ALL_PARTY = 7;
	public static final int ELECTIONS = 8;
	public static final int SHOW_ELECTION_REASULT = 9;
	public static final int EXIT = 10;

	public static void choose(HelpDesk hd, Scanner scan, Registry reg, int num) throws UserExceptions {
		// description: Menu interactive.

		switch (num) {
		case ADD_BALLOT_BOX:
			hd.addBallotBox();
			break;
		case ADD_CITIZEN:
			reg.regVoter(hd.addVoter());
			break;
		case ADD_PARTY:
			hd.addParty();
			break;
		case ADD_CANDIDATE:
			hd.addCandidate();
		case SHOW_BALLOT_BOX:
			reg.showBallotBox();
			break;
		case SHOW_ALL_CITIZEN:
			reg.showAllCitizen();
			break;
		case SHOW_ALL_PARTY:
			reg.showAllParties();
			break;
		case ELECTIONS:
			hd.elections();
			break;
		case SHOW_ELECTION_REASULT:
			reg.showElectionReasult();
			break;
		default:
			System.out.println("you didn't choose the right number(1-10), Try again.");
			break;
		}
	}
*/
	public static void main(String[] args) throws UserExceptions {
	//	Registry reg = new Registry(); // Hard Coded:
//		Scanner scan = new Scanner(System.in);
//		HelpDesk hd = new HelpDesk(scan, reg);

		MyDate d = new MyDate(1, 2, 1990);
		MyDate d1 = new MyDate(24, 7, 1990);
		MyDate d2 = new MyDate(1, 11, 1999);
		MyDate d3 = new MyDate(7, 2, 1995);
		MyDate d4 = new MyDate(15, 3, 1993);
		MyDate d5 = new MyDate(5, 2, 1990);


		reg.regBallotBox("Modiin",1);
		reg.regBallotBox("Ramat Hagolan",2);
		reg.regBallotBox("Yehud",0);

		Party likud = new Party("Mahal", d, "RIGHT");
		Party kaholLavan = new Party("blue&White", d5, "LEFT");
		Party greenLeaf = new Party("AleYarok", d1, "CENTER");

		reg.regParty(likud);
		reg.regParty(kaholLavan);
		reg.regParty(greenLeaf);

		Voter voter1 = new Voter("david", 123456789, d2, false, 2);
		Voter voter2 = new Voter("yosi", 145623789, d1, false, 3);
		Voter voter3 = new Voter("halil", 123456987, d5, false, 3);
		Voter voter4 = new Voter("dvora", 198745623, d3, false, 3);
		Voter voter5 = new Voter("yaron", 145678923, d, true, 1);// got corona

		Candidate kahlon = new Candidate("Kahlon", 165478932, d1, false, 3, 3, 3);
		Candidate bibi = new Candidate("bibi", 145678925, d1, false, 3, 1, 1);
		Candidate davidBitan = new Candidate("David Bitan", 145678239, d, false, 3, 1, 2);
		Candidate bennyGantz = new Candidate("Benny Gantz", 115678942, d3, false, 3, 2, 1);
		Candidate gennyBantz = new Candidate("Genny Bantz", 145782369, d4, false, 3, 3, 2);
		Candidate yairLapid = new Candidate("Yair Lapid", 156478932, d1, false, 3, 2, 3);

		reg.regVoter(voter1);
		reg.regVoter(voter2);
		reg.regVoter(voter3);
		reg.regVoter(voter4);
		reg.regVoter(voter5);

		reg.regCandidate(kahlon);
		reg.regCandidate(yairLapid);
		reg.regCandidate(gennyBantz);
		reg.regCandidate(bibi);
		reg.regCandidate(bennyGantz);
		reg.regCandidate(davidBitan);
		
		launch(args);
/*
		String menu = "Choose the number do you want to add or show:\n" + "1.Add BallotBox\n" + "2.Add Citizen\n"
				+ "3.Add Party\n" + "4.Add Candidate\n" + "5.Show the BallotBox\n" + "6.Show the Citizen\n"
				+ "7.Show the Parties\n" + "8.Make Elections\n" + "9.Show the elections reasult\n" + "10.Exit\n"
				+ "Your choice: ";
		System.out.println(menu);
		int num = scan.nextInt();
		while (num != EXIT) {
			choose(hd, scan, reg, num);
			if (num != EXIT)
				System.out.println("\n What is your next choice: " + menu);
			num = scan.nextInt();
		}
		System.out.println("Bye, See you next time.");
*/
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Model theModel = new Model();
		//Registry theReg = new Registry();
		View theView = new View(primaryStage);
		Controller theController = new Controller(theModel, theView, reg);
		
	}

}