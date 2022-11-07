package com.booktree;

import static org.junit.Assert.*;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.booktree.API.APIClient;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class GetBookInfoTest {
  @Test
  public void isAPIValid() {
    try {
      var res = APIClient.getInstance().getKakaoAPI().getBookInfo("https://brunch.co.kr/@tourism 집짓기",
        "accuracy",
        1,10);
      assertTrue(res.execute().isSuccessful());
    } catch (IOException e) {
      e.printStackTrace();
      fail();
    }
  }

}
