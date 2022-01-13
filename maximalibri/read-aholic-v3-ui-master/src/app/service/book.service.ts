import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
// import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  //TODO


  private baseUrlBooks = "http://192.168.49.2:31204/book";
  private baseUrlBookDescriptions = "http://192.168.49.2:31179/book-description";
  private baseUrlBookRatings = "http://192.168.49.2:31136/book-rating";
  private baseUrlBookReviews = "http://192.168.49.2:32306/book-review";

  // private baseUrl = "http://localhost:8080/book";
  // private baseUrlBooks = "http://localhost:8081/book";
  // private baseUrlBookDescriptions = "http://localhost:8082/book-description";
  // private baseUrlBookRatings = "http://localhost:8083/book-rating";
  // private baseUrlBookReviews = "http://localhost:8084/book-review";

  constructor(
    private http: HttpClient
  ) { }

  getTop12() {
    return this.http.get(this.baseUrlBookRatings + "/top");
  }

  getBook(isbn) {
    return this.http.get(this.baseUrlBooks+"/"+isbn);
  }

  getBooks(isbnList) {
    return this.http.post(this.baseUrlBooks+"/find-by-isbn-list", {value: isbnList});
  }

  getBookDescription(isbn) {
    return this.http.get(this.baseUrlBookDescriptions+"/"+isbn);
  }

  getBookReviews(isbn) {
    return this.http.get(this.baseUrlBookReviews+"/reviews/"+isbn);
  }

  search(page, searchParam?) {
    return searchParam && (searchParam+"")!="" ?
     this.http.get(this.baseUrlBooks+"/search?page="+page+"&searchParam="+searchParam)
     : this.http.get(this.baseUrlBooks+"/search?page="+page);
  }

  rateBook(userId, isbn, userRating) {
    let bookRatingData = {"userId":userId, "isbn":isbn, "rating":userRating};
    return this.http.post(this.baseUrlBookRatings+"/rate", bookRatingData);
  }

  unRateBook(userId, isbn) {
    let bookRatingData = {"userId":userId, "isbn":isbn, "rating":0};
    return this.http.post(this.baseUrlBookRatings+"/un-rate", bookRatingData);
  }

  saveReview(userId, isbn, review) {
    let bookReviewData = {"userId":userId, "isbn":isbn, "review":review};
    return this.http.post(this.baseUrlBookReviews+"/user/review", bookReviewData);
  }

  getBookRatingForUser(userId, isbn) {
    return this.http.get(this.baseUrlBookRatings+"/"+isbn+"/rating/user/"+userId);
  }
}
