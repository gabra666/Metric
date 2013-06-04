package metric.stats;

import java.util.ArrayList;
import java.util.List;

public class ClassMetrics {

    private String className;
    private int blankLines;
    private int commentedLines;
    private int effectiveLines;
    private List<String> classCode;
    private List<String> attributeList;
    private List<MethodMetrics> methodList;

    public ClassMetrics() {
        classCode = new ArrayList<>();
        attributeList = new ArrayList<>();
        methodList = new ArrayList<>();
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void increaseBlankLines() {
        blankLines++;
    }

    public int getBlankLines() {
        for (MethodMetrics method : methodList) {
            blankLines += method.getBlankLines();
        }
        return blankLines;
    }

    public void increaseCommentedLines() {
        commentedLines++;
    }

    public int getCommentedLines() {
        for (MethodMetrics method : methodList) {
            commentedLines += method.getCommentedLines();
        }
        return commentedLines;
    }

    public void increaseEffectiveLines() {
        blankLines++;
    }

    public int getEffectiveLines() {
        for (MethodMetrics method : methodList) {
            effectiveLines += method.getEffectiveLines();
        }
        return effectiveLines;
    }

    public void addCodeLine(String line) {
        classCode.add(line);
    }

    public List<String> getClassCode() {
        return classCode;
    }

    public void addAttribute(String attribute) {
        attributeList.add(attribute);
    }

    public List<String> getAttributeList() {
        return attributeList;
    }

    public int getNumberOfAttributes() {
        return attributeList.size();
    }

    public void addMethod(MethodMetrics methodMetrics) {
        methodList.add(methodMetrics);
    }

    public List<MethodMetrics> getMethodList() {
        return methodList;
    }

    public int getNumberOfMethods() {
        return methodList.size();
    }

    public double getLackOfCohesion() {
        return calculateLackOfCohesion();
    }

    private double calculateLackOfCohesion() {
        return 1 - (sumMf() / (getNumberOfMethods() * getNumberOfAttributes()));
    }

    private double sumMf() {
        double total = 0;
        for (MethodMetrics method : methodList) {
            for (String attribute : attributeList) {
                if (method.isAttributeUsed(attribute)) {
                    total++;
                }
            }
        }
        return total;
    }
}
