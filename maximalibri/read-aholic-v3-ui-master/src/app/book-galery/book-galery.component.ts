import { Component, OnInit } from '@angular/core';
import { BookService } from '../service/book.service';
import { AuthenticationService } from '../service/authentication.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-book-galery',
  templateUrl: './book-galery.component.html',
  styleUrls: ['./book-galery.component.css']
})
export class BookGaleryComponent implements OnInit {

  bookIsbnList: Array<any>;
  bookList: Array<any>;
  recommendations: Array<any>;
  loadingRecommendations: boolean = true;
  loadingGalery: boolean = true;

  constructor(
    private bookService: BookService,
    private authService: AuthenticationService,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.bookService.getTop12().subscribe(
      (result: Array<{isnb:string, average:number}>) => {
        this.bookIsbnList = result;
        this.bookService.getBooks(this.bookIsbnList.map(isbnAndRating => isbnAndRating.isbn))
        .subscribe((result: Array<any>) => {
          this.bookList = result;
          this.loadingGalery = false;
        },
        error => {
          this.bookList = [];
          this.loadingGalery = false;
        });
      },
      error => {
        this.bookIsbnList = [];
        this.bookList = [];
        this.loadingGalery = false;
      }
    );

    if(this.authService.isUserLogged()) this.userService.getRecomendationsForUser(this.authService.getUserId()).subscribe(
      (result: Array<any>) => {
        this.recommendations = result;
        this.loadingRecommendations = false;
      },
      error => {
        this.recommendations = [];
        this.loadingRecommendations = false;
      }
    );
    else this.loadingRecommendations = false;
  }

}
