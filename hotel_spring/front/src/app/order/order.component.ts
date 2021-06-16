import {Component, OnInit} from '@angular/core';
import {ReservationService} from '../service/reservation.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LoginComponent} from '../login/login.component';
import {OAuthService} from 'angular-oauth2-oidc';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  reservationForm: FormGroup;
  user = 1;
  state = 'PENDING';

  constructor(private reservationService: ReservationService,
              private formBuilder: FormBuilder,
              private oauthService: OAuthService) {
  }

  ngOnInit(): void {
    this.reservationForm = this.formBuilder.group(
      {
        places: ['', Validators.required],
        checkIn: ['', Validators.required],
        checkOut: ['', Validators.required],
        room_type: ['', Validators.required]
      }
    );
    console.log(this.oauthService.getIdentityClaims());
  }

  buildReservation(): any {
    const reservation = {
      places: this.inputs.places.value,
      room_type: this.inputs.room_type.value,
      checkIn: this.inputs.checkIn.value,
      checkOut: this.inputs.checkOut.value,
      userId: this.user,
      status: this.state
    };
    console.log(reservation);
    return reservation;
  }

  get inputs(): any {
    return this.reservationForm.controls;
  }

  createNewReservationByUser(): any {
    return this.reservationService.createReservationByUser(this.buildReservation()).subscribe(d => console.log(d));
  }

}
