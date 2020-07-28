package model;

public class CoronaBox extends BallotBox {
	final int TYPE = 1;

	public CoronaBox(String adress) {
		super(adress);

	}

	public boolean addVoter(Voter voter) {
		if (voter.isSick()) {
			super.addVoter(voter);
			return true;
		}
		return false;
	}

	public int type() {
		return TYPE;
	}
	public String toString() {
		return "Corona Box: "+super.toString();
	}

}
