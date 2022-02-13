import { Component, OnInit } from '@angular/core';
import { FilroDocente } from 'src/app/models/filro-docente.model';
import { Ubigeo } from 'src/app/models/ubigeo.model';
import { DocenteService } from 'src/app/services/docente.service';
import { UbigeoService } from 'src/app/services/ubigeo.service';

@Component({
  selector: 'app-consulta-docente',
  templateUrl: './consulta-docente.component.html',
  styleUrls: ['./consulta-docente.component.css']
})
export class ConsultaDocenteComponent implements OnInit {

  //Valores de formulario
  nombre: string = "";
  dni: string = "";
  selDepartamento: string = "";
  selProvincia: string = "";
  selDistrito: number = 0;

  //Arreglos de los combos de Ubigeo
  arrayDepartamentos: string[] = [];
  arrayProvincias: string[] = [];
  arrayDistritos: Ubigeo[] = [];

  //Arreglo de la grilla
  arrayDocentes: FilroDocente[] = [];

  constructor(private ubigeoService: UbigeoService,
              private docenteService: DocenteService) {
    this.ubigeoService.listarDepartamento().subscribe(
      response => this.arrayDepartamentos = response
    );
  }

  consultaDocente(){
    this.docenteService.consultaDocente(this.nombre, this.dni, this.selDistrito).subscribe(
        response => this.arrayDocentes = response.list
    );
  }

  listaProvincia() {
    this.ubigeoService.listaProvincias(this.selDepartamento).subscribe(
      response => this.arrayProvincias = response
    );
  }

  listaDistrito() {
    this.ubigeoService.listaDistritos(this.selDepartamento, this.selProvincia).subscribe(
      response => this.arrayDistritos = response
    );
  }



  ngOnInit(): void {
  }


}
