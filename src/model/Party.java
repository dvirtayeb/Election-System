package model;

import java.util.ArrayList;
import java.util.Collections;

public class Party {
	public int candidatesNum = 0;
	private String partyName, kindFaction;
	private MyDate date;
	private ArrayList<Candidate> candidatesList;
	public static int partyENum = 1;
	public int partyNum;

	public enum Faction {
		RIGHT, CENTER, LEFT
	};

	public Party(String partyName, MyDate date, String faction) throws UserExceptions {
		this.partyName = partyName;
		this.date = date;
		partyNum = partyENum++;
		kindFaction = returnLegalFaction(faction);
		candidatesList = new ArrayList<Candidate>();
	}

	public void addCandidate(Candidate c) {
		candidatesList.add(c);
		Collections.sort(candidatesList);
		candidatesNum++;

	}

	public boolean isFactionCorrect(String faction) throws UserExceptions {
		Faction[] types = Faction.values();
		for (int i = 0; i < types.length; i++) {
			if (types[i].name().equals(faction))
				return true;
		}
		throw new UserExceptions("Faction Does not exists");
	}

	public String returnLegalFaction(String faction) throws UserExceptions {
		if (isFactionCorrect(faction))
			return faction;
		return "faction don't exist";
	}

	public String getKindFaction() {
		return kindFaction;
	}

	public int getPartyNum() {
		return partyNum;
	}

	public String getPartyName() {
		return partyName;
	}

	public MyDate getDate() {
		return date;
	}

	private String candidateList() {
		StringBuffer back = new StringBuffer("");
		for (int i = 0; i < candidatesNum; i++) {
			back.append(candidatesList.get(i) + "\n");
		}
		return back.toString();
	}

	public boolean equals(Party p) {
		if (p.getPartyName().equals(partyName)) {
			return true;
		}
		return false;
	}

	public String toString() {
		return "party:#" + partyNum + "," + partyName + "\n faction:  " + kindFaction + ", PartyOpen: " + date;
	}

}