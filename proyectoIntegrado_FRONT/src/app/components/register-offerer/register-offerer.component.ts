import { Component } from '@angular/core';
import { OffererService } from '../../service/offerer.service';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../service/auth.service';
import { User } from '../../model/user';
import { FormsModule } from '@angular/forms';
import { Role } from '../../model/role';

@Component({
  selector: 'app-register-offerer',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './register-offerer.component.html',
  styleUrl: './register-offerer.component.css'
})
export class RegisterOffererComponent {
  public offerer: any = {};
  public user: User = <User>{};
  public confirmarPassword: string = "";

  constructor(private offService: OffererService, private auth: AuthService, private router: Router) {

  }

  onSubmit() {
    if (this.user.password !== this.confirmarPassword) {
      console.error("Las contraseñas no coinciden.");
      return;
    }

    this.user.role = Role.ROLE_OFF;
    this.auth.register(this.user).subscribe({
      next: user => {
        this.user = user;
        this.offerer.user_email = this.user.email;
        this.offService.create(this.offerer).subscribe({
          next: data => {
            this.offerer = data;
            this.router.navigate(["login"]);
          },
          error: e => console.log(e)
        });
      },
      error: e => console.log(e)
    });


  }
}
