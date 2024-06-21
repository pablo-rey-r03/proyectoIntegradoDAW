import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { User } from '../../model/user';
import { CustomerService } from '../../service/customer.service';
import { AuthService } from '../../service/auth.service';
import { Role } from '../../model/role';

@Component({
  selector: 'app-register-customer',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './register-customer.component.html',
  styleUrl: './register-customer.component.css'
})
export class RegisterCustomerComponent {
  public customer: any = {};
  public user: User = <User>{};
  public confirmarPassword: string = '';

  constructor(private cusService: CustomerService, private router: Router, private auth: AuthService) {

  }

  onSubmit() {
    console.log(this.customer);
    console.log(this.user);

    if (this.user.password !== this.confirmarPassword) {
      console.error("Las contraseñas no coinciden.");
      return;
    }

    this.user.role = Role.ROLE_CUS;
    this.auth.register(this.user).subscribe({
      next: user => {
        console.log(user);
        this.user = user;

        this.customer.user_email = this.user.email;
        console.log(this.customer);
        this.cusService.create(this.customer).subscribe({
          next: data => {
            this.customer = data;
            this.router.navigate(["login"]);
          },
          error: e => console.log(e)
        });
      },
      error: e => console.log(e)
    });


  }
}
