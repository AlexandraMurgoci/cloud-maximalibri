import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookGaleryComponent } from './book-galery.component';

describe('BookGaleryComponent', () => {
  let component: BookGaleryComponent;
  let fixture: ComponentFixture<BookGaleryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookGaleryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookGaleryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
