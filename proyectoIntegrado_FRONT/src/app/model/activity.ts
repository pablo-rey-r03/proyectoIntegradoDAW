import { Offerer } from "./offerer";

export interface Activity {
  id: number,
  availableQuota: number,
  date: Date,
  description: string,
  duration: string,
  location: string,
  maxQuota: number,
  name: string,
  offerer: Offerer,
  price: number,
  providedMats?: string,
  requiredMats?: string,
  recommendedFormation?: string
}
