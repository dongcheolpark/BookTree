package com.booktree.ui.book.bookList;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import com.booktree.API.APIClient;
import com.booktree.API.DTO.BookDTO;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRecyclerList {
  private final RecyclerView bookInfoList;
  private final Context context;
  private final bookListAdapter adapter;
  private LinearLayoutManager layoutManager;
  private String query;

  private Integer currentPage = 0;
  private final Integer pageSize = 10;

  public BookRecyclerList(RecyclerView list, Context context) {
    bookInfoList = list;
    this.context = context;
    adapter = new bookListAdapter(this.context);
    layoutManager = new LinearLayoutManager(this.context);
    layoutManager.setOrientation(RecyclerView.VERTICAL);
    bookInfoList.setAdapter(adapter);
    bookInfoList.setLayoutManager(layoutManager);
    // 리스트 기본 설정
    bookInfoList.addOnScrollListener(new OnScrollListener() {
      @Override
      public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        int totalItemCount = layoutManager.getItemCount();
        int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();

        if (lastVisible >= totalItemCount - 1) {
          getItems(++currentPage);
        }
      }
    });
  }

  private void getItems(int page) {
    APIClient.getInstance().getKakaoAPI().getBookInfo(query,
        "accuracy",
        page, pageSize).enqueue(
        new Callback<BookDTO>() {
          @Override
          public void onResponse(Call<BookDTO> call, Response<BookDTO> response) {
            if(response.body().meta.total_count != 0) {
              adapter.addDocuments(response.body().documents);
              adapter.notifyItemInserted((page-1)*pageSize);
            }
          }
          @Override
          public void onFailure(Call<BookDTO> call, Throwable t) {
            //
          }
        });

  }

  public Observer<String> getInitialListItems() {
    return new Observer<String>() {
      @Override
      public void onChanged(String s) {
        query = s;
        currentPage = 0;
        adapter.clearDocuments();
        getItems(++currentPage);
      }
    };
  }
}
