package com.wilyr.crud.model;

import java.util.List;

public class Developer {
    private List<Skill> skills;
    private Account account;

    public Developer(List<Skill> skills, Account account) {
        this.skills = skills;
        this.account = account;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


}
