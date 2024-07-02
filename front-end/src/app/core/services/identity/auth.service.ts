import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginRequest } from '../../models/identity/login-request';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8000';
  private authUrl = `${this.baseUrl}/api/auth`

  constructor(private httpClient: HttpClient) { }

  login(loginRequest: LoginRequest): Observable<any> {
    return this.httpClient.post<any>(`${this.authUrl}/login`, loginRequest)
      .pipe(tap(response => {
        localStorage.setItem('token', response.token);
      }));
  }

  logout() {
    localStorage.removeItem('token');
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

}
