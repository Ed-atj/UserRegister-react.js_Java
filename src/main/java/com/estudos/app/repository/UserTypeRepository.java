package com.estudos.app.repository;

import com.estudos.app.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long>{

}
