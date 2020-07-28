package model;

public abstract class Citizen {

	protected MyDate birthDate;
	protected int id, age;
	protected boolean isSick;
	protected String name;
	private final int ID_MAX_LENGTH = 999999999, ID_MAX_NUMS = 9;

	public Citizen(String name, int id, MyDate birthDate, boolean isSick) throws UserExceptions {
		this.name = name;
		setID(id);
		this.isSick = isSick;
		setAge(birthDate);
	}

	public boolean setID(int checkID) throws UserExceptions {
		int counter = 0;
		int checker = checkID;
		while (checker > 0) {
			checker = checker / 10;
			counter++;
		}
		if (checkID <= 0 || checkID > ID_MAX_LENGTH || counter < ID_MAX_NUMS) {
			throw new UserExceptions("An ID should have 9 numbers, please insert again.");
		} else {
			id = checkID;
			return true;
		}
	}

	private boolean setAge(MyDate birthDate) {
		this.birthDate = new MyDate(birthDate);
		int age = birthDate.yearsValue();
		if (age < 0) {
			return false;
		} else {
			this.age = age;
			return true;
		}

	}

	public String toString() {
		return getName() + " id:" + getId() + " Birth Date: " + birthDate + " , got corona: " + isSick();
	}

	public boolean isSick() {
		return isSick;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
