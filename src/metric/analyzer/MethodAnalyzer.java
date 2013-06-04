package metric.analyzer;

import metric.stats.MethodMetrics;

public class MethodAnalyzer {

    private LineAnalyzer lineAnalyzer;
    private MethodMetrics methodMetrics;

    public MethodAnalyzer() {
        lineAnalyzer = new LineAnalyzer();
        methodMetrics = new MethodMetrics();
    }

    public void analyzeMethodLine(String line) {
        methodMetrics.addCodeLine(line);
        if (lineAnalyzer.isMethodHead(line)) {
            analyzeHead(line);
        } else {
            analyzeBody(line);
        }
    }

    public MethodMetrics getMethodMetrics() {
        return methodMetrics;
    }

    private void extractParameters(String line) {
        if (!hasParameters(line)) {
            String params[] = line.substring(line.indexOf("(") + 1, line.indexOf(")")).split(",");
            for (String parameter : params) {
                methodMetrics.addParameter(parameter);
            }
        }
    }

    private boolean hasParameters(String line) {
        return line.contains("()");
    }

    private void analyzeHead(String line) {
        methodMetrics.increaseEffectiveLines();
        methodMetrics.setMethodName(line.substring(line.indexOf("p"), line.indexOf("(")).trim());
        extractParameters(line);
    }

    private void analyzeBody(String line) {
        if (lineAnalyzer.isBlankLine(line)) {
            methodMetrics.increaseBlankLines();
        } else {
            if (lineAnalyzer.isCommentedLine(line)) {
                methodMetrics.increaseCommentedLines();
            } else {
                analyzeEffectiveLine(line);
            }
        }
    }

    private void analyzeEffectiveLine(String line) {
        methodMetrics.increaseEffectiveLines();
        if (lineAnalyzer.containsReservedWord(line)) {
            methodMetrics.increaseCiclomaticComplexity();
        }
    }
}
