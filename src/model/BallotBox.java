package model;

import java.util.ArrayList;

public class BallotBox {
	public static int ballotENum = 1;
	public String adress;
	public ArrayList<Voter> voterCitizens;
	public ArrayList<Integer> resaults;
	public int ballotNum, citizenNum/* , numType */;
	public double percentageOfVoters;
	private final int TYPE = 0;

	public BallotBox(String adress) {
		ballotNum = ballotENum++;
		this.adress = adress;
		voterCitizens = new ArrayList<Voter>();
		citizenNum = 0;

	}

	public BallotBox() {
		this("Default");
	}

	public boolean addVoter(Voter voter) { // voter can vote
		citizenNum++;
		if (voterCitizens.size() != 0)
			percentageOfVoters = (double) voterCitizens.size() / citizenNum;
		if (voter.canVote) {
			voterCitizens.add(voter);
			return true;
		}
		return false;

	}

	public void electParty(int num) {
		resaults.set(num, resaults.get(num) + 1);
	}

	public boolean setResaultList(int length) {
		resaults = new ArrayList<Integer>();
		resaults.add(0);
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				resaults.add(0);
			}
			return true;
		}
		return false;
	}

	public String showResaults() {
		StringBuffer back = new StringBuffer("The resaults in Ballot Box #" + ballotNum + ", are:\n");
		back.append("Voted to no one:" + resaults.get(0) + "\n");
		for (int i = 1; i < resaults.size(); i++) {
			back.append("party #" + i + ", got :" + resaults.get(i) + " Votes\n");
		}
		back.append(percentageOfVoters * 100 + "% of the BallotBox registered people voted");
		return back.toString();
	}

	public int type() {
		return TYPE;
	}

	public String toString() {
		return "BallotBox #" + ballotNum + ", That located in: " + adress;
	}

}
