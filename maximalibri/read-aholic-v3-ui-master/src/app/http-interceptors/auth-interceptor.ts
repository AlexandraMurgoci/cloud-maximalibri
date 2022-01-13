import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import { AuthenticationService } from '../service/authentication.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
      private authService: AuthenticationService
  ) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    if(!this.authService.isUserLogged())
        return next.handle(req);

    // Clone the request and replace the original headers with
    // cloned headers, updated with the authorization.
    let authReq = req.clone({
      headers: req.headers.set('User-Email', this.authService.getUserEmail())
    });
    let authReq2 = authReq.clone({
        headers: authReq.headers.set('User-Password', this.authService.getUserPassword())
      });
    // send cloned request with header to the next handler.
    return next.handle(authReq2);
  }
}