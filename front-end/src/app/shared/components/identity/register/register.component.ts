import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { RegisterRequest } from 'src/app/core/models/identity/register-request';
import { UserService } from 'src/app/core/services/identity/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  @Output() closeRegister = new EventEmitter<void>();

  showNotification = false;

  registerRequest: RegisterRequest = new RegisterRequest();

  constructor(
    private userService: UserService
  ) { }

  ngOnInit(): void {
  }

  onRegister() {
    this.userService.register(this.registerRequest).subscribe({
      error: (err) => {
        console.error(err);
      }
    });

    this.showNotification = true;
  }

  close() {
    this.closeRegister.emit();
  }

  closeNotification() {
    this.showNotification = false;
    this.closeRegister.emit();
  }
}
