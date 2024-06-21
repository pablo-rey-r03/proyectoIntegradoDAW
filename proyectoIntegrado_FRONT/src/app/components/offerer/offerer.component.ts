// offerer.component.ts
import { Component } from '@angular/core';
import { OffererService } from '../../service/offerer.service';
import { AuthService } from '../../service/auth.service';
import { Router, RouterLink } from '@angular/router';
import { RequestService } from '../../service/request.service';
import { Request } from '../../model/request';
import { DatePipe } from '@angular/common';
import { Offerer } from '../../model/offerer';

@Component({
  selector: 'app-offerer',
  standalone: true,
  imports: [DatePipe, RouterLink, DatePipe],
  templateUrl: './offerer.component.html',
  styleUrls: ['./offerer.component.css']
})
export class OffererComponent {
  public offerer: Offerer = <Offerer>{};
  public requests: Request[] = [];
  public selectedRequest: Request | null = null;
  public isModalOpen: boolean = false;

  constructor(private offService: OffererService, private reqService: RequestService, private auth: AuthService, private router: Router) {
    this.reqService.list().subscribe(
      data => {
        this.requests = data;
      }
    );

    this.offService.getByUserEmail(this.auth.getUserEmail()).subscribe(
      data => {
        this.offerer = data;
        console.log(this.offerer);
      }
    );
  }

  toggleMenu() {
    document.getElementById('wrapper')?.classList.toggle('toggled');
    document.getElementById("menu-toggle")?.classList.toggle("toggled");
  }

  openModal(request: Request) {
    this.selectedRequest = request;
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
    this.selectedRequest = null;
  }

  logout() {
    this.auth.logout();
    this.router.navigate(["login"]);
  }

  deleteAccount(id: number) {
    if (confirm("¿Desea eliminar su cuenta? ESTA ACCIÓN ES IRREVERSIBLE.")) {
      this.offService.delete(id).subscribe({
        next: data => {
          this.auth.logout();
          console.log(data);
        },
        error: e => console.log(e)
      })
    }
  }
}
