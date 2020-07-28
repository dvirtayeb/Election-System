package model;

public class ArmyBox extends BallotBox {

	private final int TYPE = 2;

	public ArmyBox(String adress) {
		super(adress);

	}

	public boolean addVoter(Voter voter) {
		if (voter.isASoldier) {
			super.addVoter(voter);
			return true;
		}
		return false;
	}

	public int type() {
		return TYPE;
	}
	public String toString() {
		return "Army Box: "+super.toString();
	}

}
