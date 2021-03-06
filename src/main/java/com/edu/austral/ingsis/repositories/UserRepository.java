package com.edu.austral.ingsis.repositories;

import com.edu.austral.ingsis.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  @Query(value = "select * from users", nativeQuery = true)
  List<User> findAll();

  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

  boolean existsByUsername(String username);

  @Query(value = "select * from users u where u.id in (select uf.user_id from users_followed uf where uf.followed_id = ?1)", nativeQuery = true)
  List<User> findFollowers(Long id);

  @Query(value = "select * from users u where u.id in (select lp.id from liked_posts lp where lp.liked = ?1)", nativeQuery = true)
  List<User> getUsersWhoLikedPost(Long id);

  @Query(value = "select * from users u where u.username like %?1%", nativeQuery = true)
  List<User> findByRegex(String value);
}
