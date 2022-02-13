import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Deporte } from 'src/app/models/deporte.model';
import { Modalidad } from 'src/app/models/modalidad.model';
import { DeporteService } from 'src/app/services/deporte.service';
import { ModalidadService } from 'src/app/services/modalidad.service';

// importaciones para las validaciones
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-modalidad',
  templateUrl: './add-modalidad.component.html',
  styleUrls: ['./add-modalidad.component.css']
})
export class AddModalidadComponent implements OnInit {

  deportes: Deporte[] = [];

  modalidad: Modalidad = {
    deporte: {
      idDeporte: 0,
    }
  };

  form: FormGroup | undefined;
  submitted = false;

  constructor(private deporteService: DeporteService, private modalidadService: ModalidadService, private formBuilder: FormBuilder) {
    this.deporteService.listarTodos().subscribe(
      (deportes) => this.deportes = deportes
    );
  }

  registraModalidad() {
    console.log(this.modalidad);

    this.submitted = true;

    //!this.form.invalid ==> si la validacion es correcta
    if (!this.form.invalid) {
      this.modalidadService.registrar(this.modalidad).subscribe(
        response => {
          console.log(response.mensaje);
          alert(response.mensaje);
        },
        error => {
          console.log(error);
        },
      );
    }
  }

  ngOnInit(): void {

    this.form = this.formBuilder.group(
      {
        nombre : ['', Validators.required]
      });

  }



}
