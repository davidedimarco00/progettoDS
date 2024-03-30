import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { OnInit } from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl:'../views/initial_grid.component.html',
  imports: [CommonModule, RouterOutlet],
  styles: [`
    table {
        border-collapse: collapse;
      }

      td {
        width: 30px;
        height: 30px;
        border: 1px solid black;
        text-align: center;
      }

    .ship {
      width: 30px;
      height: 30px;
      border: 1px solid black;
      background-color: green;
    }

      .selected {
        background-color: lightblue;
      }
      `],
})

export class InitialGridComponent implements OnInit{

        gridSize = 10;
        ships = [
          { name: "AIRCRAFT_CARRIER", size: 2 },
          { name: "BATTLESHIP", size: 3},
          { name: "CRUISER", size: 3},
          { name: "DESTROYER", size: 4},
          { name: "SUBMARINE", size: 5}
        ];

        gameBoard: number[][] = [];

        playerShips: { shipType: string, cells: { point_x: number, point_y: number }[] }[] = [];
        constructor(private http: HttpClient)
          {
              // guess what we'll do here ...
          }
        ngOnInit(): void {
          this.initializeBoard();
          this.placeRandomShips();
        }

        initializeBoard(): void {
          for (let i = 0; i < this.gridSize; i++) {
            this.gameBoard[i] = [];
            for (let j = 0; j < this.gridSize; j++) {
              this.gameBoard[i][j] = 0; // 0 represents empty cell
            }
          }
        }
  generateNewPositions(): void {
    this.initializeBoard();
    this.placeRandomShips();
  }
  placeRandomShips(): void {
    this.playerShips = [];
    this.ships.forEach(ship => {
      let placed = false;
      while (!placed) {
        const isHorizontal = Math.random() < 0.5;
        const row = Math.floor(Math.random() * this.gridSize);
        const col = Math.floor(Math.random() * this.gridSize);
        const endRow = isHorizontal ? row : row + ship.size - 1;
        const endCol = isHorizontal ? col + ship.size - 1 : col;

        if (this.isValidPosition(row, col, endRow, endCol) && this.noAdjacentShips(row, col, endRow, endCol)) {
          const shipCoordinates: {  point_x: number,  point_y: number }[] = [];
          for (let i = 0; i < ship.size; i++) {
            if (isHorizontal) {
              this.gameBoard[row][col + i] = ship.size;
              shipCoordinates.push({  point_x: row,  point_y: col + i });
            } else {
              this.gameBoard[row + i][col] = ship.size;
              shipCoordinates.push({  point_x: row + i,  point_y: col});
            }
          }
          this.playerShips.push({ shipType: ship.name, cells: shipCoordinates });
          placed = true;
        }
      }
    });
    console.log(this.playerShips);
  }

  isValidPosition(startRow: number, startCol: number, endRow: number, endCol: number): boolean {
    if (endRow >= this.gridSize || endCol >= this.gridSize) return false;

    for (let i = startRow; i <= endRow; i++) {
      for (let j = startCol; j <= endCol; j++) {
        if (this.gameBoard[i][j] !== 0) return false; // Cell is already occupied
      }
    }

    return true;
  }

  noAdjacentShips(startRow: number, startCol: number, endRow: number, endCol: number): boolean {
    for (let i = startRow - 1; i <= endRow + 1; i++) {
      for (let j = startCol - 1; j <= endCol + 1; j++) {
        if (i >= 0 && i < this.gridSize && j >= 0 && j < this.gridSize) {
          if (this.gameBoard[i][j] !== 0) {
            return false;
          }
        }
      }
    }
    return true;
  }

  playerShipsToSend: { player: (string|null), ships: { shipType: string, cells: { point_x: number, point_y: number }[] }[]}[] = [];

  player = {
    username: localStorage.getItem("player"),
  };


  async savePositions(): Promise<void> {
    try {
      const playerShipsToSend = { player: this.player.username, ships: this.playerShips };
      console.log(playerShipsToSend);
      await this.http.post<any[]>("/battleship/v1.0/battlefield/ships", playerShipsToSend).toPromise();
      console.log('Ships saved successfully');
    } catch (error) {
      console.error('Saving ships failed', error);
    }
  }
}

