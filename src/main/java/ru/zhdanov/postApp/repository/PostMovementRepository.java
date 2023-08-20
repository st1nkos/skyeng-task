package ru.zhdanov.postApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhdanov.postApp.entity.PostMovement;

import java.util.List;

@Repository
public interface PostMovementRepository extends JpaRepository<PostMovement,Long> {
    List<PostMovement> findByPostItemIdOrderByMovementDate(long id);
}
