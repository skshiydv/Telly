import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {JwtHelperService} from "@auth0/angular-jwt";
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private jwtHelper = new JwtHelperService();
  private emailSubject = new BehaviorSubject<string | null>(null);
  public email$: Observable<string | null> = this.emailSubject.asObservable();
  isAuthenticated(): boolean {
    const token = localStorage.getItem("access_token");
    if (token) {
      const decodedToken = this.jwtHelper.decodeToken(token);
      const email = decodedToken.sub;
      this.emailSubject.next(email);
      return!this.jwtHelper.isTokenExpired(token);
    }
    return false;
  }
  logout() {
    localStorage.removeItem('access_token');
  }
  login(token: any) {
    localStorage.setItem('access_token', token);
  }
}


export class User {
}
