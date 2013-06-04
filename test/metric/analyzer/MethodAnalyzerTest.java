package metric.analyzer;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class MethodAnalyzerTest {

    MethodAnalyzer methodAnalyzer;

    @Before
    public void initialize() {
        methodAnalyzer = new MethodAnalyzer();
    }

    @Test
    public void blankLines() {
        methodAnalyzer.analyzeMethodLine("");
        methodAnalyzer.analyzeMethodLine("");
        Assert.assertEquals(2, methodAnalyzer.getMethodMetrics().getBlankLines());
    }

    @Test
    public void commentedLines() {
        methodAnalyzer.analyzeMethodLine("// hola que hace");
        methodAnalyzer.analyzeMethodLine("// segundo coment");
        Assert.assertEquals(2, methodAnalyzer.getMethodMetrics().getCommentedLines());
    }

    @Test
    public void commentedBlockLines() {
        methodAnalyzer.analyzeMethodLine("/* hola que hace");
        methodAnalyzer.analyzeMethodLine("* segundo coment");
        methodAnalyzer.analyzeMethodLine("* tercer coment");
        methodAnalyzer.analyzeMethodLine("* cuarto coment */");
        Assert.assertEquals(4, methodAnalyzer.getMethodMetrics().getCommentedLines());
    }

    @Test
    public void effectiveBlockLines() {
        methodAnalyzer.analyzeMethodLine("/* hola que hace");
        methodAnalyzer.analyzeMethodLine("* segundo coment");
        methodAnalyzer.analyzeMethodLine("* tercer coment");
        methodAnalyzer.analyzeMethodLine("* cuarto coment */");
        Assert.assertEquals(0, methodAnalyzer.getMethodMetrics().getEffectiveLines());
    }

    @Test
    public void methodName() {
        methodAnalyzer.analyzeMethodLine("public void hola(){");
        Assert.assertEquals("public void hola", methodAnalyzer.getMethodMetrics().getMethodName());
    }

    @Test
    public void methodParameters() {
        methodAnalyzer.analyzeMethodLine("public void hola( String param, int param){");
        Assert.assertEquals(2, methodAnalyzer.getMethodMetrics().getNumberOfParameters());
        System.out.print("lala");
    }

    @Test
    public void ciclomaticComplexity() {
        methodAnalyzer.analyzeMethodLine("public void hola(){");
        methodAnalyzer.analyzeMethodLine("if(){");
        methodAnalyzer.analyzeMethodLine("} else {");
        methodAnalyzer.analyzeMethodLine("} for");
        Assert.assertEquals(3, methodAnalyzer.getMethodMetrics().getCiclomaticComplexity());
    }
}