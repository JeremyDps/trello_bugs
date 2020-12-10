import { Injectable } from '@angular/core';
import {HttpClient } from '@angular/common/http';
import { Bug } from '../models/bug';
import { Observable} from 'rxjs';
import { environment } from 'src/environments/environment';
import { Commentaire } from '../models/commentaire';

@Injectable({
  providedIn: 'root'
})
export class BugsService {

  constructor(private http: HttpClient) { }

  public getBugsList(): Observable<Bug[]>{
    return this.http.get<Bug[]>(`${environment.url}/bugs`);
  }

  public getBugsById(id: number): Observable<Bug>{
    return this.http.get<Bug>(`${environment.url}/bugs/${id}`);
  }

  public deleteBug(id: number): Observable<any>{
    return this.http.delete(`${environment.url}/bugs/${id}`);
  }

  public createBug(bug: Bug): Observable<Bug>{
    return this.http.post<Bug>(`${environment.url}/bugs`, bug);
  }

  public getCommentairesList(): Observable<Commentaire[]> {
    return this.http.get<Commentaire[]>(`${environment.url}/commentaires`);
  }
}
