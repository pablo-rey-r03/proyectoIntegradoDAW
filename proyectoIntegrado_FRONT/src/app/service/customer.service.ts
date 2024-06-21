import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../model/customer';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private API_URL: string = environment.API_URL + "/cus";

  constructor(private http: HttpClient) { }

  getByUserEmail(email: string) {
    return this.http.post<Customer>(`${this.API_URL}/detailByUser`, { email: email });
  }

  getById(id: number) {
    return this.http.get<any>(`${this.API_URL}/detail/${id}`);
  }

  create(customer: any) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Customer>(`${this.API_URL}/create`, customer, { headers });
  }

  delete(id: number) {
    return this.http.delete<Customer[]>(`${this.API_URL}/delete/${id}`);
  }

  update(customer: any, id: number) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Customer>(`${this.API_URL}/update/${id}`, customer, { headers })
  }
}
