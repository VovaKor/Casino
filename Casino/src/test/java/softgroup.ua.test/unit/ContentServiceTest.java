package softgroup.ua.test.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.jpa.content.Content;
import softgroup.ua.repository.UserRepository;
import softgroup.ua.repository.content.ContentRepository;
import softgroup.ua.service.content.ContentService;

import java.util.Date;
import java.util.List;

/**
 * Created by Vlad on 05.03.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContentServiceTest {
/*    @Autowired
    public ContentService contentService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ContentRepository contentRepository;

    @Test
    public void testCriteriaSearch(){
        UserEntity user = userRepository.findOne("admin");
        List<Content> list  = contentRepository.findByAuthor(user);

        for(Content content : list){
            System.out.println(content.getDate());
        }

    }

    @Test
    public void testUpdate() {

//        contentRepository.updateText(2l, "new text");
//        contentRepository.updateDescription(2l, "new dis");

    }

    @Test
    public void test() {
        UserEntity user = userRepository.findOne("admin");

        Content content = new Content();
        content.setAuthor(user);
        content.setDate(new Date());
        content.setDescription("dis");
        content.setText("text");
      //  content.setContentId(1l);
//        contentRepository.save(content);
        contentService.save(content);
//
        List<Content> list = contentService.findAll();      Content content1 = contentService.findOne(1l);
//        System.out.println(content1.getDate());
//        contentService.delete(1l);
        for(Content content2:list ){
            System.out.println(content2.getContentId());
        }

    }

    */

}
