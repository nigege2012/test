package action;

import java.util.ArrayList;
import model.Student;
import com.opensymphony.xwork2.ActionSupport;
import db.studentDAO;

public class StudentAction extends ActionSupport {
	private ArrayList<Student> stuList;
	private studentDAO sd;
	private int count;
	
	public  StudentAction(){
		 sd=new studentDAO();
	}
	
	public ArrayList<Student> getStuList() {
		return stuList;
	}
	
	public String getAllStudent(){
		this.stuList=sd.getStudentList();
		this.count=stuList.size();
		return SUCCESS;
	}
	
	public int getCount() {
		return count;
	}
}
