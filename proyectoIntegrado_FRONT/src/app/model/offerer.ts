import { Activity } from "./activity";
import { User } from "./user";

export interface Offerer {
  id: number,
  name: string,
  surname: string,
  address: string,
  birthDate: Date,
  phoneNumber: string,
  formation: string,
  activities: Activity[],
  user: User
}
