package com.tarique.webportal.backend.persistence.repositories;

import com.tarique.webportal.backend.persistence.domain.backend.PasswordResetToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by tariquedev on 3/11/17.
 */
@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    @Query("select ptkn from PasswordResetToken ptkn inner join ptkn.user u where ptkn.user.id =?1")
    Set<PasswordResetToken> findAllByUserId(long userId);



}
