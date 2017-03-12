package com.tarique.webportal.backend.persistence.domain.backend;

import com.tarique.webportal.backend.persistence.converter.LocalDateTimeAttributeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by tariquedev on 3/4/17.
 */
@Entity
public class PasswordResetToken implements Serializable {
    /**
     * Serial version id for serializable classes
     */
    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_TOKEN_IN_MINUTES=60;

    /* The Application Logger */
        private static final Logger LOG = LoggerFactory.getLogger(PasswordResetToken.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String token;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "expiry_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime expiryTime;

    /**
     * Default Constructor
     */
    public PasswordResetToken() {
    }

    /**
     *
     * @param token the unique token
     * @param user  the user
     * @param creationTime
     * @param expInMinutes
     */
    public PasswordResetToken(String token, User user, LocalDateTime creationTime, int expInMinutes) {
        if((null == token) || (null == user) || (null == creationTime)){
            throw new IllegalArgumentException("Token, User or creation time Time can not be null");
        }
        if(expInMinutes == 0){
            LOG.warn("Expiration in Minutes can not be zero");
            expInMinutes = DEFAULT_TOKEN_IN_MINUTES;
        }
        this.token = token;
        this.user = user;
        this.expiryTime = creationTime.plusMinutes(expInMinutes);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordResetToken that = (PasswordResetToken) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
