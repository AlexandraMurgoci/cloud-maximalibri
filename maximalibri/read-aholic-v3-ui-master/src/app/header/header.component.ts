import { Component, OnInit } from '@angular/core';
import { AuthenticationService, Role } from '../service/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private authService: AuthenticationService
  ) { }

  ngOnInit() {
  }

  isUserLogged(): boolean {
    return this.authService.isUserLogged();
  }

  isUserAdmin(): boolean {
    return this.isUserLogged() && this.authService.userHasRole(Role.ADMIN);
  }

}
