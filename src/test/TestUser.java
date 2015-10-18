import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by charles on 2015/10/4.
 */
public class TestUser extends Object
{
    public class User
    {
        public int a;
        public int b;
        public User(int a, int b)
        {
            this.a = a;
            this.b = b;
        }
    }

    @Test
    public void test1()
    {
        List<Object> ls = new ArrayList<Object>();

        User user = new User( 1 , 1);
        for(int i=0; i<10;i++)
        {
            user = new User( i , i);
            ls.add(user);
        }

        for(int i=0;i<ls.size();i++)
        {
            User elem = (User)ls.get(i);
            System.out.println( elem.a + ":" + elem.b);
        }
    }
}
