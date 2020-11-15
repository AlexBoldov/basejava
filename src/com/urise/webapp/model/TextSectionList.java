package com.urise.webapp.model;

import java.util.List;

public class TextSectionList extends Section {

    private final List<String> content;

    public TextSectionList(List<String> content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSectionList that = (TextSectionList) o;

        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        return " " + content;
    }
}
