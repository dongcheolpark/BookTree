package com.booktree.Model;

import static org.assertj.core.api.Assertions.assertThat;

import com.booktree.model.Documents;
import org.junit.Test;

public class DocumentsTest {
  @Test
  public void ISBN_테스트1() {
    var doc = new Documents();
    doc.isbn = "0123456789";
    assertThat(doc.getIsbn()).isEqualTo("0123456789");
  }
  @Test
  public void ISBN_테스트2() {
    var doc = new Documents();
    doc.isbn = "0123456789 01234567890123";
    assertThat(doc.getIsbn()).isEqualTo("0123456789");
  }
}
