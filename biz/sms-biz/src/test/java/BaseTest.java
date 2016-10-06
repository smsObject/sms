/**
 * Created by user on 2016/10/6.
 */
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public abstract class BaseTest implements ApplicationContextAware {

}