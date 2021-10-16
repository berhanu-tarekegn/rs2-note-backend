package com.rs2.note.user.credential;

import com.rs2.note.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {

    public UserCredential findByUser(User user);
}
