package model;

public class Candidate extends Voter implements Comparable<Candidate> {

	public int partyNum;
	public static int candidatesENum = 1;
	public int candidateNum;
	private int primaryElections;

	public Candidate(String name, int id, MyDate birthDate, boolean isSick, int kalfiNum, int party,
			int primaryElections) throws UserExceptions {
		super(name, id, birthDate, isSick, kalfiNum);
		partyNum = party;
		candidateNum = candidatesENum++;
		this.primaryElections = primaryElections;
	}

	public Candidate(Voter voter, int party, int primaryElections) throws UserExceptions {
		this(voter.getName(), voter.getId(), voter.birthDate, voter.isSick(), voter.kalfiNum, party, primaryElections);
	}

	public int getPrimaryElections() {
		return primaryElections;
	}

	@Override
	public int compareTo(Candidate compareCandidate) {
		int compareVotes = ((Candidate) compareCandidate).getPrimaryElections();
		return this.primaryElections - compareVotes;
	}

	public int getPartyNum() {
		return partyNum;
	}

	public String toString() {
		return super.toString() + ", Number of party: " + partyNum
				+ " , PrimaryElections: " + primaryElections;
	}

}