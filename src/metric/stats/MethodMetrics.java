package metric.stats;

import java.util.ArrayList;
import java.util.List;

public class MethodMetrics {

    private String methodName;
    private int blankLines;
    private int commentedLines;
    private int effectiveLines;
    private int ciclomaticComplexity;
    private List<String> methodCode;
    private List<String> parameterList;

    public MethodMetrics() {
        methodCode = new ArrayList<>();
        parameterList = new ArrayList<>();
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void increaseBlankLines() {
        blankLines++;
    }

    public int getBlankLines() {
        return blankLines;
    }

    public void increaseCommentedLines() {
        commentedLines++;
    }

    public int getCommentedLines() {
        return commentedLines;
    }

    public void increaseEffectiveLines() {
        effectiveLines++;
    }

    public int getEffectiveLines() {
        return effectiveLines;
    }

    public void increaseCiclomaticComplexity() {
        ciclomaticComplexity++;
    }

    public int getCiclomaticComplexity() {
        return ciclomaticComplexity;
    }

    public void addCodeLine(String line) {
        methodCode.add(line);
    }

    public List<String> getMethodCode() {
        return methodCode;
    }

    public void addParameter(String parameter) {
        parameterList.add(parameter);
    }

    public List<String> getParameterList() {
        return parameterList;
    }

    public int getNumberOfParameters() {
        return parameterList.size();
    }

    public boolean isAttributeUsed(String attribute) {
        return isAttributeInCode(checkParameters(attribute));
    }

    private String checkParameters(String attribute) {
        if (isAttributeInParameters(attribute)) {
            attribute = "this." + attribute;
        }
        return attribute;
    }

    private boolean isAttributeInParameters(String attribute) {
        for (String parameter : parameterList) {
            if (parameter.contains(attribute)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAttributeInCode(String attribute) {
        for (String codeLine : methodCode) {
            if (codeLine.contains(attribute)) {
                return true;
            }
        }
        return false;
    }
}
