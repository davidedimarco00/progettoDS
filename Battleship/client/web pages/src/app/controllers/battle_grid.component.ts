import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: "../views/battle_grid.component.html",
  imports: [CommonModule, RouterOutlet],
  styles: [
  ],
})
export class BattleGridComponent {
  title = 'battleship';
  rows = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'];
  columns = Array.from({ length: 10 }, (_, i) => i + 1);
  ships: string[] = ['A1', 'A2', 'A3', 'C6', 'C7', 'C8', 'C9'];
  ships_u1: string[] = ['A1', 'A2', 'A3', 'C6', 'C7', 'C8', 'C9'];
  ships_u2: string[] = ['A1', 'A2', 'A3', 'C6', 'C7', 'C8', 'C9'];
  ships_u3: string[] = ['A1', 'A2', 'A3', 'C6', 'C7', 'C8', 'C9'];
  hits_recived: string[] = ['A1', 'B2','C6'];
  hits: string[] = [];

  // Function to check if a cell is a hit

  isHit(cell: string): boolean {
    return this.hits_recived.includes(cell) && this.ships.includes(cell);}

    isSelected(cell: string): boolean {
      return this.ships.includes(cell);}

      getCellColor(cell: string): string {
        if (this.hits_recived.includes(cell) && this.ships.includes(cell)) {
          return 'red'; // Hit cells are red
        } else if (this.ships.includes(cell)) {
          return 'green'; // Additional conditions
        } else {
          return '';
        }
      }



      clickedCells: string[] = [];

      // Function to toggle the clicked state and change color
      toggleCellClick(coordinates: string): void {
        if (this.isCellClicked(coordinates)) {
          return; // Skip further processing if the cell is already clicked
        }

        const index = this.clickedCells.indexOf(coordinates);

        if (index == -1) {
          this.hits.push(coordinates);
          this.clickedCells.push(coordinates); // Remove from array if already clicked
        }


      }

      isCellClicked(coordinates: string): boolean {
        return this.clickedCells.includes(coordinates);
      }
}
