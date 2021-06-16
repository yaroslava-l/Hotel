import {Room} from './room';

export interface Reservation {
  id?: number;
  places: number;
  checkIn: Date;
  checkOut: Date;
  room_type: string;
  userByUserId: number;
  roomId?: Room;
  status: string;

}
