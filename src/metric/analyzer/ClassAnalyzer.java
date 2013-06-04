package metric.analyzer;

import metric.stats.ClassMetrics;

public class ClassAnalyzer {

    private LineAnalyzer lineAnalyzer;
    private ClassMetrics classMetrics;
    private MethodAnalyzer methodAnalyzer;

    public ClassAnalyzer() {
        lineAnalyzer = new LineAnalyzer();
        classMetrics = new ClassMetrics();
        methodAnalyzer = new MethodAnalyzer();
    }

    public void analyzeClassLine(String line) {
        classMetrics.addCodeLine(line);
        if (lineAnalyzer.isClass(line)) {
            extractClassName(line);
        } else {
            analyzeBody(line);
        }
    }

    public ClassMetrics getClassMetrics() {
        return classMetrics;
    }

    private String getAttributeName(String line) {
        return line.substring(line.lastIndexOf(" ") + 1, line.indexOf(";"));
    }

    private void extractClassName(String line) {
        classMetrics.setClassName(line.substring(line.indexOf("p"), line.indexOf("{")).trim());
    }

    private void analyzeLine(String line) {
        if (lineAnalyzer.isAttribute(line)) {
            classMetrics.addAttribute(getAttributeName(line));
        } else {
            if (lineAnalyzer.isBlankLine(line)) {
                classMetrics.increaseBlankLines();
            } else {
                if (lineAnalyzer.isCommentedLine(line)) {
                    classMetrics.increaseCommentedLines();
                }
            }
        }
    }

    private void analyzeNewMethod(String line) {
        methodAnalyzer = new MethodAnalyzer();
        methodAnalyzer.analyzeMethodLine(line);
    }

    private void checkForEndingMethod(String line) {
        if (lineAnalyzer.isMethodBody(line)) {
            classMetrics.addMethod(methodAnalyzer.getMethodMetrics());
        }
    }

    private void classifyLine(String line) {
        if (lineAnalyzer.isMethodBody(line)) {
            methodAnalyzer.analyzeMethodLine(line);
            checkForEndingMethod(line);
        } else {
            analyzeLine(line);
        }
    }

    private void analyzeBody(String line) {
        if (lineAnalyzer.isMethodHead(line)) {
            analyzeNewMethod(line);
        } else {
            classifyLine(line);
        }
    }
}
