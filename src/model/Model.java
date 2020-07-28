package model;

public class Model {

	private Voter voter;
	private BallotBox ballot;
	private Candidate candidate;
	private Party party;

	private String address;
	private String typeBallotBox;

	private String name;
	private String id;
	private String birthDate;
	private String sick;

	public Model() {
		// Ballot Box
		address = "";
		typeBallotBox = "";
		/*
		 * // Voter: name = ""; id = ""; birthDate = ""; sick = "";
		 */
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getSick() {
		return sick;
	}

	public void setSick(String sick) {
		this.sick = sick;
	}

	public String getTypeBallotBox() {
		return typeBallotBox;
	}

	public void setTypeBallotBox(String typeBallotBox) {
		this.typeBallotBox = typeBallotBox;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Voter getVoter() {
		return voter;
	}

	public void setVoter(Voter voter) {
		this.voter = voter;
	}

	public BallotBox getBallot() {
		return ballot;
	}

	public void setBallot(BallotBox ballot) {
		this.ballot = ballot;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}
}