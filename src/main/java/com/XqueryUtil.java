package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.sf.saxon.xqj.SaxonXQDataSource;

public class XqueryUtil {
	public static void execute(String xquery_file) throws FileNotFoundException, XQException {
		InputStream inputStream = new FileInputStream(new File(xquery_file));
	     XQDataSource ds = new SaxonXQDataSource();
	     XQConnection conn = ds.getConnection();
	     XQPreparedExpression exp = conn.prepareExpression(inputStream);
	     XQResultSequence result = exp.executeQuery();
	     
	     while (result.next()) {
	        System.out.println(result.getItemAsString(null));
	     }
	}
}
