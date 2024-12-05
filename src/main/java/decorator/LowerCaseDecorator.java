package decorator;

import model.Text;

public class LowerCaseDecorator extends Decorator {
    public LowerCaseDecorator(Text text) {
        super(text);
    }

    @Override
    public String transform() {
        return super.transform().toLowerCase();
    }
}
