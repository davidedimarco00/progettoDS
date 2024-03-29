import {
  RouterOutlet
} from "./chunk-AOGEK5Q2.js";
import {
  CommonModule,
  NgForOf,
  ɵsetClassDebugInfo,
  ɵɵStandaloneFeature,
  ɵɵadvance,
  ɵɵclassProp,
  ɵɵdefineComponent,
  ɵɵelement,
  ɵɵelementEnd,
  ɵɵelementStart,
  ɵɵgetCurrentView,
  ɵɵlistener,
  ɵɵnextContext,
  ɵɵproperty,
  ɵɵresetView,
  ɵɵrestoreView,
  ɵɵtemplate,
  ɵɵtext
} from "./chunk-4KJKHX6B.js";

// src/app/features/initial_grid.component.ts
function InitialGridComponent_tr_4_td_1_Template(rf, ctx) {
  if (rf & 1) {
    const _r8 = \u0275\u0275getCurrentView();
    \u0275\u0275elementStart(0, "td", 2);
    \u0275\u0275listener("click", function InitialGridComponent_tr_4_td_1_Template_td_click_0_listener() {
      const restoredCtx = \u0275\u0275restoreView(_r8);
      const j_r5 = restoredCtx.index;
      const i_r2 = \u0275\u0275nextContext().index;
      const ctx_r6 = \u0275\u0275nextContext();
      return \u0275\u0275resetView(ctx_r6.placeShip(i_r2, j_r5));
    });
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const cell_r4 = ctx.$implicit;
    const j_r5 = ctx.index;
    const i_r2 = \u0275\u0275nextContext().index;
    const ctx_r3 = \u0275\u0275nextContext();
    \u0275\u0275classProp("ship", cell_r4 !== 0)("selected", ctx_r3.isSelected(i_r2, j_r5));
  }
}
function InitialGridComponent_tr_4_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "tr");
    \u0275\u0275template(1, InitialGridComponent_tr_4_td_1_Template, 1, 4, "td", 1);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const row_r1 = ctx.$implicit;
    \u0275\u0275advance();
    \u0275\u0275property("ngForOf", row_r1);
  }
}
var _InitialGridComponent = class _InitialGridComponent {
  constructor() {
    this.gridSize = 10;
    this.ships = [
      { size: 2, count: 1 },
      { size: 3, count: 1 },
      { size: 3, count: 1 },
      { size: 4, count: 1 },
      { size: 5, count: 1 }
    ];
    this.gameBoard = [];
    this.selectedCells = [];
    this.selectedShipSize = 0;
    this.isHorizontal = true;
  }
  ngOnInit() {
    this.initializeBoard();
  }
  initializeBoard() {
    for (let i = 0; i < this.gridSize; i++) {
      this.gameBoard[i] = [];
      for (let j = 0; j < this.gridSize; j++) {
        this.gameBoard[i][j] = 0;
      }
    }
  }
  placeShip(row, col) {
    console.log("Placing ship at row:", row, "col:", col);
    const selectedShip = this.ships.find((ship) => ship.size === this.selectedShipSize);
    if (!selectedShip || selectedShip.count === 0)
      return;
    const shipLength = selectedShip.size;
    const isHorizontal = this.isHorizontal;
    const maxRow = isHorizontal ? row : row + shipLength - 1;
    const maxCol = isHorizontal ? col + shipLength - 1 : col;
    if (!this.isValidPosition(row, col, maxRow, maxCol))
      return;
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
  isValidPosition(startRow, startCol, endRow, endCol) {
    if (endRow >= this.gridSize || endCol >= this.gridSize)
      return false;
    for (let i = startRow; i <= endRow; i++) {
      for (let j = startCol; j <= endCol; j++) {
        if (this.gameBoard[i][j] !== 0) {
          console.log("Position is not valid: Cell at row", i, "column", j, "is already occupied.");
          return false;
        }
      }
    }
    console.log("Position is valid");
    return true;
  }
  isSelected(row, col) {
    return this.selectedCells.some((cell) => cell.row === row && cell.col === col);
  }
  selectShip(size) {
    this.selectedShipSize = size;
    console.log("Selected ship size:", this.selectedShipSize);
  }
  toggleOrientation() {
    this.isHorizontal = !this.isHorizontal;
  }
};
_InitialGridComponent.\u0275fac = function InitialGridComponent_Factory(t) {
  return new (t || _InitialGridComponent)();
};
_InitialGridComponent.\u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _InitialGridComponent, selectors: [["app-root"]], standalone: true, features: [\u0275\u0275StandaloneFeature], decls: 6, vars: 1, consts: [[4, "ngFor", "ngForOf"], [3, "ship", "selected", "click", 4, "ngFor", "ngForOf"], [3, "click"]], template: function InitialGridComponent_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "div")(1, "h3");
    \u0275\u0275text(2, "Battleship Game");
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(3, "table");
    \u0275\u0275template(4, InitialGridComponent_tr_4_Template, 2, 1, "tr", 0);
    \u0275\u0275elementEnd()();
    \u0275\u0275element(5, "router-outlet");
  }
  if (rf & 2) {
    \u0275\u0275advance(4);
    \u0275\u0275property("ngForOf", ctx.gameBoard);
  }
}, dependencies: [CommonModule, NgForOf, RouterOutlet], styles: ["\n\ntable[_ngcontent-%COMP%] {\n  border-collapse: collapse;\n}\ntd[_ngcontent-%COMP%] {\n  width: 30px;\n  height: 30px;\n  border: 1px solid black;\n  text-align: center;\n}\n.ship[_ngcontent-%COMP%] {\n  background-color: gray;\n}\n.selected[_ngcontent-%COMP%] {\n  background-color: lightblue;\n}\n/*# sourceMappingURL=initial_grid.component.css.map */"] });
var InitialGridComponent = _InitialGridComponent;
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(InitialGridComponent, { className: "InitialGridComponent", filePath: "src\\app\\features\\initial_grid.component.ts", lineNumber: 51 });
})();
export {
  InitialGridComponent
};
//# sourceMappingURL=chunk-SBWSP32N.js.map
