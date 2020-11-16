package com.urise.webapp.model;

import java.time.YearMonth;
import java.util.Objects;

public class Experience {

    private final String orgName;
    private final String link;
    private final YearMonth beginDate;
    private final YearMonth endDate;
    private final String desc;

    public Experience(String orgName, String link, YearMonth beginDate, YearMonth endDate, String desc) {
        this.orgName = orgName;
        this.link = link;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experience that = (Experience) o;

        if (!orgName.equals(that.orgName)) return false;
        if (!Objects.equals(link, that.link)) return false;
        if (!beginDate.equals(that.beginDate)) return false;
        if (!Objects.equals(endDate, that.endDate)) return false;
        return desc.equals(that.desc);
    }

    @Override
    public int hashCode() {
        int result = orgName.hashCode();
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + beginDate.hashCode();
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + desc.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\n\t\t\t{" +
                "orgName = '" + orgName + '\'' +
                ", link = '" + link + '\'' +
                ", beginDate = " + beginDate +
                ", endDate = " + endDate +
                ", desc = '" + desc + '\'' +
                '}';
    }
}
