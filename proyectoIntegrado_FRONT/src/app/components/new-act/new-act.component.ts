import { Component } from '@angular/core';
import { Activity } from '../../model/activity';
import { ActivityService } from '../../service/activity.service';
import { FormsModule } from '@angular/forms';
import { OffererService } from '../../service/offerer.service';
import { AuthService } from '../../service/auth.service';
import { Router, RouterLink } from '@angular/router';
import { of } from 'rxjs';
import { Offerer } from '../../model/offerer';

@Component({
  selector: 'app-new-act',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './new-act.component.html',
  styleUrl: './new-act.component.css'
})
export class NewActComponent {
  public activity: any = {
    name: '',
    description: '',
    date: '',
    location: '',
    price: 0,
    duration: '',
    maxQuota: 0,
    availableQuota: 0,
    requiredMats: null,
    providedMats: null,
    recommendedFormation: null,
    offererId: -1
  };
  public offerer: Offerer = <Offerer>{};

  constructor(private actService: ActivityService, private offService: OffererService, private auth: AuthService, private router: Router) {
    this.offService.getByUserEmail(this.auth.getUserEmail()).subscribe(
      data => {
        this.offerer = data;
        this.activity.offererId = data.id;
      }
    );
  }

  onSubmit() {
    this.activity.availableQuota = this.activity.maxQuota;
    this.actService.create(this.activity).subscribe(
      data => {
        console.log(data);
        this.router.navigate(["/offererActivities/" + this.activity.offererId])
      }
    )
  }

}
