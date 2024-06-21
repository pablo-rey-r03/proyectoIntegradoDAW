import { Component } from '@angular/core';
import { User } from '../../model/user';
import { Role } from '../../model/role';
import { AuthService } from '../../service/auth.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-unauthorized',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './unauthorized.component.html',
  styleUrl: './unauthorized.component.css'
})
export class UnauthorizedComponent {

  constructor(private auth: AuthService) {
    auth.logout();
  }
}
