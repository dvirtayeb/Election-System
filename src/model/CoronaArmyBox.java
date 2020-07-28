package model;

public class CoronaArmyBox extends BallotBox {
	private final int TYPE = 3;

	public CoronaArmyBox(String adress) {
		super(adress);

	}

	public boolean addVoter(Voter voter) {
		if (voter.isASoldier && voter.isSick()) {
			super.addVoter(voter);
			return true;
		}
		return false;
	}

	public int type() {
		return TYPE;
	}
	public String toString() {
		return "Corona Army Box: "+super.toString();
	}
}
