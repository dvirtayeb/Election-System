package model;

import java.util.ArrayList;

public class Set<T extends Voter> {

	private ArrayList<T> voterList;

	public Set() {
		voterList = new ArrayList<T>();
	}

	public boolean add(T v) throws UserExceptions {
		if (voterList.size() == 0) {
			voterList.add(v);
			return true;
		}
		if (valuesEquals(v))
			return false;
		if (searchID(v))
			setRandomId(v);
		voterList.add(v);
		return true;
	}

	public boolean valuesEquals(T v) throws UserExceptions {
		for (int i = 0; i < voterList.size(); i++) {
			if (v.equals(voterList.get(i)))
				throw new UserExceptions("this Voter is allready Exists in the Set.");
		}
		return false;
	}

	private boolean searchID(Voter voter) {
		for (int i = 0; i < voterList.size(); i++) {
			if (voterList.get(i).getId() == voter.getId()) {
				return true;
			}
		}
		return false;
	}

	private void setRandomId(Voter voter) throws UserExceptions {
		int maxId = 0;
		for (int i = 0; i < voterList.size(); i++) {
			if (maxId < voterList.get(i).getId())
				maxId = voterList.get(i).getId();
		}
		voter.setID(maxId + 1);
	}

	public ArrayList<T> getList() {
		return voterList;
	}

	public <Voter> T getVoter(int i) {
		return voterList.get(i);
	}

	public int size() {
		return voterList.size();
	}

}