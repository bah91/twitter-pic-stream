package com.bah91;

import com.bah91.services.StreamLoader;
import org.junit.Test;

/**
 * Created by bah91 on 29/07/17.
 */
public class StreamLoaderTests {


    @Test
    public void Test_Remove_URL() {
        StreamLoader streamLoader = new StreamLoader();
        System.out.println(streamLoader
                .extractText("#BK2018 http://blahbalg,com lookin good!"));
    }


}
