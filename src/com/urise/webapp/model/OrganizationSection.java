package com.urise.webapp.model;

import java.util.List;

public class OrganizationSection extends Section {

    private final List<Experience> content;

    public OrganizationSection(List<Experience> content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

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
