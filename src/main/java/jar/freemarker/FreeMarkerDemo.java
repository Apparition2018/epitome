package jar.freemarker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class FreeMarkerDemo {
	private FreeMarkerUtil util = FreeMarkerUtil.getInstance("/WEB-INF/ftl");
	private Map<String, Object> params = new HashMap<String, Object>();
	private String fn = "T:/";

	@Before
	public void setUp() {
		params.put("username", "小张");
	}

	@Test
	public void test01() {
		Employer emp = new Employer(1, "小李", 17);
		List<Employer> emps = Arrays.asList(new Employer(1, "小李", 22), new Employer(2, "八戒", 444),
				new Employer(3, "刘德华", 54));
		params.put("emp", emp);
		params.put("emps", emps);
		util.sprint(params, "01.ftl");
		util.fprint(params, "01.ftl", fn + "test01.ftl");
	}

	@Test
	public void test02() {
		Employer emp = new Employer(1, "小李", 17);
		List<Employer> emps = Arrays.asList(new Employer(1, "小李", 22), new Employer(2, "八戒", 444),
				new Employer(3, "刘德华", 54));
		params.put("emp", emp);
		params.put("emps", emps);
		util.sprint(params, "01.htm");
		util.fprint(params, "01.htm", fn + "test02.htm");
	}

	@Test
	public void test03() {
		Employer emp = new Employer(1, "小李", 17);
		List<Employer> emps = Arrays.asList(new Employer(1, "小李", 22), new Employer(2, "八戒", 444),
				new Employer(3, "刘德华", 54));
		params.put("emp", emp);
		params.put("emps", emps);
		util.sprint(params, "01.jsp");
		util.fprint(params, "01.jsp", fn + "test03.jsp");
	}

}
