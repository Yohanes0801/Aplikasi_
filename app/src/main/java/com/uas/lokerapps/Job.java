package com.uas.lokerapps;

public class Job {
    private final int logo;
    private final String position;
    private final String company;
    private final String description;
    private boolean isExpanded;

    public Job(int logo, String position, String company, String description) {
        this.logo = logo;
        this.position = position;
        this.company = company;
        this.description = description;
    }

    public int getLogo() {
        return logo;
    }

    public String getPosition() {
        return position;
    }

    public String getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }

    public boolean isExpanded() {
        return isExpanded;
    }
    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

}

