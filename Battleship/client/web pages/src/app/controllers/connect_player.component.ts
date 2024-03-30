import {Component, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import { HttpClient } from '@angular/common/http';
import {Player} from "../models/Player";
import {Router, RouterOutlet} from "@angular/router";
import {WebsocketService} from "../websocket/websocket.service";

@Component({
  selector: 'app-root',
  templateUrl:'../views/connect_player.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, RouterOutlet],
  styles: [
  ],
})
export class ConnectPlayerComponent implements OnInit{


  constructor(private http: HttpClient, private playerModel: Player, private router: Router, private websocketService: WebsocketService){
  }

    ngOnInit(): void {
        // Connect to WebSocket when the component initializes
        this.websocketService.connect();
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

  async login(): Promise<void> {
    try {
      this.saveUser();
      console.log(this.player);
      const response = await this.http.post<any[]>("/battleship/v1.0/players", this.player).toPromise();
      console.log('Login successful', response);
      localStorage.setItem("player", this.player.username);
      this.router.navigate(['/lobbies']);
    } catch (error) {
      console.error('Login failed', error);
    }
  }

}
