package com.booktree;


import static org.assertj.core.api.Assertions.assertThat;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.booktree.API.FBDatabase;
import com.booktree.model.Feed;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FireBaseTest {
  @Test
  public void 파이어베이스_연결_테스트() {
    FBDatabase.getInstance();
  }
  @Test
  public void 피드_생성_테스트() throws InterruptedException {
    final CountDownLatch signal = new CountDownLatch(1);
    var feed = new Feed("9791159171772",
        "testAuthor",
        "test content",
        "https://i.stack.imgur.com/GsDIl.jpg");
    var res =  FBDatabase.getInstance().createFeed(feed);
    res.onSuccessTask((response) -> {
      signal.countDown();
      return null;
    });
    signal.await(30, TimeUnit.SECONDS);
  }
}
