import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Reservation} from '../model/reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  url = 'http://localhost:8081/hotel_project_war/reservation';
  urlCabinet = 'http://localhost:8081/hotel_project_war/cabinet';
  urlAdmin = 'http://localhost:8081/hotel_project_war/admin';

  constructor(private httpClient: HttpClient) {
  }


  getAllReservationsByUser(id: number): Observable<Reservation[]> {
    return this.httpClient.get<Reservation[]>(this.urlCabinet + '?user=' + id);
  }

  createReservationByUser(reservation: Reservation): any {
    console.log(reservation);
    return this.httpClient.post<Reservation>(this.url, {reservation});
  }

  getAllReservations(): Observable<Reservation[]> {
    return this.httpClient.get<Reservation[]>(this.url);
  }

  updateReservation(roomId: number, id: number): Observable<any> {
    return this.httpClient.get<any>(this.urlAdmin + '?room_id=' + roomId + '&id=' + id);
  }
}
