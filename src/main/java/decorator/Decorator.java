package decorator;

import model.Text;

public abstract class Decorator implements Text {
    protected Text text;

    public Decorator(Text text) {
        this.text = text;
    }

    @Override
    public String transform() {
        return text.transform();
    }
}
