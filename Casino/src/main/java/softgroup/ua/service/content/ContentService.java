package softgroup.ua.service.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import softgroup.ua.jpa.content.Content;
import softgroup.ua.repository.content.ContentRepository;
import softgroup.ua.service.GeneralServiceImp;

/**
 * Created by Vlad on 05.03.2017.
 */
@Service("contentService")
public class ContentService extends GeneralServiceImp<Content,Long> {

    @Autowired
    private ContentRepository contentRepository;
    @Override
    public JpaRepository<Content, Long> getRepository() {
        return this.contentRepository;
    }
}
