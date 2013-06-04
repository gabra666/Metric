
package metric.analyzer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class LineAnalyzerTest {

    private LineAnalyzer lineAnalyzer;
    @Before 
    public void initialize(){
        lineAnalyzer = new LineAnalyzer();
    }
    
    @Test
    public void isMethod(){
        lineAnalyzer.isMethodHead("private void extractParameters(String line) {");
        lineAnalyzer.isMethodBody(" if (!hasParameters(line)) {");
        Assert.assertTrue(lineAnalyzer.isMethodBody("}"));
    }
    
    @Test
    public void notIsMethod(){
        lineAnalyzer.isMethodHead("private void extractParameters(String line) {");
        lineAnalyzer.isMethodBody(" if (!hasParameters(line)) {");
        lineAnalyzer.isMethodBody(" }");
        lineAnalyzer.isMethodBody(" }");
        Assert.assertFalse(lineAnalyzer.isMethodBody(""));
    }
    
}