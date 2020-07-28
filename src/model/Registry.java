package model;

import java.util.ArrayList;

public class Registry {

	private Set<Voter> voterList;
	private int voterNum;

	private ArrayList<Party> partyList;
	private int partyNum;

	private ArrayList<Candidate> candidateList;
	private int candidateNum;

	private ArrayList<BallotBox> ballotBoxList;

	private final int ARMY_BOX_TYPE = 2, CORONA_BOX_TYPE = 1, CORONA_ARMY_TYPE = 3;

	private ArrayList<Integer> resaults;

	public Registry() { // description: Object arraysList for each category
		partyList = new ArrayList<Party>();
		partyNum = 0;
		candidateList = new ArrayList<Candidate>();
		candidateNum = 0;
		ballotBoxList = new ArrayList<BallotBox>();
		voterList = new Set<>();
		voterNum = 0;

	}

	public void regBallotBox(String adress, int type) {
		switch (type) {
		case CORONA_BOX_TYPE:
			ballotBoxList.add(new CoronaBox(adress));
			break;
		case ARMY_BOX_TYPE:
			ballotBoxList.add(new ArmyBox(adress));
			break;
		case CORONA_ARMY_TYPE:
			ballotBoxList.add(new CoronaArmyBox(adress));
			break;
		default:
			ballotBoxList.add(new BallotBox(adress));
			break;
		}

	}

	public void regBallotBox(BallotBox b) {
		ballotBoxList.add(b);
	}

	public ArrayList<BallotBox> getBallotBoxList() {
		return ballotBoxList;
	}

	public void regVoter(Voter voter) throws UserExceptions {
		// term if the user choose wrong ballot box we cannot add him
		if (ballotBoxList.get(voter.getKalfiNum()).addVoter(voter)) {
			voterList.add(voter);
			voterNum++;
		}

	}

	public ArrayList<Party> getPartyList() {
		return partyList;
	}

	public void regParty(Party p) {
		partyList.add(p);
		partyNum++;
	}

	private void addCandidateToParty(Candidate candidate) {
		for (int i = 0; i < partyNum; i++) {
			if (candidateList.get(candidateNum).getPartyNum() == partyList.get(i).getPartyNum()) {
				partyList.get(i).addCandidate(candidate);
			}
		}
	}

	public boolean regCandidate(Candidate candidate) throws UserExceptions {
		for (int i = 0; i < voterList.size(); i++) {
			if (candidate.equals(voterList.getVoter(i))) {
				regCandidateManually(candidate);
				return true;
			}
		}
		regVoter(candidate);
		regCandidateManually(candidate);

		return false;
	}

	public void regCandidateManually(Candidate candidate) {
		candidateList.add(candidate);
		addCandidateToParty(candidate);
		candidateNum++;
	}

	public String showBallotBox() {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < ballotBoxList.size(); i++) {
			sb.append(ballotBoxList.get(i) + "\n");
			System.out.println(ballotBoxList.get(i));
		}
		return sb.toString();
	}

	public void showCoronaBallotBox() {
		for (int i = 0; i < ballotBoxList.size(); i++) {
			if (ballotBoxList.get(i).type() == CORONA_BOX_TYPE)
				System.out.println(ballotBoxList.get(i));
		}
	}

	public String showArmyBallotBox() {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < ballotBoxList.size(); i++) {
			if (ballotBoxList.get(i).type() == ARMY_BOX_TYPE)
				sb.append(ballotBoxList.get(i) + "\n");
			System.out.println(ballotBoxList.get(i));
		}
		return sb.toString();
	}

	public String showCoronaArmyBox() {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < ballotBoxList.size(); i++) {
			if (ballotBoxList.get(i).type() == CORONA_ARMY_TYPE)
				sb.append(ballotBoxList.get(i) + "\n");
			System.out.println(ballotBoxList.get(i));
		}
		return sb.toString();
	}

	public String showAllCitizen() {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < voterNum; i++) {
			sb.append(voterList.getVoter(i) + "\n");
			System.out.println(voterList.getVoter(i));
		}
		return sb.toString();
	}

	public String showAllParties() {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < partyNum; i++) {
			sb.append(partyList.get(i) + "\n");
			System.out.println(partyList.get(i));
		}
		return sb.toString();
	}

	public void startElections() {
		resaults = new ArrayList<Integer>();
		resaults.add(0);// if you vote to this {index0} u don't want to vote to any party.
		for (int i = 0; i < partyList.size(); i++) {
			resaults.add(0);
		}
		for (int i = 0; i < ballotBoxList.size(); i++) {
			ballotBoxList.get(i).setResaultList(partyList.size());
		}
	}

	public void regElections(int numberOfpartyChoice, Voter voter) {
		resaults.set(numberOfpartyChoice, resaults.get(numberOfpartyChoice) + 1);
		ballotBoxList.get(voter.getKalfiNum()).electParty(numberOfpartyChoice);
	}

	public String showElectionReasult() {
		StringBuffer sb = new StringBuffer("General Votes: \n" + resaults.get(0) + " People voted to no one \n");
		System.out.println(resaults.get(0) + " People voted to no one");
		for (int i = 1; i < resaults.size(); i++) {
			sb.append(resaults.get(i) + " voted to " + partyList.get(i - 1).getPartyName() + "\n");
			System.out.println(resaults.get(i) + " voted to " + partyList.get(i - 1).getPartyName());
		}
		System.out.println();
		for (int i = 0; i < ballotBoxList.size(); i++) {
			sb.append(ballotBoxList.get(i).showResaults() + "\n");
			System.out.println(ballotBoxList.get(i).showResaults());
		}
		return sb.toString();
	}

	public void showBallotElect(int ballotNum) {
		System.out.println(ballotBoxList.get(ballotNum).showResaults());
	}

	public int getVoterKalfi(int numberCitizen) {
		return voterList.getVoter(numberCitizen).getKalfiNum();
	}

	public int getVoterNum() {
		return voterNum;
	}

	public int getPartyNum() {
		return partyNum;
	}

	public Voter getVoter(int i) {
		return voterList.getVoter(i);

	}

	public ArrayList<Voter> getVoterList() {
		return voterList.getList();
	}

}