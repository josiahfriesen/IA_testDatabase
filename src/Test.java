/**
* date: Nov 17 2017
* purpose: This is the test class. It is a template for objects of the Test type.
*/
import java.util.ArrayList;

public class Test implements java.io.Serializable{
	
	private int unit;
	private String name;
	private int testNum;
	private int year; 
	private int marks;
	private int gradeLevel;
	private boolean summative;
	private boolean calc;
	private ArrayList<String> standards;
	private ArrayList<Question> questions;
	private boolean hasChallengeQ;
	
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTestNum() {
		return testNum;
	}
	public void setTestNum(int testNum) {
		this.testNum = testNum;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public int getGradeLevel() {
		return gradeLevel;
	}
	public void setGradeLevel(int gradeLevel) {
		this.gradeLevel = gradeLevel;
	}
	public boolean isType() {
		return summative;
	}
	public void setType(boolean type) {
		this.summative = type;
	}
	public Boolean getCalc() {
		return calc;
	}
	public void setCalc(Boolean calc) {
		this.calc = calc;
	}
	public ArrayList<String> getStandards() {
		return standards;
	}
	public void setStandards(ArrayList<String> standards) {
		this.standards = standards;
	}
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	//constructor:
	public Test(int unit, String name, int testNum, int year, int marks, int gradeLevel, boolean summative,
		boolean calc, ArrayList<String> standards, ArrayList<Question> questions) {
		
		this.unit = unit;
		this.name = name;
		this.testNum = testNum;
		this.year = year;
		this.marks = marks;
		this.gradeLevel = gradeLevel;
		this.summative = summative;
		this.calc = calc;
		this.standards = standards;
		this.questions = questions;
	}
	public boolean hasChallengeQ() {
		return hasChallengeQ;
	}
	public void setHasChallengeQ(boolean hasChallengeQ) {
		this.hasChallengeQ = hasChallengeQ;
	}
	
	
	
}
