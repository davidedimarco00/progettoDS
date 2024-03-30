import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { RouterModule } from '@angular/router';
import { InitialGridComponent } from './features/initial_grid.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterModule],
  template: `
    <h1>Battleship Free-For-All</h1>

    <button routerLink="connectPlayer">Inizia gioco</button>



    <router-outlet></router-outlet>
  `,
  styles: [
  ],
})
export class AppComponent {
  title = 'battleship';

}
