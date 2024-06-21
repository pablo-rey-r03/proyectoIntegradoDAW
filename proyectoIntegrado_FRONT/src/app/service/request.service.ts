import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Request } from '../model/request';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class RequestService {
  private API_URL = environment.API_URL + "/req";

  constructor(private http: HttpClient) { }

  list() {
    return this.http.get<Request[]>(`${this.API_URL}/list`);
  }

  byCustomer(id: number) {
    return this.http.get<any>(`${this.API_URL}/cus/${id}`);
  }

  delete(id: number) {
    return this.http.delete<any>(`${this.API_URL}/delete/${id}`);
  }

  create(request: any) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<any>(`${this.API_URL}/create`, request, { headers });
  }

  byId(id: number) {
    return this.http.get<any>(`${this.API_URL}/detail/${id}`);
  }

  update(request: any) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Request>(`${this.API_URL}/update/${request.id}`, request, { headers });
  }
}
