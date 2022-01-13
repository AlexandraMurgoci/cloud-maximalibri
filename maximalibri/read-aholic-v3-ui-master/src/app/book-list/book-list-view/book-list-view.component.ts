import { Component, OnInit, Input } from '@angular/core';
import { BookSummary } from '../book-list.component';

@Component({
  selector: 'app-book-list-view',
  templateUrl: './book-list-view.component.html',
  styleUrls: ['./book-list-view.component.css']
})
export class BookListViewComponent implements OnInit {

  @Input() book : BookSummary;

  constructor() { }

  ngOnInit() {
  }

}
