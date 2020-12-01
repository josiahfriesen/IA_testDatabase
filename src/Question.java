/**
* date: Nov 17 2017
* purpose: This is the question class. It is a template for objects of the Question type. Question objects are 
* the smallest objects used in this application. Units and tests are both comprised of collections of this object type. 
*/
public class Question implements java.io.Serializable{
	
	private String unit;
	private int year;
	private int marks;
	private String question;
	private boolean challenge;
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
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
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	//constructor:
	public Question(String Qunit, int Qyear, int Qmarks, String QQuestion, boolean Qchallenge) {
	    unit = Qunit;
	    year = Qyear;
	    marks = Qmarks;
	    question = QQuestion;
	    challenge = Qchallenge;
	}
	public boolean isChallenge() {
		return challenge;
	}
	public void setChallenge(boolean challenge) {
		this.challenge = challenge;
	}
}
