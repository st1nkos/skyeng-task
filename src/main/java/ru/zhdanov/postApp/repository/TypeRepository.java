package ru.zhdanov.postApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhdanov.postApp.entity.Type;
@Repository
public interface TypeRepository extends JpaRepository<Type,Integer> {

    Type findByName(String name);

}
