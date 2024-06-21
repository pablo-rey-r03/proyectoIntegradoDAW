import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { ActivityService } from '../../service/activity.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Activity } from '../../model/activity';
import { OffererService } from '../../service/offerer.service';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-offerer-activities',
  standalone: true,
  imports: [DatePipe, RouterLink],
  templateUrl: './offerer-activities.component.html',
  styleUrl: './offerer-activities.component.css'
})
export class OffererActivitiesComponent {
  public activities: Activity[] = [];
  private offererId: number;

  constructor(private actService: ActivityService, private offService: OffererService, private auth: AuthService, private aR: ActivatedRoute, private router: Router) {
    this.offererId = this.aR.snapshot.params["id"];
    this.offService.getById(this.offererId).subscribe(
      data => {
        if (data.user.email != this.auth.getUserEmail()) {
          router.navigate(["unauthorized"]);
        }
      },
      error => router.navigate(["unauthorized"])
    );

    this.actService.byOfferer(this.offererId).subscribe(
      data => {
        this.activities = data;
      },
      e => {
        console.log(e.error);
        console.log(this.activities);
      }
    );
  }

  borrar(id: number, name: string) {
    if (confirm("¿Desea borrar la actividad \"" + name + "\"  del sistema?")) {
      this.actService.delete(id).subscribe(
        data => {
          this.activities = data;
        }
      )
    }
  }

}
