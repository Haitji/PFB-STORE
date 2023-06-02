package com.kreitek.store.infrastructure.persistence;

import com.kreitek.store.domain.entity.Category;
import com.kreitek.store.domain.entity.Item;
import com.kreitek.store.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByNick(String nick);
}
