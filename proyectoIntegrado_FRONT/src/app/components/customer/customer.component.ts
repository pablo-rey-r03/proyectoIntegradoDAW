// customer.component.ts
import { Component } from '@angular/core';
import { Customer } from '../../model/customer';
import { Router, RouterLink } from '@angular/router';
import { CurrencyPipe, DatePipe } from '@angular/common';
import { Activity } from '../../model/activity';
import { CustomerService } from '../../service/customer.service';
import { ActivityService } from '../../service/activity.service';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-customer',
  standalone: true,
  imports: [RouterLink, DatePipe, CurrencyPipe],
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent {
  public customer: Customer = <Customer>{};
  public activities: Activity[] = [];
  public selectedActivity: Activity | null = null;
  public isModalOpen: boolean = false;

  constructor(private cusService: CustomerService, private actService: ActivityService, private auth: AuthService, private router: Router) {
    this.actService.list().subscribe(
      data => {
        this.activities = data;
      }
    );

    this.cusService.getByUserEmail(this.auth.getUserEmail()).subscribe(
      data => {
        this.customer = data;
      }
    );
  }

  toggleMenu() {
    document.getElementById('wrapper')?.classList.toggle('toggled');
    document.getElementById("menu-toggle")?.classList.toggle("toggled");
  }

  openModal(activity: Activity) {
    this.selectedActivity = activity;
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
    this.selectedActivity = null;
  }

  logout() {
    this.auth.logout();
    this.router.navigate(["login"]);
  }

  deleteAccount(id: number) {
    if (confirm("¿Desea eliminar su cuenta? ESTA ACCIÓN ES IRREVERSIBLE.")) {
      this.cusService.delete(id).subscribe({
        next: data => {
          this.auth.logout();
          console.log(data);
        },
        error: e => console.log(e)
      })
    }
  }
}
