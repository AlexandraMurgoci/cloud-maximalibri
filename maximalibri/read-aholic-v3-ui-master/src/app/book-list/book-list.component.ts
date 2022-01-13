import { Component, OnInit } from '@angular/core';
import { BookService } from '../service/book.service';
import { Subject } from 'rxjs';
import { debounceTime , distinctUntilChanged } from 'rxjs/operators';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  bookList: BookSummary[] = [];
  searchParam = "";
  totalRecords = 0;

  customInput : Subject<string> = new Subject();

  constructor(
    private bookService: BookService
  ) { }

  ngOnInit() {
    this.reloadData();
    this.customInput.pipe(debounceTime(300),distinctUntilChanged()).subscribe(value =>{
      this.reloadData();
     });
  }

  loadData(event) {
    this.bookService.search(event.first/10, this.searchParam).subscribe(
      (result: {content:Array<BookSummary>, totalPages: number, totalElements: number}) => {
        this.bookList = result.content;
        this.totalRecords = result.totalElements;
      },
      () => this.bookList = []
    );
  }

  reloadData() {
    this.bookService.search(0, this.searchParam).subscribe(
      (result: {content:Array<BookSummary>, totalPages: number, totalElements: number}) => {
        this.bookList = result.content;
        this.totalRecords = result.totalElements;
      }
    );
  }

  inputValueChanged(){
    this.customInput.next(this.searchParam);
  }

}

export interface BookSummary {
  isbn;
  title;
  author;
  imageUrlS;
  imageUrlM;
  imageUrlL;
  rating;
}
