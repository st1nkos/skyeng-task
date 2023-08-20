package ru.zhdanov.postApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zhdanov.postApp.entity.PostItem;

public interface PostItemRepository extends JpaRepository<PostItem,Long> {
}
