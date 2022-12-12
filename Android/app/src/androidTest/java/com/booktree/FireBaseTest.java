package com.booktree;


import static org.assertj.core.api.Assertions.assertThat;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.booktree.API.FBDatabase;
import com.booktree.model.Feed;
import com.booktree.model.Friend;
import com.booktree.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FireBaseTest {
  private final CountDownLatch signal = new CountDownLatch(1);
  @Test
  public void 파이어베이스_연결_테스트() {
    FBDatabase.getInstance();
  }
  //@Test
  public void 피드_생성_테스트() throws InterruptedException {
    var feed = new Feed("9791159171772",
        "testAuthor",
        "test content",
        "https://i.stack.imgur.com/GsDIl.jpg",
        new Date());
    var res =  FBDatabase.getInstance().createFeed(feed);
    res.onSuccessTask((response) -> {
      signal.countDown();
      return null;
    });
    signal.await(30, TimeUnit.SECONDS);
  }
  @Test
  public void 유저_생성_테스트() throws InterruptedException {
    var user = new User(
        "cogus",
        "t02xJtMsiBc7sl4BSYA8EdNEJyo2",
        "https://cdn-icons-png.flaticon.com/512/1361/1361876.png"
    );
    FBDatabase.getInstance().createUser(user,() ->{
      signal.countDown();
    });
    signal.await(30, TimeUnit.SECONDS);
  }
  @Test
  public void 유저_가져오기_테스트() throws InterruptedException {
    var user = new User(
        "dongcheolpark",
        "nvWcwYhZPrXRhGjD4Hiq0qX9vt52",
        "https://cdn-icons-png.flaticon.com/512/1361/1361876.png"
    );
    FBDatabase.getInstance().getUser(user.uid,(res) ->{
      assertThat(res).isEqualTo(user);
      signal.countDown();
    });
    signal.await(5, TimeUnit.SECONDS);
  }
  @Test
  public void 친구_생성_테스트() throws InterruptedException {
    var friend = new Friend(
        "nvWcwYhZPrXRhGjD4Hiq0qX9vt52",
        "WllfZDNy79RntUt5pEqA13r79Kj2"
    );
    FBDatabase.getInstance().createFollow(friend,() ->{
      signal.countDown();
    });
    signal.await(5, TimeUnit.SECONDS);
  }
  @Test
  public void 친구_리스트_가져오기_테스트() throws InterruptedException {
    FBDatabase.getInstance().getFollowing("nvWcwYhZPrXRhGjD4Hiq0qX9vt52",(list) ->{
      assertThat(list.size()).isEqualTo(2);
      signal.countDown();
    });
    signal.await(5, TimeUnit.SECONDS);
  }
  @Test
  public void 피드_가져오기_테스트() throws InterruptedException {
    FBDatabase.getInstance().getFeed(list -> {
      assertThat(list.size()).isGreaterThan(0);
      signal.countDown();
    });
    signal.await(5, TimeUnit.SECONDS);
  }
  @Test
  public void 피드_가져오기_테스트2() throws InterruptedException {
    FBDatabase.getInstance().getFeedWithIsbn("1158318804",list -> {
      assertThat(list.size()).isGreaterThan(0);
      signal.countDown();
    });
    signal.await(5, TimeUnit.SECONDS);
  }
}
