package spring.data.elasticsearch.bean;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @ClassName: Test2
 * @Description: TODO
 * @author: xf.sui
 * @date: 2016年8月8日 上午9:29:54
 */
@Document(indexName = "test2")
public class Test2 {

	private String id;
	private String column1;
	private String column2;
	private String column3;
	private String column4;
	private String column5;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}

	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}

	public static void main(String[] args) {
	}
}
