import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Activity } from '../model/activity';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class ActivityService {
  private API_URL = environment.API_URL + "/act";

  constructor(private http: HttpClient) { }

  list() {
    return this.http.get<any>(`${this.API_URL}/list`);
  }

  byOfferer(id: number) {
    return this.http.get<any>(`${this.API_URL}/off/${id}`);
  }

  create(activity: any) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<any>(`${this.API_URL}/create`, activity, { headers });
  }

  delete(id: number) {
    return this.http.delete<any>(`${this.API_URL}/delete/${id}`);
  }

  getById(id: number) {
    return this.http.get<any>(`${this.API_URL}/detail/${id}`);
  }

  update(activity: any) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Activity>(`${this.API_URL}/update/${activity.id}`, activity, { headers });
  }
}
