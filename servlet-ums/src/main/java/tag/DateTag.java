package tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * 数据库工具类
 * @author 彼得·潘
 */
public class DateTag extends SimpleTagSupport{

	private String pattern;
	
	
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}




	@Override
	public void doTag() throws JspException, IOException {
		/*
		 * 通过继承自SimpleTagSupport提供的
		 * getJspContext方法来获得pageContext
		 */
		//PageContext pc = (PageContext)getJspContext();
		
		/*
		 * PageContext提供了获得其他所有隐含对象的方法
		 */
		//JspWriter out = pc.getOut();
		

		PageContext pc = (PageContext)getJspContext();
		JspWriter out = pc.getOut();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		out.println(sdf.format(date));
		
		
	}

	
}
