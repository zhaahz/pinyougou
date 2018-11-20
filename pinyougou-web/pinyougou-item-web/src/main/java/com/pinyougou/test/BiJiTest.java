package com.pinyougou.test;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/15 10:12
 * @Description: // TODO 笔记
 * @Version: 1.0
 */
public class BiJiTest {
	//为什么请求url的后缀名为*.html
	//伪静态和真静态?
//	伪静态
//	http://item.jd.com/15325.html
//	这个请求地址你以为是个html页面
//	其实他有可能发送的是后缀为html的请求,进入tomcat,返回item.ftl (伪静态)
//
//	好处:
//	SEO优化(搜索引擎优化)
//	--认为静态页面比动态页面的评分高,评分高的排名会靠前
//	百度则是竞价排名


//	真静态
//	请求的是一个真正的静态页面
//	http://item.jd.com/15325.html 进入tomcat,返回的是一个 html页面





//	freemarker使用的区别和正常的差别在于
//	视图解析器的问题
//
//			在controller层写的代码一样
//	但是视图解析器不一样,它所返回的结果存储的不一样
//					正常是返回到页面,然后存储到域中中
//	 freemarker是返回到

}
