import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import { HttpClient } from '@angular/common/http';
import { Lobby } from '../models/Lobby';
import {Player} from "../models/Player";
import { Router } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl:'../views/lobbies.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  styles: [
  ],
})
export class LobbiesComponent {

  lobbies: Lobby[] = [];

  lobby = {
    name: ''
  };

  constructor(private http: HttpClient, private fb: FormBuilder, private playerModel: Player,  private router: Router){
    this.getLobbies();
  }

  player = {
    username: localStorage.getItem("player"),
  };

  connectLobby(id : number){
  this.http.put<any[]>(

    `/battleship/v1.0/lobbies/${id}`, this.player

  ).subscribe(
    (response) => {
      // Handle successful login response here
      console.log('Login successful', response);
      // You may want to store the authentication token or user data here
    },
    (error) => {
      // Handle error response here
      console.error('Login failed', error);
      // You may want to show an error message to the user
    }
  );

  this.router.navigate(['/initialGrid']);



  }

  getLobbies() {

    this.http.get<Lobby[]>(
      "/battleship/v1.0/lobbies"

    ).subscribe(
      res => {
        this.lobbies = res;
      }
  );
  }

  newLobby() {

    this.http.post<any[]>(
      "/battleship/v1.0/lobbies", this.lobby

    ).subscribe(
      (response) => {
        // Handle successful login response here
        console.log('New lobby', response);
        // You may want to store the authentication token or user data here
      },
      (error) => {
        // Handle error response here
        console.error('Login failed', error);
        // You may want to show an error message to the user
      }
    );
  }
}
