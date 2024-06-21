import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginUser } from '../model/login-user';
import { Jwt } from '../model/jwt';
import { environment } from '../../environments/environment.development';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private API_URL = environment.API_URL + "/auth";
  private TOKEN_KEY = "token";

  constructor(private http: HttpClient) { }

  login(credentials: LoginUser) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Jwt>(`${this.API_URL}/login`, credentials, { headers });
  }

  register(user: User) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<User>(`${this.API_URL}/register`, user, { headers });
  }

  setToken(token: string) {
    sessionStorage.setItem(this.TOKEN_KEY, token)
  }

  logout() {
    sessionStorage.removeItem(this.TOKEN_KEY);
  }

  getToken() {
    return sessionStorage.getItem(this.TOKEN_KEY);
  }

  getDecodedToken(token: string) {
    const payload = token.split(".")[1];
    return JSON.parse(atob(payload));
  }

  getUserRole() {
    const token = this.getToken();

    if (token) {
      const dec = this.getDecodedToken(token);
      return dec.rol;
    }

    return null;
  }

  getUserEmail() {
    const token = this.getToken();

    if (token) {
      const dec = this.getDecodedToken(token);
      return dec.sub;
    }

    return null;
  }
}
