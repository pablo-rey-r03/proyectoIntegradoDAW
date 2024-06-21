import { Role } from "./role";

export interface User {
  email: string,
  password: string,
  role: Role
}
