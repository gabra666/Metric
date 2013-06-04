package metric.analyzer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassAnalyzerTest {

    private ClassAnalyzer classAnalyzer;

    @Before
    public void initialize() {
        classAnalyzer = new ClassAnalyzer();
    }
    
    @Test
    public void className(){
        classAnalyzer.analyzeClassLine("public class ClassAnalyzerTest {");
        Assert.assertEquals("public class ClassAnalyzerTest", classAnalyzer.getClassMetrics().getClassName());
    }
    @Test
    public void numberOfMethods(){
        classAnalyzer.analyzeClassLine("public void method(){");
        classAnalyzer.analyzeClassLine("classAnalyzer.analyzeClassLine(\"public class ClassAnalyzerTest \");");
        classAnalyzer.analyzeClassLine("}");
        Assert.assertEquals(1, classAnalyzer.getClassMetrics().getNumberOfMethods());
    }
    @Test
    public void numberOfEffectiveLines(){
        classAnalyzer.analyzeClassLine("public void method(){");
        classAnalyzer.analyzeClassLine("classAnalyzer.analyzeClassLine(\"public class ClassAnalyzerTest \");");
        classAnalyzer.analyzeClassLine("}");
        Assert.assertEquals(3, classAnalyzer.getClassMetrics().getEffectiveLines());
    }
}