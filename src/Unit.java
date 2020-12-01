/**
* date: Nov 17 2017
* purpose: This is the unit class. It is a template for objects of the Unit type.
*/
import java.util.ArrayList;

public class Unit implements java.io.Serializable{
	private String name;
	private int index;
	private ArrayList<Question> questions;
	
	public int getIndex() {
		return index;
	}
	
	public ArrayList getQuestions() {
		return questions;
	}
	
	public void addQuestion(Question question) {
		questions.add(question);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//constructor:
	public Unit(String Uname, int Uindex, ArrayList<Question> Uquestions) {
		name = Uname;
		index = Uindex;
		questions = Uquestions;
	}
	
//
	
}
