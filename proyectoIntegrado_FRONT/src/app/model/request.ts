import { Customer } from "./customer";

export interface Request {
  id: number,
  name: string,
  description: string,
  date: Date,
  location: string,
  customer: Customer
}
