package action;

import java.util.ArrayList;
import java.util.List;




import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Question;
import db.questionDAO;


public class QuestionManager extends ActionSupport {

	private List<Question> questions = new ArrayList<Question>();
	private static questionDAO qu;
	private int p = 1; // p变量存储当前页码，初始值设置为1
	private int lastpage; // 总页数
	private int pageS = 0; // 每页显示?条记录
	private int number = 0;

	public int getPageS() {
		return pageS;
	}

	public void setPageS(int pageS) {
		this.pageS = pageS;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getLastpage() {
		return lastpage;
	}

	public void setLastpage(int lastpage) {
		this.lastpage = lastpage;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public static int[] randomCommon(int min, int max, int n) { // 生成随机机数
		if (n > (max - min + 1) || max < min) {
			return null;
		}
		int[] result = new int[n];
		int count = 0;
		while (count < n) {
			int num = (int) (Math.random() * (max - min)) + min;
			boolean flag = true;
			for (int j = 0; j < n; j++) {
				if (num == result[j]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				result[count] = num;
				count++;
			}
		}
		return result;
	}

	public String browse() throws Exception {
		// 从数据库中读取，并放入
		int[] randomNumber = randomCommon(0, qu.getId().size(), 5);

		for (int i = 1; i <= 5; i++) {

			this.questions.addAll(qu.getQuestion((int) qu.getId().get(randomNumber[i - 1]), i));
		}
		return SUCCESS;
	}

	public String save() throws Exception {
		// 写入数据库
//		for (int i = 0; i < questions.size(); i++) {
//			System.out.println(questions.get(i));
//		}
		
//		
//		System.out.println(questions.get(1).getCode());
//		System.out.println(x.getStem());
//		System.out.println(x.getAnswer());
		for (int i = 0; i < questions.size(); i++) {
			Question x=questions.get(i);
		qu.setAnswer(x.getCode(), x.getStem(), x.getAnswer());
		}
		return SUCCESS;
	}

	public String getAllQuestion() {
//		 pageS = 5; // 每页显示5条记录
//		 number = 50;
		if (pageS != 0 && number != 0) {
			ActionContext.getContext().getSession().put("pageS", pageS);
			ActionContext.getContext().getSession().put("number", number);
		} else {
			pageS = (int) ActionContext.getContext().getSession().get("pageS");
			number = (int) ActionContext.getContext().getSession().get("number");
			if (pageS == 0 || number == 0) {
				return ERROR;
			}
		}

		int[] randomNumber = randomCommon(0, qu.getId().size(), number + 10);

		for (int i = 1; i <= number + 10; i++) {

			this.questions.addAll(qu.getQuestion((int) qu.getId().get(randomNumber[i - 1]), i));
		}
//		this.questions=qu.getQuestion();
//		int listLength=this.questions.size();
		if (number % pageS == 0) {
			this.lastpage = number / pageS;
		} else {
			this.lastpage = number / pageS + 1;
		}
		if (this.p < 1)
			this.p = 1;
		if (this.p > this.lastpage)
			this.p = this.lastpage;

		int startIndex = (this.p - 1) * pageS;
		int endIndex = startIndex + pageS;

		if (endIndex >= number)
			endIndex = number;
		this.questions = this.questions.subList(startIndex, endIndex);
		return SUCCESS;
	}

//public static void main(String[] args) {
//	int i=(int) qu.getId().size();
//	System.out.println(i);
//}
}
