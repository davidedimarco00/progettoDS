import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import { HttpClient } from '@angular/common/http';
import { Lobby } from '../models/Lobby';
import {Player} from "../models/Player";
import { Router } from '@angular/router';
import { interval } from 'rxjs';
import { switchMap, take } from 'rxjs/operators';
@Component({
  selector: 'app-root',
  templateUrl:'../views/lobbies.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  styles: [
  ],
})
export class LobbiesComponent {

  lobbies: Lobby[] | undefined = [];

  lobby = {
    name: ''
  };

  constructor(private http: HttpClient, private fb: FormBuilder, private playerModel: Player,  private router: Router){
    this.getLobbies();
  }

  player = {
    username: localStorage.getItem("player"),
  };

  async connectLobby(id: number): Promise<void> {
    try {
      const response = await this.http.put<any[]>(`/battleship/v1.0/lobbies/${id}`, this.player).toPromise();
      console.log('Login successful', response);
      this.router.navigate(['/initialGrid']);
    } catch (error) {
      console.error('Login failed', error);
    }
  }

  async getLobbies(): Promise<void> {
    for (let i = 0; i < 1; i++) {
      await interval(1000).pipe(
        take(1),
        switchMap(() => this.http.get<Lobby[]>("/battleship/v1.0/lobbies"))
      ).toPromise().then(res => {
        this.lobbies = res;
      });
    }
  }

  async newLobby(): Promise<void> {
    try {
      const response = await this.http.post<any[]>("/battleship/v1.0/lobbies", this.lobby).toPromise();
      console.log('New lobby', response);
      window.location.reload();
    } catch (error) {
      console.error('Creating lobby failed', error);
    }
  }
}
