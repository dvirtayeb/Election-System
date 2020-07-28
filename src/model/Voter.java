package model;

public class Voter extends Citizen {
	public int kalfiNum, numType;
	public boolean canVote, isASoldier, carryAGun;
	public MyDate sickTime;

	public Voter(String name, int id, MyDate birthDate, boolean isSick, int kalfiNum) throws UserExceptions {
		super(name, id, birthDate, isSick); // sick or not from citizen
		setKalfiNum(kalfiNum);
		canVote = canVote(); // 18+
		if (!canVote) {
			throw new UserExceptions("The Voter's age isn't 18+, " + birthDate.toString());
		}
		isASoldier = checkSoldier(); // 18-21
	}

	public boolean setSicknessTime(MyDate date) {
		if (isSick()) {
			sickTime = new MyDate(date);
			return true;
		}
		return false;

	}

	public void setCarryAGun(boolean carry) {
		if (carry)
			carryAGun = carry;
	}

	public Voter(String name, int id, MyDate birthDate, boolean isSick) throws UserExceptions {
		this(name, id, birthDate, isSick, -1);
	}

	public Voter(Voter v) throws Exception {
		this(v.getName(), v.getId(), v.birthDate, v.isSick(), v.kalfiNum);
	}

	private boolean checkSoldier() {
		if (canVote && super.age <= 21) {
			return true;
		}
		return false;
	}

	public String getCondition() {
		if (isSick() && !isASoldier)
			return "Corona";
		if (isASoldier && !isSick())
			return "Army";
		if (isASoldier && isSick())
			return "SickSoldier";
		return "Normal";
	}

	public boolean setKalfiNum(int num) {
		if (num <= 0) {
			return false;
		} else {
			kalfiNum = num;
			return true;
		}
	}

	private boolean canVote() {
		if (super.age >= 18)
			return true;
		return false;
	}

	public boolean equals(Voter voter) {
		if (voter.getId() == getId() && voter.getName().equals(getName())) {
			return true;
		}
		return false;
	}

	public int getKalfiNum() { // minus 1 because of the difference of index arrays.
		return kalfiNum - 1;
	}

	public String toString() {
		return super.toString() + ", can Vote: " + canVote;
	}
}
