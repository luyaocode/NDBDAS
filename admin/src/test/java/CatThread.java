import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CatThread implements Runnable{
    @SneakyThrows
    @Override
    public void run() {
        log.info("喵喵");
        Thread.sleep(100000000);
    }
}
