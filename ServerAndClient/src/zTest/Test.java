package zTest;

import overall.Builder;
import overall.Diector;
import overall.XMLUtil;


public class Test {

	public static void main(String[] args) {
		try {
			// spmething I want to change
			Diector d = new Diector();
			d.setBuilder((Builder) XMLUtil.getBean("Server"));
			d.construct();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
