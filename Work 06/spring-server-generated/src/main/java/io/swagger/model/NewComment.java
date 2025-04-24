package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.configuration.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NewComment
 */
@Validated
@NotUndefined
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-04-24T05:33:38.835967035Z[GMT]")


public class NewComment   {
  @JsonProperty("text")

  private String text = null;

  @JsonProperty("author")

  private String author = null;


  public NewComment text(String text) { 

    this.text = text;
    return this;
  }

  /**
   * Get text
   * @return text
   **/
  
  @Schema(example = "Это новый комментарий.", required = true, description = "")
  
  @NotNull
  public String getText() {  
    return text;
  }



  public void setText(String text) { 

    this.text = text;
  }

  public NewComment author(String author) { 

    this.author = author;
    return this;
  }

  /**
   * Get author
   * @return author
   **/
  
  @Schema(example = "Автор нового комментария", required = true, description = "")
  
  @NotNull
  public String getAuthor() {  
    return author;
  }



  public void setAuthor(String author) { 

    this.author = author;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewComment newComment = (NewComment) o;
    return Objects.equals(this.text, newComment.text) &&
        Objects.equals(this.author, newComment.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, author);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewComment {\n");
    
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
