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
        background-color: black;
      }

      .selected {
        background-color: lightblue;
      }
      `],
})

export class InitialGridComponent implements OnInit{

        gridSize = 10;
        ships = [
          { size: 2, count: 1 },
          { size: 3, count: 1 },
          { size: 3, count: 1 },
          { size: 4, count: 1 },
          { size: 5, count: 1 }
        ];
        gameBoard: number[][] = [];
        selectedCells: { row: number, col: number }[] = [];

        constructor()
          {
              // guess what we'll do here ...
          }
        ngOnInit(): void {
          this.initializeBoard();
        }

        initializeBoard(): void {
          for (let i = 0; i < this.gridSize; i++) {
            this.gameBoard[i] = [];
            for (let j = 0; j < this.gridSize; j++) {
              this.gameBoard[i][j] = 0; // 0 represents empty cell
            }
          }
        }

        placeShip(row: number, col: number): void {
            console.log("Placing ship at row:", row, "col:", col);
          const selectedShip = this.ships.find(ship => ship.size === this.selectedShipSize);
          if (!selectedShip || selectedShip.count === 0) return;

          const shipLength = selectedShip.size;
          const isHorizontal = this.isHorizontal;
          const maxRow = isHorizontal ? row : row + shipLength - 1;
          const maxCol = isHorizontal ? col + shipLength - 1 : col;

          if (!this.isValidPosition(row, col, maxRow, maxCol)) return;

          for (let i = 0; i < shipLength; i++) {
            if (isHorizontal) {
              this.gameBoard[row][col + i] = shipLength;
            } else {
              this.gameBoard[row + i][col] = shipLength;
            }
          }

          selectedShip.count--;

          if (selectedShip.count === 0) {
            this.selectedShipSize = 0;
          }

          this.selectedCells.push({ row, col });
        }

        isValidPosition(startRow: number, startCol: number, endRow: number, endCol: number): boolean {
            // Check if the end coordinates are within the game board boundaries
            if (endRow >= this.gridSize || endCol >= this.gridSize) return false;

            // Check if any cell in the range is already occupied by a ship
            for (let i = startRow; i <= endRow; i++) {
              for (let j = startCol; j <= endCol; j++) {
                if (this.gameBoard[i][j] !== 0) {
                  console.log("Position is not valid: Cell at row", i, "column", j, "is already occupied.");
                  return false; // Cell is already occupied
                }
              }
            }

            console.log("Position is valid");
            return true; // All cells in the range are empty
          }

          isSelected(row: number, col: number): boolean {
            return this.selectedCells.some(cell => cell.row === row && cell.col === col);
          }

        selectedShipSize: number = 0;
        isHorizontal: boolean = true;


        selectShip(size: number): void {
            this.selectedShipSize = size;
            console.log("Selected ship size:", this.selectedShipSize);
          }
        toggleOrientation(): void {
          this.isHorizontal = !this.isHorizontal;
        }

    }
