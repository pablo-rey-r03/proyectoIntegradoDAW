import { Routes } from '@angular/router';
import { CustomerComponent } from './components/customer/customer.component';
import { RoleGuard } from './guard/role.guard';
import { OffererComponent } from './components/offerer/offerer.component';
import { UnauthorizedComponent } from './components/unauthorized/unauthorized.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterOffererComponent } from './components/register-offerer/register-offerer.component';
import { RegisterCustomerComponent } from './components/register-customer/register-customer.component';
import { Role } from './model/role';
import { OffererActivitiesComponent } from './components/offerer-activities/offerer-activities.component';
import { NewActComponent } from './components/new-act/new-act.component';
import { UpdateActComponent } from './components/update-act/update-act.component';
import { CustomerRequestsComponent } from './components/customer-requests/customer-requests.component';
import { NewReqComponent } from './components/new-req/new-req.component';
import { UpdateReqComponent } from './components/update-req/update-req.component';
import { UpdateOffComponent } from './components/update-off/update-off.component';
import { UpdateCusComponent } from './components/update-cus/update-cus.component';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'registerOfferer',
    component: RegisterOffererComponent
  },
  {
    path: 'registerCustomer',
    component: RegisterCustomerComponent
  },
  {
    path: "customer",
    component: CustomerComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: Role.ROLE_CUS
    }
  },
  {
    path: "offerer",
    component: OffererComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: Role.ROLE_OFF
    }
  },
  {
    path: "offererActivities/:id",
    component: OffererActivitiesComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: Role.ROLE_OFF
    }
  },
  {
    path: "customerRequests/:id",
    component: CustomerRequestsComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: Role.ROLE_CUS
    }
  },
  {
    path: "newAct",
    component: NewActComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: Role.ROLE_OFF
    }
  },
  {
    path: "newReq",
    component: NewReqComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: Role.ROLE_CUS
    }
  },
  {
    path: "updateAct/:id",
    component: UpdateActComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: Role.ROLE_OFF
    }
  },
  {
    path: "updateReq/:id",
    component: UpdateReqComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: Role.ROLE_CUS
    }
  },
  {
    path: "updateOff/:id",
    component: UpdateOffComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: Role.ROLE_OFF
    }
  },
  {
    path: "updateCus/:id",
    component: UpdateCusComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: Role.ROLE_CUS
    }
  },
  {
    path: "unauthorized",
    component: UnauthorizedComponent
  },
  {
    path: "**",
    redirectTo: "login"
  }
];
