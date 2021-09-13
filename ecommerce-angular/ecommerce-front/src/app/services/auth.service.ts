import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthRequest } from '../common/auth-request';
import { AuthResult } from '../common/auth-result';
import { Customer } from '../common/customer';
// import * as moment from "moment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = "http://localhost:8080/api/users/authenticate";

  private registerUrl = "http://localhost:8080/api/users/register";

  constructor(private http: HttpClient) {
  }

  register(customer : Customer){
    return this.http.post<any>(this.registerUrl, customer);
  }

  login(authRequest: AuthRequest) {
    return this.http.post<AuthResult>(this.loginUrl, authRequest);
  }

  logout() {
    localStorage.removeItem("id_token");
    localStorage.removeItem("expires_at");
  }

  public isLoggedIn() {
    // return moment().isBefore(this.getExpiration());
  }

  // isLoggedOut() {
  //   return !this.isLoggedIn();
  // }

  // getExpiration() {
  //   const expiration = localStorage.getItem("expires_at");
  //   const expiresAt = JSON.parse(expiration);
  //   return moment(expiresAt);
  // }
}
