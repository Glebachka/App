package com.saienko.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by gleb on 26.11.2015.
 */
@Entity
@Table(name = "LINK")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "LINK_ID")
    private int linkId;

    @Column(name = "USER_ID", nullable = false)
    private int userId;

    @NotEmpty
    @Column(name = "LINK", unique = true, nullable = false)
    private String Link;

    @NotEmpty
    @Column(name = "DESCRIPTION", nullable = false)
    private String LinkDescription;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;

        Link link = (Link) o;

        if (getLinkId() != link.getLinkId()) return false;
        if (getUserId() != link.getUserId()) return false;
        if (!getLink().equals(link.getLink())) return false;
        return getLinkDescription().equals(link.getLinkDescription());

    }

    @Override
    public int hashCode() {
        int result = getLinkId();
        result = 31 * result + getUserId();
        result = 31 * result + getLink().hashCode();
        result = 31 * result + getLinkDescription().hashCode();
        return result;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getLinkDescription() {
        return LinkDescription;
    }

    public void setLinkDescription(String linkDescription) {
        LinkDescription = linkDescription;
    }

}
