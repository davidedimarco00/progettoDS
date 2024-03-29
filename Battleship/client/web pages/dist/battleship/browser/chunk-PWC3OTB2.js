import {
  RouterOutlet
} from "./chunk-AOGEK5Q2.js";
import {
  CommonModule,
  NgForOf,
  NgIf,
  NgStyle,
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
  ɵɵpropertyInterpolate,
  ɵɵpureFunction1,
  ɵɵresetView,
  ɵɵrestoreView,
  ɵɵtemplate,
  ɵɵtext,
  ɵɵtextInterpolate,
  ɵɵtextInterpolate1
} from "./chunk-4KJKHX6B.js";

// src/app/features/battle_grid.component.ts
function BattleGridComponent_th_10_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "th");
    \u0275\u0275text(1);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const column_r5 = ctx.$implicit;
    \u0275\u0275advance();
    \u0275\u0275textInterpolate(column_r5);
  }
}
var _c0 = (a0) => ({ "background-color": a0 });
function BattleGridComponent_tr_12_td_3_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275element(0, "td", 5);
  }
  if (rf & 2) {
    const column_r8 = ctx.$implicit;
    const row_r6 = \u0275\u0275nextContext().$implicit;
    const ctx_r7 = \u0275\u0275nextContext();
    \u0275\u0275propertyInterpolate("id", row_r6 + column_r8);
    \u0275\u0275property("ngStyle", \u0275\u0275pureFunction1(2, _c0, ctx_r7.getCellColor(row_r6 + column_r8)));
  }
}
function BattleGridComponent_tr_12_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "tr")(1, "th");
    \u0275\u0275text(2);
    \u0275\u0275elementEnd();
    \u0275\u0275template(3, BattleGridComponent_tr_12_td_3_Template, 1, 4, "td", 4);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const row_r6 = ctx.$implicit;
    const ctx_r1 = \u0275\u0275nextContext();
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(row_r6);
    \u0275\u0275advance();
    \u0275\u0275property("ngForOf", ctx_r1.columns);
  }
}
function BattleGridComponent_th_20_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "th");
    \u0275\u0275text(1);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const column_r10 = ctx.$implicit;
    \u0275\u0275advance();
    \u0275\u0275textInterpolate(column_r10);
  }
}
function BattleGridComponent_tr_22_td_3_Template(rf, ctx) {
  if (rf & 1) {
    const _r16 = \u0275\u0275getCurrentView();
    \u0275\u0275elementStart(0, "td", 7);
    \u0275\u0275listener("click", function BattleGridComponent_tr_22_td_3_Template_td_click_0_listener() {
      const restoredCtx = \u0275\u0275restoreView(_r16);
      const column_r13 = restoredCtx.$implicit;
      const row_r11 = \u0275\u0275nextContext().$implicit;
      const ctx_r14 = \u0275\u0275nextContext();
      return \u0275\u0275resetView(ctx_r14.toggleCellClick(row_r11 + column_r13));
    });
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const column_r13 = ctx.$implicit;
    const row_r11 = \u0275\u0275nextContext().$implicit;
    const ctx_r12 = \u0275\u0275nextContext();
    \u0275\u0275classProp("clicked", ctx_r12.isCellClicked(row_r11 + column_r13));
    \u0275\u0275propertyInterpolate("id", row_r11 + column_r13);
  }
}
function BattleGridComponent_tr_22_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "tr")(1, "th");
    \u0275\u0275text(2);
    \u0275\u0275elementEnd();
    \u0275\u0275template(3, BattleGridComponent_tr_22_td_3_Template, 1, 3, "td", 6);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const row_r11 = ctx.$implicit;
    const ctx_r3 = \u0275\u0275nextContext();
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(row_r11);
    \u0275\u0275advance();
    \u0275\u0275property("ngForOf", ctx_r3.columns);
  }
}
function BattleGridComponent_div_23_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "div")(1, "h2");
    \u0275\u0275text(2, "Hits List:");
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(3, "p");
    \u0275\u0275text(4);
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(5, "p");
    \u0275\u0275text(6);
    \u0275\u0275elementEnd()();
  }
  if (rf & 2) {
    const ctx_r4 = \u0275\u0275nextContext();
    \u0275\u0275advance(4);
    \u0275\u0275textInterpolate1("Your hits: ", ctx_r4.hits.join(", "), "");
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate1("Hits recived: ", ctx_r4.hits_recived.join(", "), "");
  }
}
var _BattleGridComponent = class _BattleGridComponent {
  constructor() {
    this.title = "battleship";
    this.rows = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];
    this.columns = Array.from({ length: 10 }, (_, i) => i + 1);
    this.ships = ["A1", "A2", "A3", "C6", "C7", "C8", "C9"];
    this.ships_u1 = ["A1", "A2", "A3", "C6", "C7", "C8", "C9"];
    this.ships_u2 = ["A1", "A2", "A3", "C6", "C7", "C8", "C9"];
    this.ships_u3 = ["A1", "A2", "A3", "C6", "C7", "C8", "C9"];
    this.hits_recived = ["A1", "B2", "C6"];
    this.hits = [];
    this.clickedCells = [];
  }
  // Function to check if a cell is a hit
  isHit(cell) {
    return this.hits_recived.includes(cell) && this.ships.includes(cell);
  }
  isSelected(cell) {
    return this.ships.includes(cell);
  }
  getCellColor(cell) {
    if (this.hits_recived.includes(cell) && this.ships.includes(cell)) {
      return "red";
    } else if (this.ships.includes(cell)) {
      return "green";
    } else {
      return "";
    }
  }
  // Function to toggle the clicked state and change color
  toggleCellClick(coordinates) {
    if (this.isCellClicked(coordinates)) {
      return;
    }
    const index = this.clickedCells.indexOf(coordinates);
    if (index == -1) {
      this.hits.push(coordinates);
      this.clickedCells.push(coordinates);
    }
  }
  isCellClicked(coordinates) {
    return this.clickedCells.includes(coordinates);
  }
};
_BattleGridComponent.\u0275fac = function BattleGridComponent_Factory(t) {
  return new (t || _BattleGridComponent)();
};
_BattleGridComponent.\u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _BattleGridComponent, selectors: [["app-root"]], standalone: true, features: [\u0275\u0275StandaloneFeature], decls: 25, vars: 5, consts: [[1, "tables-container"], [1, "table-container"], [4, "ngFor", "ngForOf"], [4, "ngIf"], [3, "ngStyle", "id", 4, "ngFor", "ngForOf"], [3, "ngStyle", "id"], [3, "id", "clicked", "click", 4, "ngFor", "ngForOf"], [3, "id", "click"]], template: function BattleGridComponent_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "h1");
    \u0275\u0275text(1, "Battleship Free-For-All");
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(2, "div", 0)(3, "div", 1)(4, "table")(5, "caption");
    \u0275\u0275text(6, "Your Grid");
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(7, "thead")(8, "tr");
    \u0275\u0275element(9, "th");
    \u0275\u0275template(10, BattleGridComponent_th_10_Template, 2, 1, "th", 2);
    \u0275\u0275elementEnd()();
    \u0275\u0275elementStart(11, "tbody");
    \u0275\u0275template(12, BattleGridComponent_tr_12_Template, 4, 2, "tr", 2);
    \u0275\u0275elementEnd()()();
    \u0275\u0275elementStart(13, "div", 1)(14, "table", 1)(15, "caption");
    \u0275\u0275text(16, "Battle Grid");
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(17, "thead")(18, "tr");
    \u0275\u0275element(19, "th");
    \u0275\u0275template(20, BattleGridComponent_th_20_Template, 2, 1, "th", 2);
    \u0275\u0275elementEnd()();
    \u0275\u0275elementStart(21, "tbody");
    \u0275\u0275template(22, BattleGridComponent_tr_22_Template, 4, 2, "tr", 2);
    \u0275\u0275elementEnd()()()();
    \u0275\u0275template(23, BattleGridComponent_div_23_Template, 7, 2, "div", 3);
    \u0275\u0275element(24, "router-outlet");
  }
  if (rf & 2) {
    \u0275\u0275advance(10);
    \u0275\u0275property("ngForOf", ctx.columns);
    \u0275\u0275advance(2);
    \u0275\u0275property("ngForOf", ctx.rows);
    \u0275\u0275advance(8);
    \u0275\u0275property("ngForOf", ctx.columns);
    \u0275\u0275advance(2);
    \u0275\u0275property("ngForOf", ctx.rows);
    \u0275\u0275advance();
    \u0275\u0275property("ngIf", ctx.hits.length > 0);
  }
}, dependencies: [CommonModule, NgForOf, NgIf, NgStyle, RouterOutlet], encapsulation: 2 });
var BattleGridComponent = _BattleGridComponent;
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(BattleGridComponent, { className: "BattleGridComponent", filePath: "src\\app\\features\\battle_grid.component.ts", lineNumber: 63 });
})();
export {
  BattleGridComponent
};
//# sourceMappingURL=chunk-PWC3OTB2.js.map
