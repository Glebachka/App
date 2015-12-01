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


    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotEmpty
    @Column(name = "LINK", unique = true, nullable = false)
    private String link;

    @NotEmpty
    @Column(name = "DESCRIPTION", nullable = false)
    private String linkDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;

        Link link1 = (Link) o;

        if (getLinkId() != link1.getLinkId()) return false;
        if (!getLink().equals(link1.getLink())) return false;
        return !(getLinkDescription() != null ? !getLinkDescription().equals(link1.getLinkDescription()) : link1.getLinkDescription() != null);

    }

    @Override
    public int hashCode() {
        int result = getLinkId();
        result = 31 * result + getLink().hashCode();
        result = 31 * result + (getLinkDescription() != null ? getLinkDescription().hashCode() : 0);
        return result;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLink() {
        return link;

    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkDescription() {
        return linkDescription;
    }

    public void setLinkDescription(String linkDescription) {
        this.linkDescription = linkDescription;
    }

}
