import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Customer } from '../../model/customer';
import { AuthService } from '../../service/auth.service';
import { RequestService } from '../../service/request.service';
import { CustomerService } from '../../service/customer.service';

@Component({
  selector: 'app-new-req',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './new-req.component.html',
  styleUrl: './new-req.component.css'
})
export class NewReqComponent {
  public customer: Customer = <Customer>{}
  public request: any = {
    name: '',
    description: '',
    date: '',
    location: '',
    customerId: -1
  }

  constructor(private cusService: CustomerService, private auth: AuthService, private router: Router, private reqService: RequestService) {
    this.cusService.getByUserEmail(this.auth.getUserEmail()).subscribe(
      data => {
        this.customer = data;
        this.request.customerId = data.id;
      }
    )
  }

  onSubmit() {
    this.reqService.create(this.request).subscribe(
      data => {
        console.log(data);
        this.router.navigate(["/customerRequests/" + this.request.customerId])
      }
    )
  }

}
