package decorator;

import model.Text;

public class UpperCaseDecorator extends Decorator {
    public UpperCaseDecorator(Text text) {
        super(text);
    }

    @Override
    public String transform() {
        return super.transform().toUpperCase();
    }
}
