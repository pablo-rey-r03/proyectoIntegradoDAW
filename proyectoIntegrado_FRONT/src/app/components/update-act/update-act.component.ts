import { Component } from '@angular/core';
import { ActivityService } from '../../service/activity.service';
import { AuthService } from '../../service/auth.service';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { OffererService } from '../../service/offerer.service';
import { Offerer } from '../../model/offerer';

@Component({
  selector: 'app-update-act',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './update-act.component.html',
  styleUrl: './update-act.component.css'
})
export class UpdateActComponent {
  public activity: any = {};
  private actId: number;
  public offerer: Offerer = <Offerer>{};

  constructor(private actService: ActivityService, private auth: AuthService, private offService: OffererService, private aR: ActivatedRoute, private router: Router) {
    this.actId = this.aR.snapshot.params["id"];
    this.actService.getById(this.actId).subscribe(
      data => {
        this.activity = data;
        this.activity.date = this.convertDate(data.date);
        console.log(this.activity);
      },
      e => router.navigate(["/unauthorized"])
    );

    this.offService.getByUserEmail(this.auth.getUserEmail()).subscribe(
      data => {
        this.offerer = data;
      }
    );

    if (this.activity.offererId != this.offerer.id) {
      router.navigate(["/unauthorized"])
    }
  }

  onSubmit() {
    this.actService.update(this.activity).subscribe(
      data => {
        console.log(data);
        this.router.navigate(["/offererActivities/" + this.offerer.id]);
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
