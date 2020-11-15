package com.urise.webapp.model;

import java.time.YearMonth;
import java.util.Objects;

public class OrgData {

    private final String orgName;
    private final YearMonth beginDate;
    private final YearMonth endDate;
    private final String desc;

    public OrgData(String orgName, YearMonth beginDate, YearMonth endDate, String desc) {
        this.orgName = orgName;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrgData orgData = (OrgData) o;

        if (!orgName.equals(orgData.orgName)) return false;
        if (!beginDate.equals(orgData.beginDate)) return false;
        if (!Objects.equals(endDate, orgData.endDate)) return false;
        return desc.equals(orgData.desc);
    }

    @Override
    public int hashCode() {
        int result = orgName.hashCode();
        result = 31 * result + beginDate.hashCode();
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + desc.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\n\t\t\t{" +
                "orgName = '" + orgName + '\'' +
                ", beginDate = " + beginDate +
                ", endDate = " + endDate +
                ", desc = '" + desc + '\'' +
                '}';
    }
}
