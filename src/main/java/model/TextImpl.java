package model;

public class TextImpl implements Text{
    private final String content;

    public TextImpl(String content) {
        this.content = content;
    }

    @Override
    public String transform() {
        return content;
    }
}
