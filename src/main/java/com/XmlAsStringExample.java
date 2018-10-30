package com;

import java.io.StringReader;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.InputSource;

import net.sf.saxon.Configuration;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.XQueryCompiler;
import net.sf.saxon.s9api.XQueryEvaluator;
import net.sf.saxon.s9api.XQueryExecutable;
import net.sf.saxon.s9api.XdmDestination;

public class XmlAsStringExample {
public static void main(String[] args) {
    try {
        final String tableXml = 
          "<table>" + 
          "  <columns>" + 
          "    <column>Foo</column><column>Bar</column>" + 
          "  </columns>" + 
          "  <rows>" + 
          "    <row><cell>Foo1</cell><cell>Bar1</cell></row>" + 
          "    <row><cell>Foo2</cell><cell>Bar2</cell></row>" + 
          "  </rows>" + 
          "</table>";

        Configuration saxonConfig = new Configuration();
        Processor processor = new Processor(saxonConfig);

        XQueryCompiler xqueryCompiler = processor.newXQueryCompiler();
        XQueryExecutable xqueryExec = xqueryCompiler
                .compile("<result>{"
                         + "/table/rows/row/cell"
                         + "}</result>");

        XQueryEvaluator xqueryEval = xqueryExec.load();
        xqueryEval.setSource(new SAXSource(new InputSource(
            new StringReader(tableXml))));

        XdmDestination destination = new XdmDestination();

        xqueryEval.setDestination(destination);

        // Avert your eyes!
        xqueryEval.setURIResolver(new URIResolver() {
          @Override
          public Source resolve(String href, String base) throws TransformerException {
              return new StreamSource(new StringReader(tableXml));
          }
        });

        xqueryEval.run();

        System.out.println(destination.getXdmNode());

      } catch (Exception e) {
        e.printStackTrace();
      }
}
}
