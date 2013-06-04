package metric.analyzer;

public class LineAnalyzer {

    private boolean commentedBlock = false;
    private boolean methodBlock = false;
    private int bracerOpened = 0;

    private boolean containsAccesModifier(String line) {
        if (line.trim().startsWith("private")
                || line.trim().startsWith("public")
                || line.trim().startsWith("protected")) {
            return true;
        }
        return false;
    }

    public boolean isAttribute(String line) {
        return containsAccesModifier(line) && line.trim().endsWith(";")
                && !line.trim().endsWith(");");
    }

    public boolean isMethodHead(String line) {
        if (containsAccesModifier(line) && line.trim().endsWith("{") && !isClass(line)) {
            bracerOpened++;
            methodBlock = true;
            return true;
        }
        return false;
    }

    public boolean isMethodBody(String line) {
        if (methodBlock) {
            containsOpenBracer(line);
            containsCloseBracer(line);
            return true;
        }
        return false;
    }

    private void containsOpenBracer(String line) {
        if (line.contains("{")) {
            bracerOpened++;
        }
    }

    private void containsCloseBracer(String line) {
        if (line.contains("}")) {
            bracerOpened--;
            endsMethodBody();
        }
    }

    private void endsMethodBody() {
        if (bracerOpened == 0) {
            methodBlock = false;
        }
    }

    public boolean isMethodBlock() {
        return methodBlock;
    }
    
    public boolean isImport(String line) {
        return line.trim().contains("import");
    }

    public boolean isClass(String line) {
        return containsAccesModifier(line) && line.trim().contains("class") && line.trim().endsWith("{");
    }

    public boolean isBlankLine(String line) {
        return line.isEmpty();
    }
    
    public boolean isCommentedLine(String line){
        return isSingleLineComment(line) || isCommentedBlock(line);
    }

    private boolean isSingleLineComment(String line) {
        return line.trim().startsWith("//") && commentedBlock == false;
    }

    private boolean isCommentedBlock(String line) {
        return startsCommentedBlock(line)
                || endsCommentedBlock(line)
                || insideCommentedBlock();
    }

    private boolean startsCommentedBlock(String line) {
        if (line.trim().startsWith("/*")) {
            commentedBlock = true;
            return true;
        }
        return false;
    }

    private boolean endsCommentedBlock(String line) {
        if (line.trim().endsWith("*/")) {
            commentedBlock = false;
            return true;
        }
        return false;
    }

    private boolean insideCommentedBlock() {
        if (commentedBlock) {
            return true;
        }
        return false;
    }

    public boolean containsReservedWord(String line) {
        String[] reservedWords = new String[]{
            "if", "else if", "else", "case",
            "foreach", "while", "for",
            "default", "continue", "catch", "return",
            "||", "&&", "?", "=="
        };
        for (String symbol : reservedWords) {
            if (line.contains(symbol)) {
                return true;
            }
        }
        return false;
    }
}
