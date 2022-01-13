import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';
import { ActivatedRoute } from '@angular/router';
import { Role } from '../service/authentication.service';

export class UserDet {
  id: number;
  username: string;
  email: string;
  active: boolean;
  roles: Array<string>;
}


@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  user: UserDet;
  reviews: [];
  loading: boolean = false;

  constructor(
    private userService: UserService,
    private activatedRoute : ActivatedRoute
  ) { }

  ngOnInit() {
    this.activatedRoute.url.subscribe(url =>{
      this.loading = true;
      this.getUser(url[1]);
      this.getReviews(url[1]);
    });
  }

  getUser(id) {
    this.userService.getUserDetails(id).subscribe(
      (result: UserDet) => {
        this.user = result;
        this.loading = false;
      }
    );
    // this.user = new UserDet();
    // this.user.id = 300000;
    // this.user.username = "SuperAdmin";
    // this.user.email = "ceva@mail.com";
    // this.user.active = true;
    // this.user.roles = ['ROLE_USER', 'ROLE_ADMIN'];
  }

  activateUser() {
    this.userService.changeUserActivation(this.user.id, true).subscribe(
      () => this.user.active = true
    );
  }
  
  deactivateUser() {
    this.userService.changeUserActivation(this.user.id, false).subscribe(
      () => this.user.active = false
    );
  }

  getReviews(id) {
    this.userService.getReviewsForUser(id).subscribe(
      (result: []) => this.reviews = result
    )
  }

  makeAdmin() {
    this.userService.makeAdmin(this.user.id).subscribe(
      () => this.getUser(this.user.id)
    );
  }

  private userIsAdmin(): boolean {
    return this.user.roles.indexOf(Role.ADMIN)!=-1;
  }

  deleteReview(id){
    this.userService.deleteReviewById(id).subscribe(
      () => this.getReviews(this.user.id)
    );
  }

}

