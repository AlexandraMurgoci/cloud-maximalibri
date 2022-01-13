import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../service/authentication.service';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData : {email, password} = {email: "", password: ""};
  showError: boolean = false;

  constructor(
    private authService: AuthenticationService,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  handleLogin() {
    this.userService.login(this.loginData.email, this.loginData.password).subscribe(
      (result:{userId:string; roles:Array<string>}) => {
        this.authService.setAuthentication(this.loginData.email, this.loginData.password,result.roles, result.userId);
        this.showError = false;
        this.router.navigate(['home']);
      },
      error => {
        this.showError = true;
      }
    );
  }

  handleRegister() {
    this.router.navigate(['register']);
  }

}
