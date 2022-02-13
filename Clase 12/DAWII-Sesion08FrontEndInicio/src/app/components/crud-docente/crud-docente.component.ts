import { Component, OnInit } from '@angular/core';
import { Docente } from 'src/app/models/docente.model';
import { Ubigeo } from 'src/app/models/ubigeo.model';
import { DocenteService } from 'src/app/services/docente.service';
import { UbigeoService } from 'src/app/services/ubigeo.service';

@Component({
  selector: 'app-crud-docente',
  templateUrl: './crud-docente.component.html',
  styleUrls: ['./crud-docente.component.css']
})
export class CrudDocenteComponent implements OnInit {

  //Para la Grilla
  docentes: Docente[] = [];
  filtro: string = "";

  //Para el ubigeo
  departamentos: string[] = [];;
  provincias: string[] = [];;
  distritos: Ubigeo[] = [];;


  //Json para registrar o actualizar
  docente: Docente = {
    idDocente: 0,
    nombre: "",
    dni: "",
    estado: 1,
    ubigeo: {
      idUbigeo: 0,
      departamento: "-1",
      provincia: "-1",
      distrito: "-1",
    }
  };

  constructor(private docenteService: DocenteService,
    private ubigeoService: UbigeoService) {
    this.ubigeoService.listarDepartamento().subscribe(
      response => this.departamentos = response
    );
  }

  cargaProvincia() {
    this.ubigeoService.listaProvincias(this.docente.ubigeo?.departamento).subscribe(
      response => this.provincias = response
    );
  }

  cargaDistrito() {
    this.ubigeoService.listaDistritos(this.docente.ubigeo?.departamento, this.docente.ubigeo?.provincia).subscribe(
      response => this.distritos = response
    );
  }

  ngOnInit(): void {
  }

  consulta() {
    this.docenteService.consultaDocente(this.filtro).subscribe(
      response => this.docentes = response
    );
  }

  registra() {
    this.docenteService.registraDocente(this.docente).subscribe(
      response => {
        alert(response.mensaje);

        this.docenteService.consultaDocente(this.filtro).subscribe(
          response => this.docentes = response
        );

        this.docente = {
          idDocente: 0,
          nombre: "",
          dni: "",
          estado: 1,
          ubigeo: {
            idUbigeo: 0,
            departamento: "-1",
            provincia: "-1",
            distrito: "-1",
          }
        };
      }
    );
  }

  busca(aux: Docente) {
    this.docente = aux;

    this.ubigeoService.listaProvincias(this.docente.ubigeo?.departamento).subscribe(
      response => this.provincias = response
    );

    this.ubigeoService.listaDistritos(this.docente.ubigeo?.departamento, this.docente.ubigeo?.provincia).subscribe(
      response => this.distritos = response
    );
  }

  actualiza() {
    this.docenteService.actualizaDocente(this.docente).subscribe(
      response => {
        alert(response.mensaje);

        this.docenteService.consultaDocente(this.filtro).subscribe(
          response => this.docentes = response
        );

        this.docente = {
          idDocente: 0,
          nombre: "",
          dni: "",
          estado: 1,
          ubigeo: {
            idUbigeo: 0,
            departamento: "-1",
            provincia: "-1",
            distrito: "-1",
          }
        };
      }
    );
  }

  getEstado(estado: number): string {
    var salida = "";
    if (estado == 1) {
      salida = "Activo";
    } else {
      salida = "Inactivo"
    }
    return salida == null ? "" : salida;
  }

  getBtnEstado(estado: number): string {
    var salida = "";
    if (estado == 1) {
      salida = "Desactivar";
    } else {
      salida = "Activar"
    }
    return salida == null ? "" : salida;
  }

  cambiarEstado(idDocente: number, estado:number){
    if (estado == 0) {
      estado = 1;
    } else {
      estado = 0;
    }

    this.docente.idDocente = idDocente;
    this.docente.estado = estado;

    this.docenteService.actualizaEstadoDocente(this.docente).subscribe(
      response => {
        alert(response.mensaje);

        this.docenteService.consultaDocente(this.filtro).subscribe(
          response => this.docentes = response
        );

        this.docente = {
          idDocente: 0,
          nombre: "",
          dni: "",
          estado: 1,
          ubigeo: {
            idUbigeo: 0,
            departamento: "-1",
            provincia: "-1",
            distrito: "-1",
          }
        };
      },
      error => {
        console.log(error);
      }
    );

  }

}
