package model;

public interface UI {

	void addBallotBox();

	void addCandidate() throws UserExceptions;

	void addParty() throws UserExceptions;

	Voter addVoter() throws UserExceptions;

	void elections() throws UserExceptions;

}
