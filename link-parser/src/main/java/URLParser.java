import ru.tinkoff.edu.java.parsers.LinkParser;


public class URLParser {

    public String parseLink(String link) {
        @SuppressWarnings("unchecked") Class<LinkParser>[] d = (Class<LinkParser>[]) LinkParser.class.getPermittedSubclasses();
        for (Class<LinkParser> linkParserClass : d) {
            String result = null;
            try {
                result = linkParserClass.getDeclaredConstructor().newInstance().parseLink(link);
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}
