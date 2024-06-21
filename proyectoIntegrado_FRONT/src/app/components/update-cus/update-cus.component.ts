import { Component } from '@angular/core';
import { CustomerService } from '../../service/customer.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AuthService } from '../../service/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-update-cus',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './update-cus.component.html',
  styleUrl: './update-cus.component.css'
})
export class UpdateCusComponent {
  public customer: any = {};
  private customerId: number;

  constructor(private cusService: CustomerService, private aR: ActivatedRoute, private router: Router, private auth: AuthService) {
    this.customerId = this.aR.snapshot.params["id"];
    this.cusService.getById(this.customerId).subscribe({
      next: data => {
        console.log(data);
        this.customer.name = data.name;
        this.customer.surname = data.surname;
        this.customer.address = data.address;
        this.customer.phoneNumber = data.phoneNumber;
        this.customer.birthDate = this.convertDate(data.birthDate);
        this.customer.user_email = data.user.email;
      },
      error: e => router.navigate(["unauthorized"])
    });
  }

  onSubmit() {
    this.cusService.update(this.customer, this.customerId).subscribe({
      next: data => {
        this.customer = data;
        this.router.navigate(["customer"]);
      }
    })
  }

  convertDate(dateString: string): string {
    const date = new Date(dateString);
    const pad = (n: number) => n.toString().padStart(2, '0');

    const year = date.getFullYear();
    const month = pad(date.getMonth() + 1);
    const day = pad(date.getDate());

    return `${year}-${month}-${day}`;
  }
}
