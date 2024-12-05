package decorator;

import model.Text;

public class PrefixDecorator extends Decorator {
    public PrefixDecorator(Text text) {
        super(text);
    }

    @Override
    public String transform() {
        return "_" + super.transform();
    }
}
