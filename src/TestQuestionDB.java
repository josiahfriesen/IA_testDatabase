/**
* date: Nov 17 2017
* purpose: This is the class for a TestQuestiojnDB object. DB stands for database, and it is essentially the object containing all information 
* that is relevant to tests, which is then written to a text file as a single object. 
*/

import java.util.ArrayList;

public class TestQuestionDB implements java.io.Serializable {
	
	ArrayList<Test> tests = new ArrayList<Test>();
	ArrayList<Test> usedTests = new ArrayList<Test>();
	ArrayList<String> possibleStandards = new ArrayList<String>();
	ArrayList<Integer> standardCount = new ArrayList<Integer>();
	
	public ArrayList<Test> getUsedTests() {
		return usedTests;
	}
	public ArrayList<String> getPossibleStandards() {
		return possibleStandards;
	}
	public void setPossibleStandards(ArrayList<String> possibleStandards) {
		this.possibleStandards = possibleStandards;
	}
	public ArrayList<Integer> getStandardCount() {
		return standardCount;
	}
	public void setStandardCount(ArrayList<Integer> standardCount) {
		this.standardCount = standardCount;
	}

}
