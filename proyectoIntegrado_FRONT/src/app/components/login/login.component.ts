import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../service/auth.service';
import { Router, RouterLink } from '@angular/router';
import { LoginUser } from '../../model/login-user';
import { Role } from '../../model/role';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  public credentials: LoginUser = <LoginUser>{};
  public error: string = "";
  public showError: boolean = false;

  constructor(private auth: AuthService, private router: Router) {
    this.auth.logout();
  }

  onSubmit(): void {
    this.auth.login(this.credentials).subscribe({
      next: jwt => {
        if (jwt.token) {
          this.auth.setToken(jwt.token);
          if (this.auth.getUserRole() == Role.ROLE_OFF) {
            this.router.navigate(["/offerer"]);
          } else if (this.auth.getUserRole() == Role.ROLE_CUS) {
            this.router.navigate(["/customer"]);
          }
        }
      },
      error: error => {
        console.log(error);
        this.error = error.error;
        this.showError = true;
        setTimeout(() => {
          this.showError = false;
        }, 3000);
      }
    });
  }
}
