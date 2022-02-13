import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const baseUrl = "http://localhost:8090/rest/docente"

@Injectable({
  providedIn: 'root'
})
export class DocenteService {

  constructor(private http: HttpClient) { }

  consultaDocente(nombre: string, dni: string, idUbigeo: number): Observable<any>{
    const params = new HttpParams()
      .set("name", nombre)
      .set("dni", dni)
      .set("idUbigeo", idUbigeo);

    return this.http.get(baseUrl + "/queryDocenteForParameters", {params});
  }

}
