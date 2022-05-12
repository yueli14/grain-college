import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.Test;

/**
 * @Classname DateTest
 * @Description TODO
 * @Date 2022/5/5 16:36
 * @Created by 28327
 */

public class DateTest {
    @Test
    public void one(){
        DateTime date = DateUtil.date();
        System.out.println(date.toString());
        System.out.println(System.currentTimeMillis());
    }
}