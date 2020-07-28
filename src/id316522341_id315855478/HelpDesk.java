package id316522341_id315855478;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.Candidate;
import model.MyDate;
import model.Party;
import model.Registry;
import model.UI;
import model.UserExceptions;
import model.Voter;

public class HelpDesk implements UI {
	public static Scanner scan;
	public static Registry reg;
	private final static int ID_MAX_LENGTH = 999999999;
	private final static int ID_MAX_NUMS = 9;

	public HelpDesk(Scanner scan, Registry reg) {
		this.scan = scan;
		this.reg = reg;
	}

	public void addBallotBox() {
		System.out.println("Enter Ballot Box Adress : ");
		String adress = scan.next();
		scan.nextLine();
		System.out.println(
				"Choose a Ballot Box to add :\n Army Ballot Box - 2 \n Corona Ballot Box -1 \n Sick Soldiers Ballot Box - 3 \n Regular Ballot Box -(any other number)");
		int type = scan.nextInt();
		reg.regBallotBox(adress, type);
	}

	public Voter addVoter() throws UserExceptions {
		int id = 0;
		boolean isOK = false;
		System.out.println("Name: ");
		String name = scan.next();
		scan.nextLine();
		do {
			try {
				id = insertId();
				isOK = true;
			} catch (InputMismatchException exception1) {
				System.out.println("you didn't insert numbers, exception has been thrown: " + exception1.getMessage());
				System.out.println("Please enter number with 9 numbers: ");
				isOK = false;
				scan.next();

			} catch (UserExceptions ID) {
				System.out.println(ID.getMessage());
				isOK = false;
			}

		} while (!isOK);
		MyDate birthDate = null;
		do {
			try {
				birthDate = getDateFromUser(isOK);
			} catch (UserExceptions age) {
				System.out.println("exception has been thrown: " + age.getMessage());
				isOK = false;
			}
		} while (!isOK);

		boolean isSick = false;
		System.out.println("You got the corona virus? (answer: true/false): ");
		try {
			isSick = scan.nextBoolean();
		} catch (InputMismatchException exception1) {
			System.out
					.println("you didn't boolean (true/false), exception has been thrown: " + exception1.getMessage());
			scan.next();
		}

		Voter voter = new Voter(name, id, birthDate, isSick);

		if (isSick) {
			do {
				System.out.println("Since when are u sick? : (");
				voter.setSicknessTime(insertSickDate(isOK));
			} while (!isOK);
		}
		if (voter.isASoldier) {
			System.out.println("Are you carrying a gun ?");
			try {
				voter.setCarryAGun(scan.nextBoolean());
			} catch (InputMismatchException exception1) {
				System.out.println(
						"you didn't boolean (true/false), exception has been thrown: " + exception1.getMessage());
				scan.next();
			}
		}

		System.out.println("Look at the following Ballot Boxes, Which number you prefer?");
		if (voter.isASoldier && voter.isSick()) {
			reg.showCoronaArmyBox();
		} else if (voter.isSick()) {
			reg.showCoronaBallotBox();
		} else if (voter.isASoldier) {
			reg.showArmyBallotBox();
		} else {
			reg.showBallotBox();
		}
		voter.setKalfiNum(scan.nextInt());
		return voter;
	}

	public void addParty() throws UserExceptions {
		System.out.println("Name: ");
		String name = scan.next();
		scan.nextLine();
		System.out.println("Date: ");
		MyDate date = new MyDate(scan.nextInt(), scan.nextInt(), scan.nextInt());
		System.out.println("Faction: ");
		String faction = scan.next();
		reg.regParty(new Party(name, date, faction));
	}

	public void addCandidate() throws UserExceptions {
		Voter voter = addVoter();
		System.out.println("Look at the following Parties, Which number you prefer?");
		reg.showAllParties();
		int partyNum = scan.nextInt();
		System.out.println("which number you in the Primary-Elections? ");
		int primaryElections = scan.nextInt();
		reg.regCandidate(new Candidate(voter, partyNum, primaryElections));
	}

	private static int insertId() throws UserExceptions {
		System.out.println("id: ");
		int id = scan.nextInt();
		int counter = 0;
		int checker = id;
		while (checker > 0) {
			checker = checker / 10;
			counter++;
		}
		if (id <= 0 || id > ID_MAX_LENGTH || counter < ID_MAX_NUMS)
			throw new UserExceptions("An ID should have 9 numbers, please insert again.");
		for (int i = 0; i < reg.getVoterList().size(); i++) {
			if (id == reg.getVoter(i).getId())
				throw new UserExceptions("This ID is already exist, please try again");
		}

		return id;
	}

	private static MyDate insertDate() throws UserExceptions {
		MyDate bDay;
		System.out.println("Date (day - month - year) : ");
		int day = scan.nextInt();
		int month = scan.nextInt();
		int year = scan.nextInt();
		bDay = new MyDate(day, month, year);
		if (bDay.yearsValue() < 18)
			throw new UserExceptions("An under aged voter cannot be registered, please try again");

		return bDay;
	}

	private static MyDate insertSickDate(boolean isOK) {
		MyDate sickDate;
		System.out.println("Date (day - month - year) : ");
		try {
			int day = scan.nextInt();
			int month = scan.nextInt();
			int year = scan.nextInt();
			sickDate = new MyDate(day, month, year);
			isOK = true;
			return sickDate;
		} catch (InputMismatchException exception1) {
			System.out.println("you didn't insert numbers, exception has been thrown: " + exception1.getMessage());
			isOK = false;
			scan.next();
			return null;
		}
	}

	public static MyDate getDateFromUser(boolean isOK) throws UserExceptions {
		try {
			MyDate date = insertDate();
			isOK = true;
			return date;
		} catch (InputMismatchException exception1) {
			System.out.println("you didn't insert numbers, exception has been thrown: " + exception1.getMessage());
			isOK = false;
			scan.next();
			return null;
		}

	}

	public void elections() throws UserExceptions {
		reg.startElections();
		boolean answer;
		int numberOfPartyChoice = 0;
		for (int i = 0; i < reg.getVoterNum(); i++) {
			System.out.println(reg.getVoter(i).getName() + ": Do you want to vote? True/False");
			answer = scan.nextBoolean();
			if (answer) {
				if (question(i)) { // function that questions the voter by his condition according to his ballotbox
									// type
					System.out.println(
							"Go ahead and pick a party to vote to,\n If you want to put an empty vote, vote: #0  ");
					reg.showAllParties();
					numberOfPartyChoice = scan.nextInt();
					if (numberOfPartyChoice <= reg.getPartyNum())
						reg.regElections(numberOfPartyChoice, reg.getVoter(i));
				}

			}
		}
	}

	private boolean questionSick(int voterNum) throws UserExceptions {
		System.out.println("Since when you are sick? ");
		MyDate date = getDateFromUser(true);
		reg.getVoter(voterNum).setSicknessTime(date);
		return true;
	}

	private boolean questionSoldier(int voterNum) {
		System.out.println("Do you carry a weapon? true/false");
		boolean check = scan.nextBoolean();
		reg.getVoter(voterNum).setCarryAGun(check);
		return !check;
	}

	private boolean questionSickSoldier(int voterNum) throws UserExceptions {
		if (questionSoldier(voterNum) && questionSick(voterNum))
			return true;
		return false;
	}

	private boolean question(int voterNum) throws UserExceptions {
		int ballotType = reg.getBallotBoxList().get(reg.getVoterKalfi(voterNum)).type();
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
