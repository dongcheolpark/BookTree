package com.booktree.ui.book.bookList;

import android.content.Context;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.API.APIClient;
import com.booktree.API.DTO.BookDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRecyclerList {
  private final RecyclerView bookInfoList;
  private final Context context;
  private final bookListAdapter adapter;

  public BookRecyclerList(RecyclerView list, Context context) {
    bookInfoList = list;
    this.context = context;
    adapter = new bookListAdapter(this.context);
    var linearManager = new LinearLayoutManager(this.context);
    linearManager.setOrientation(RecyclerView.VERTICAL);
    bookInfoList.setAdapter(adapter);
    bookInfoList.setLayoutManager(linearManager);
  }

  public Observer<String> getInitialListItems() {
    return new Observer<String>() {
      @Override
      public void onChanged(String s) {
        APIClient.getInstance().getKakaoAPI().getBookInfo(s,
            "accuracy",
            1, 10).enqueue(
            new Callback<BookDTO>() {
              @Override
              public void onResponse(Call<BookDTO> call, Response<BookDTO> response) {
                if(response.body().meta.total_count != 0) {
                  adapter.setDocuments(response.body().documents);
                  adapter.notifyDataSetChanged();
                }
              }
              @Override
              public void onFailure(Call<BookDTO> call, Throwable t) {
                //
              }
            });
      }
    };
  }
}
