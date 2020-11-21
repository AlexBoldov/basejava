package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class BulletedListSection extends AbstractSection {

    private final List<String> content;

    public BulletedListSection(List<String> content) {
        Objects.requireNonNull(content, "BulletedListSection.content must not be null");
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BulletedListSection that = (BulletedListSection) o;

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
