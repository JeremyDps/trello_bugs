import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Developer } from '../models/developer';

@Injectable({
  providedIn: 'root'
})
export class DevelopersService {

  constructor(private httpCLient: HttpClient) { }

  public getDevelopersList(): Observable<Developer[]> {
    return this.httpCLient.get<Developer[]>(`${environment.url}/developers`);
  }

  public getDevelopersById(id: number): Observable<Developer> {
    return this.httpCLient.get<Developer>(`${environment.url}/bugs/${id}`);
  }

  public deleteDeveloper(id: number): Observable<any>{
    return this.httpCLient.delete(`${environment.url}/developers/${id}`);
  }

  public createDeveloper(dev: Developer): Observable<Developer> {
    return this.httpCLient.post<Developer>(`${environment.url}/developers`, dev);
  }

}
