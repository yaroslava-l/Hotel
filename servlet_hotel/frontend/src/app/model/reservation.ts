import {Room} from './room';

export interface Reservation {
  id?: number;
  places: number;
  checkIn: Date;
  checkOut: Date;
  type: string;
  userByUserId: number;
  roomId?: Room;
  status: string;

}
