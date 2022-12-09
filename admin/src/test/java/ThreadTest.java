import org.junit.Test;

public class ThreadTest {
    @Test
    public void test(){
        while(true){
            new Thread(new CatThread()).start();
        }
    }
}
