import com.asurplus.App;
import com.asurplus.common.onlyfortest.DogThread;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableAsync
@SpringBootTest(classes = App.class)
public class ThreadPoolTest {
    @Test
    public void test(){
        while(true){
            DogThread dogThread = new DogThread();
            dogThread.say();
        }
    }
}
