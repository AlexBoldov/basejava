package com.urise.webapp.model;

import java.util.List;

public class OrgDataSectionList extends Section {

    private final List<OrgData> content;

    public OrgDataSectionList(List<OrgData> content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrgDataSectionList that = (OrgDataSectionList) o;

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
