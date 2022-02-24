package com.example.wishservice2.domain.wish;

public enum RegionType {
    DOMESTIC("국내"), FOREIGN("국외");

    private final String description;

    RegionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
