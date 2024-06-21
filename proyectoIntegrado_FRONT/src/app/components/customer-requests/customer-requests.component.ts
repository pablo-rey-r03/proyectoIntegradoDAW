import { Component } from '@angular/core';
import { Request } from '../../model/request';
import { RequestService } from '../../service/request.service';
import { CustomerService } from '../../service/customer.service';
import { AuthService } from '../../service/auth.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-customer-requests',
  standalone: true,
  imports: [RouterLink, DatePipe],
  templateUrl: './customer-requests.component.html',
  styleUrl: './customer-requests.component.css'
})
export class CustomerRequestsComponent {
  public requests: Request[] = [];
  private customerId: number;

  constructor(private reqService: RequestService, private cusService: CustomerService, private auth: AuthService, private aR: ActivatedRoute, private router: Router) {
    this.customerId = aR.snapshot.params["id"];
    this.cusService.getById(this.customerId).subscribe(
      data => {
        if (data.user.email != this.auth.getUserEmail()) {
          this.router.navigate(["unauthorized"]);
        }
      },
      error => router.navigate(["unauthorized"])
    );

    this.reqService.byCustomer(this.customerId).subscribe(
      data => {
        this.requests = data;
      },
      e => console.log(e)
    )
  }

  borrar(id: number, name: string) {
    if (confirm("¿Desea eliminar la solicitud \"" + name + "\" del sistema?")) {
      this.reqService.delete(id).subscribe(
        data => this.requests = data
      );
    }
  }

}
