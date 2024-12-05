import decorator.LowerCaseDecorator;
import decorator.PrefixDecorator;
import decorator.SuffixDecorator;
import decorator.UpperCaseDecorator;
import model.Text;
import model.TextImpl;

public class Main {
    public static void main(String[] args) {
        Text text = new TextImpl("Coucou");
        System.out.println(text.transform());

        UpperCaseDecorator upperDecorator = new UpperCaseDecorator(text);
        System.out.println(upperDecorator.transform());

        LowerCaseDecorator lowerDecorator = new LowerCaseDecorator(upperDecorator);
        System.out.println(lowerDecorator.transform());

        PrefixDecorator prefixDecorator = new PrefixDecorator(lowerDecorator);
        System.out.println(prefixDecorator.transform());

        SuffixDecorator suffixDecorator = new SuffixDecorator(prefixDecorator);
        System.out.println(suffixDecorator.transform());
    }
}
