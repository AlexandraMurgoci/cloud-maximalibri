import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookGridViewComponent } from './book-grid-view.component';

describe('BookGridViewComponent', () => {
  let component: BookGridViewComponent;
  let fixture: ComponentFixture<BookGridViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookGridViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookGridViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
