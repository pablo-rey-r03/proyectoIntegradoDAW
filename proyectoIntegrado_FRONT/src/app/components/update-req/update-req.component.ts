import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Customer } from '../../model/customer';
import { RequestService } from '../../service/request.service';
import { CustomerService } from '../../service/customer.service';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-update-req',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './update-req.component.html',
  styleUrl: './update-req.component.css'
})
export class UpdateReqComponent {
  public request: any = {};
  private reqId: number;
  public customer: Customer = <Customer>{};

  constructor(private aR: ActivatedRoute, private reqService: RequestService, private cusService: CustomerService, private auth: AuthService, private router: Router) {
    this.reqId = this.aR.snapshot.params["id"];
    this.reqService.byId(this.reqId).subscribe(
      data => {
        this.request = data;
        this.request.date = this.convertDate(data.date);
      },
      e => router.navigate(["/unauthorized"])
    );

    this.cusService.getByUserEmail(this.auth.getUserEmail()).subscribe(
      data => {
        this.customer = data;
      }
    );

    if (this.request.customerId != this.customer.id) {
      router.navigate(["unauthorized"])
    }
  }

  onSubmit() {
    console.log(this.request);
    this.reqService.update(this.request).subscribe(
      data => {
        console.log(data);
        this.router.navigate(["/customerRequests/" + this.customer.id])
      }
    )
  }

  convertDate(dateString: string): string {
    const date = new Date(dateString);
    const pad = (n: number) => n.toString().padStart(2, '0');

    const year = date.getFullYear();
    const month = pad(date.getMonth() + 1);
    const day = pad(date.getDate());
    const hours = pad(date.getHours());
    const minutes = pad(date.getMinutes());

    return `${year}-${month}-${day}T${hours}:${minutes}`;
  }
}
