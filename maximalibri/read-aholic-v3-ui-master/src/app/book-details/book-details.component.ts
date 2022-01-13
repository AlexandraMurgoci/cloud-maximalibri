import { Component, OnInit } from '@angular/core';
import { BookService } from '../service/book.service';
import { AuthenticationService } from '../service/authentication.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  book: any;
  loading = true;
  userRating = 0;
  userReview = "";
  bookReviews = [];


  constructor(
    private bookService: BookService,
    private authService: AuthenticationService,
    private activatedRoute : ActivatedRoute
  ) { }

  ngOnInit() {
    this.activatedRoute.url.subscribe(url =>{
      this.getBook(url[1]);
      this.getBookDescription(url[1]);
      this.getBookReviews(url[1]);
    });
    
  }

  getBook(isbn) {
    this.bookService.getBook(isbn).subscribe(
      (result) => {
        this.book = result;
        this.loading = false;
        if(this.authService.isUserLogged())
          this.bookService.getBookRatingForUser(this.authService.getUserId(), this.book.isbn).subscribe(
            (result:{value:number}) => this.userRating = result.value
          );
      }
    );
  }

  getBookDescription(isbn) {
    this.bookService.getBookDescription(isbn).subscribe( (result:{value:string}) => {
      this.book.description = result.value;
    });
    // for (let i = 0; i < 10000; i++) {
    //   this.bookService.getBookDescription(isbn).subscribe( (result:{value:string}) => {
    //     //do nothing
    //   });      
    // }
  }

  getBookReviews(isbn) {
    this.bookService.getBookReviews(isbn).subscribe(
      (result: any[]) => {
        this.bookReviews = result;
      },
      () => this.bookReviews = []
    );
  }

  onRate(event) {
    this.bookService.rateBook(this.authService.getUserId(), this.book.isbn, this.userRating).subscribe();
  }

  onCancelRating() {
    this.bookService.unRateBook(this.authService.getUserId(), this.book.isbn).subscribe();
  }

  saveReview() {
    if(this.userReview != "") {
      this.bookService.saveReview(this.authService.getUserId(), this.book.isbn, this.userReview).subscribe(
        () => this.getBookReviews(this.book.isbn)
      );
    }
  }

}
