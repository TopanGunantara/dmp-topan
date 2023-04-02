package dmp.test.topan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dmp.test.topan.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
    Users findByUsername(String username);
}
