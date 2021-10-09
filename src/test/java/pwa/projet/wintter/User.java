package pwa.projet.wintter;

import javax.persistence.*;

@Table(name = "user", indexes = {
        @Index(name = "fk_user_tweet_idx", columnList = "tweetid"),
        @Index(name = "fk_user_liste1_idx", columnList = "listeid"),
        @Index(name = "fk_user_follow1_idx", columnList = "followerid"),
        @Index(name = "fk_user_conv1_idx", columnList = "convid")
})
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "pseudo", nullable = false, length = 45)
    private String pseudo;

    @Column(name = "mail", nullable = false, length = 45)
    private String mail;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tweetid", nullable = false)
    private Tweet tweetid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "convid", nullable = false)
    private Conv convid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "followerid", nullable = false)
    private Follow followerid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "listeid", nullable = false)
    private Liste listeid;

    public Liste getListeid() {
        return listeid;
    }

    public void setListeid(Liste listeid) {
        this.listeid = listeid;
    }

    public Follow getFollowerid() {
        return followerid;
    }

    public void setFollowerid(Follow followerid) {
        this.followerid = followerid;
    }

    public Conv getConvid() {
        return convid;
    }

    public void setConvid(Conv convid) {
        this.convid = convid;
    }

    public Tweet getTweetid() {
        return tweetid;
    }

    public void setTweetid(Tweet tweetid) {
        this.tweetid = tweetid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}