package decorator;

import model.Text;

public class SuffixDecorator extends Decorator {
    public SuffixDecorator(Text text) {
        super(text);
    }

    @Override
    public String transform() {
        return super.transform() + ".decorated";
    }
}
