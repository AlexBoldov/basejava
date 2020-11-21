package com.urise.webapp.model;

import java.util.Objects;

public class SingleTextSection extends AbstractSection {

    private final String content;

    public SingleTextSection(String content) {
        Objects.requireNonNull(content, "SingleTextSection.content must not be null");
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleTextSection that = (SingleTextSection) o;

        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        return " '" + content + '\'';
    }
}
