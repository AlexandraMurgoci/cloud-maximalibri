import { Component, OnInit, Input } from '@angular/core';
import { BookSummary } from '../book-list.component';

@Component({
  selector: 'app-book-grid-view',
  templateUrl: './book-grid-view.component.html',
  styleUrls: ['./book-grid-view.component.css']
})
export class BookGridViewComponent implements OnInit {

  @Input() book : BookSummary;

  constructor() { }

  ngOnInit() {
  }

}
