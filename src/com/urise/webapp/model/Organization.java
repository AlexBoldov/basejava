package com.urise.webapp.model;

import java.time.YearMonth;
import java.util.List;
import java.util.Objects;

public class Organization {

    private final Link homePage;
    private final List<Experience> experience;

    public Organization(String orgName, String url, List<Experience> experience) {
        this.homePage = new Link(orgName, url);
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        return Objects.equals(experience, that.experience);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\n\t\t\t{" + homePage + " ," + experience + '}';
    }

    public static class Experience {

        private final YearMonth beginDate;
        private final YearMonth endDate;
        private final String title;
        private final String desc;

        public Experience(YearMonth beginDate, YearMonth endDate, String title, String desc) {
            Objects.requireNonNull(beginDate, "Experience.beginDate must not be null");
            Objects.requireNonNull(title, "Experience.title must not be null");
            this.beginDate = beginDate;
            this.endDate = endDate;
            this.title = title;
            this.desc = desc;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Experience that = (Experience) o;

            if (!beginDate.equals(that.beginDate)) return false;
            if (!Objects.equals(endDate, that.endDate)) return false;
            if (!title.equals(that.title)) return false;
            return Objects.equals(desc, that.desc);
        }

        @Override
        public int hashCode() {
            int result = beginDate.hashCode();
            result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
            result = 31 * result + title.hashCode();
            result = 31 * result + (desc != null ? desc.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "\n\t\t\t\t" + "beginDate = " + beginDate +
                    ", endDate = " + endDate +
                    ", title = '" + title + '\'' +
                    ", desc = '" + desc + '\'';
        }
    }
}
