import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { debounceTime , distinctUntilChanged } from 'rxjs/operators';
import { AuthenticationService } from '../service/authentication.service';

export interface UserSummary {
  id;
  username;
  email;
  roles;
  active;
}

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: Array<UserSummary>;
  searchParam = "";
  totalRecords = 0;
  selectedUser;
  customInput : Subject<string> = new Subject();

  constructor(
    private userService: UserService,
    private router: Router,
    private authService: AuthenticationService) { }

  ngOnInit() {
    this.customInput.pipe(debounceTime(300),distinctUntilChanged()).subscribe(value =>{
      this.reloadData();
     });
  }

  reloadData() {
    this.getUsers(0);
  }

  getUsers(page: number) {
    this.userService.search(page, this.searchParam).subscribe(
      (result: {content:Array<UserSummary>, totalPages: number, totalElements: number}) => {
        this.users = result.content;
        this.totalRecords = result.totalElements;
      },
      () => {
        this.users = [];
        this.totalRecords = 0;
      }
    );
  }

  loadUsers(event) {
    this.getUsers(event.first/10);
  }

  onRowSelect(event) {
    if(!(this.authService.getUserId() == this.selectedUser.id+""))
      this.router.navigateByUrl('/user/'+this.selectedUser.id);
  }

  inputValueChanged(){
    this.customInput.next(this.searchParam);
  }

}
