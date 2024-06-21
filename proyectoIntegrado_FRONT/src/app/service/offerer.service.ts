import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Offerer } from '../model/offerer';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class OffererService {
  private API_URL = environment.API_URL + "/off";

  constructor(private http: HttpClient) { }

  getByUserEmail(email: string) {
    return this.http.post<Offerer>(`${this.API_URL}/detailByUser`, { email: email });
  }

  getById(id: number) {
    return this.http.get<any>(`${this.API_URL}/detail/${id}`);
  }

  create(offerer: any) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Offerer>(`${this.API_URL}/create`, offerer, { headers });
  }

  delete(id: number) {
    return this.http.delete<Offerer[]>(`${this.API_URL}/delete/${id}`);
  }

  update(offerer: any, id: number) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Offerer>(`${this.API_URL}/update/${id}`, offerer, { headers })
  }
}
