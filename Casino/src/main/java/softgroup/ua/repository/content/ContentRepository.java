package softgroup.ua.repository.content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.jpa.content.Content;

import java.util.List;

/**
 * Created by Vlad on 05.03.2017.
 */
public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query("SELECT c FROM Content c WHERE c.author=:author")
    public List<Content> findByAuthor(@Param("author") UserEntity author);

    @Modifying
    @Transactional
    @Query("update Content c SET c.text=:text where c.contentId=:contentId")
    public void updateText(@Param("contentId") Long contentId, @Param("text") String text);

    @Modifying
    @Transactional
    @Query("update Content c SET c.description=:description where c.contentId=:contentId")
    public void updateDescription(@Param("contentId") Long contentId, @Param("description") String description);
}
