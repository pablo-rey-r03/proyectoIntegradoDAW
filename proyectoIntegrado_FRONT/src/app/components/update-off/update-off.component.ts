import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { OffererService } from '../../service/offerer.service';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-update-off',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './update-off.component.html',
  styleUrl: './update-off.component.css'
})
export class UpdateOffComponent {
  public offerer: any = {};
  private offererId: number;

  constructor(private offService: OffererService, private aR: ActivatedRoute, private router: Router, private auth: AuthService) {
    this.offererId = this.aR.snapshot.params["id"];
    this.offService.getById(this.offererId).subscribe({
      next: data => {
        console.log(data);
        this.offerer.name = data.name;
        this.offerer.surname = data.surname;
        this.offerer.address = data.address;
        this.offerer.phoneNumber = data.phoneNumber;
        this.offerer.birthDate = this.convertDate(data.birthDate);
        this.offerer.formation = data.formation;
        this.offerer.user_email = data.user.email;
      },
      error: e => router.navigate(["unauthorized"])
    });
  }

  onSubmit() {
    console.log(this.offerer);
    this.offService.update(this.offerer, this.offererId).subscribe({
      next: data => {
        this.offerer = data;
        this.router.navigate(["offerer"]);
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
