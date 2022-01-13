import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../service/authentication.service';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerData : {email, confirmEmail, username, password, confirmPassword, terms} = {email: "", confirmEmail: "", username: "", password: "", confirmPassword: "", terms: "true"};
  showError: boolean = false;
  showSuccess: boolean = false;

  errorMessage = "Server error";

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  handleLogin() {
    this.showSuccess = false;
    this.showError = false;
    this.router.navigate(['login']);
  }

  handleRegister() {
    this.userService.register(this.registerData).subscribe(
      result => {
        this.showSuccess = true;
        this.showError = false;
        this.errorMessage = "Server error";
      },
      error => {
        if(error.message) this.errorMessage = error.message;
        this.showError = true;
        this.showSuccess = false;
      }
    );
  }

}
