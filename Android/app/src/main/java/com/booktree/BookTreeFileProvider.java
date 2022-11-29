package com.booktree;

import androidx.core.content.FileProvider;

public class BookTreeFileProvider extends FileProvider {
  public BookTreeFileProvider() {
    super(R.xml.file_paths);
  }
}
