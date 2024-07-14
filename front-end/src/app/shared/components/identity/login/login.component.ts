import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/core/models/identity/login-request';
import { AuthService } from 'src/app/core/services/identity/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginRequest: LoginRequest = new LoginRequest(); 

  showRegister = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  login() {
    this.authService.login(this.loginRequest).subscribe({
      next: () => {
        this.router.navigate(['/main']);
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

}
