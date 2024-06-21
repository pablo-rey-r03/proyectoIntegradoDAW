import { User } from "./user";

export interface Customer {
  id: number,
  name: string,
  surname: string,
  address: string,
  phoneNumber: string,
  birthDate: Date,
  user: User
}
