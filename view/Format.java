package view;

public class Format {
    private Format() {
    }

    public static String formatString(String str, int n) {
        String f = "|%" + n + "s";
        return String.format(f, str);
    }

    public static String formatNumber(Number value) {
        return String.format("%,d", value);
    }
}
