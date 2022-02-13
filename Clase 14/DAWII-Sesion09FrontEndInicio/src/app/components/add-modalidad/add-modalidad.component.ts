import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Deporte } from 'src/app/models/deporte.model';
import { Modalidad } from 'src/app/models/modalidad.model';
import { DeporteService } from 'src/app/services/deporte.service';
import { ModalidadService } from 'src/app/services/modalidad.service';

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

  form: FormGroup = this.formBuilder.group(
    {
      nombre: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(30)]],
      numHombres: ['', Validators.required],
      numMujeres: ['', Validators.required],
      edadMinima: ['', Validators.required],
      edadMaxima: ['', Validators.required],
      sede: ['', Validators.required],
      deporte: ['', Validators.required],
    }
  );;

  submitted = false;

  constructor(private deporteService: DeporteService, private modalidadService: ModalidadService, private formBuilder: FormBuilder) {
    this.deporteService.listarTodos().subscribe(
      (deportes) => this.deportes = deportes
    );
  }

  registraModalidad() {
    console.log(this.modalidad);

    this.submitted = true;

    // !this.form.invalid == la validacion es correcta
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
  }



}
