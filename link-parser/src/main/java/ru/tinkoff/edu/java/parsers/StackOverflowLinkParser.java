package ru.tinkoff.edu.java.parsers;


public record StackOverflowLinkParser() implements LinkParser {
    @Override
    public String parseLink(String link) {
        if (link.startsWith("https://stackoverflow.com/questions/")) {
            String[] parts = link.split("/");
            if (parts.length == 6 && isNumeric(parts[4])) {
                return parts[4];
            }
        }
        return null;
    }

    private boolean isNumeric(String string) {
        boolean isOnlyDigits = string.length() > 0;
        for (int i = 0; i < string.length() && isOnlyDigits; i++) {
            if (!Character.isDigit(string.charAt(i))) {
                isOnlyDigits = false;
            }
        }
        return isOnlyDigits;
    }
}
