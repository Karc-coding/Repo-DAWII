import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Deporte } from '../models/deporte.model';

const baseUrl = 'http://localhost:8090/rest/util';

@Injectable({
  providedIn: 'root'
})

export class DeporteService {

  constructor(private http:HttpClient) { }

    listarTodos(): Observable<Deporte[]>{
      return this.http.get<Deporte[]>(baseUrl+"/deporte");
    }

}
