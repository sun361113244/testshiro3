package testReflection;

import org.apache.mahout.common.ClassUtils;
import org.junit.Test;

/**
 * Created by charles on 16/9/14.
 */
public class testReflection
{
    @Test
    public void test1()
    {
//        ItemSimilarity similarity = ClassUtils.instantiateAs(PearsonCorrelationSimilarity.class, ItemSimilarity.class);
        Animal dog1 = ClassUtils.instantiateAs(dog.class ,Animal.class);

        dog1.jiao();
    }
}
