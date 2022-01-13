import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule , HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {RatingModule} from 'primeng/rating';
import {PasswordModule} from 'primeng/password';
import {DataViewModule} from 'primeng/dataview';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import {TableModule} from 'primeng/table';
import {CarouselModule} from 'primeng/carousel';

import { AppComponent } from './app.component';
import { BookDetailsComponent } from './book-details/book-details.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthInterceptor } from './http-interceptors/auth-interceptor';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { LogoutComponent } from './logout/logout.component';
import { RegisterComponent } from './register/register.component';
import { BookGaleryComponent } from './book-galery/book-galery.component';
import { BookListComponent } from './book-list/book-list.component';
import { BookListViewComponent } from './book-list/book-list-view/book-list-view.component';
import { BookGridViewComponent } from './book-list/book-grid-view/book-grid-view.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UserListComponent } from './user-list/user-list.component';

@NgModule({
  declarations: [
    AppComponent,
    BookDetailsComponent,
    LoginComponent,
    HeaderComponent,
    LogoutComponent,
    RegisterComponent,
    BookGaleryComponent,
    BookListComponent,
    BookListViewComponent,
    BookGridViewComponent,
    UserDetailsComponent,
    UserListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    InputTextModule,
    ButtonModule,
    RatingModule,
    PasswordModule,
    DataViewModule,
    ProgressSpinnerModule,
    TableModule,
    CarouselModule
  ],
  providers: [
    {  
      provide:HTTP_INTERCEPTORS, useClass:AuthInterceptor, multi:true 
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
