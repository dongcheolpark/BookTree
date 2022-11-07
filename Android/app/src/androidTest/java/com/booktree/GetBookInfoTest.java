package com.booktree;

import static org.junit.Assert.*;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.booktree.API.APIClient;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class GetBookInfoTest {
  @Test
  public void isAPIValid() {
    try {
      var res = APIClient.getInstance().getKakaoAPI().getBookInfo("어린 왕자",
        "accuracy",
        1,10).execute();
      assertTrue(res.isSuccessful());
    } catch (IOException e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void isSearchDoWell() {
    try {
      var res = APIClient.getInstance().getKakaoAPI().getBookInfo("어린 왕자",
          "accuracy",
          1,10).execute();
      assertThat(res.body().meta.total_count).isGreaterThan(0);
    } catch (IOException e) {
      e.printStackTrace();
      fail();
    }
  }
  @Test
  public void canSearchWithISBN() {
    try {
      var res = APIClient.getInstance().getKakaoAPI().getBookInfo("9791159171772",
          "accuracy",
          1,10).execute();
      assertThat(res.body().meta.total_count).isGreaterThan(0);
      assertThat(res.body().documents[0].title).isEqualTo("일본어 들어가기");
    } catch (IOException e) {
      e.printStackTrace();
      fail();
    }
  }

}
