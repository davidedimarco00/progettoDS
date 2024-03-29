import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import { HttpClient } from '@angular/common/http';
import {Player} from "../models/Player";
import {Router, RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl:'../views/connect_player.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, RouterOutlet],
  styles: [
  ],
})
export class ConnectPlayerComponent {
  constructor(private http: HttpClient, private playerModel: Player, private router: Router){
  }

  player = {
    username: ''
  };

  saveUser(){
    this.playerModel.setUsername(this.player.username);
  }
  onSubmit() {
    console.log(this.player);

  }

  login() {
    this.saveUser();
    console.log(this.player);
    this.http.post<any[]>(
      "/battleship/v1.0/players", this.player
    ).subscribe(
      (response) => {
        // Handle successful login response here
        console.log('Login successful', response);
        localStorage.setItem("player", this.player.username)
        //
        // You may want to store the authentication token or user data here
      },
      (error) => {
        // Handle error response here
        console.error('Login failed', error);
        // You may want to show an error message to the user
      }
    );
    this.router.navigate(['/lobbies']);
  }

}
