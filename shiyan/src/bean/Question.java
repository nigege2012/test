package bean;

import java.util.ArrayList;
import java.util.List;

public class Question {
	private String code;
	private String content;
	private String stem;
	private List<String> options = new ArrayList<String>();
    private String answer;
    private String seq;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStem() {
		return stem;
	}
	public void setStem(String stem) {
		this.stem = stem;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	public void addOption(String opt) {
		options.add(opt);
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	@Override
	public String toString() {
		return code+":"+stem+":"+answer;
	}
}
