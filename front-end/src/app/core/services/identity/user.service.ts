import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterRequest } from '../../models/identity/register-request';
import { Observable } from 'rxjs';
import { LoginRequest } from '../../models/identity/login-request';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8000';
  private userUrl = `${this.baseUrl}/api/users`;

  constructor(private httpClient: HttpClient) { }

  register(registerRequest: RegisterRequest): Observable<any> {
    return this.httpClient.post<any>(`${this.userUrl}/register`, registerRequest);
  }

  getUserIdFromToken(): number | null {
    const token = localStorage.getItem("token");
    if (token) {
      const payload = atob(token.split('.')[1]);
      const decoded = JSON.parse(payload);
      return decoded.userId;
    }
    return null;
  }
}
