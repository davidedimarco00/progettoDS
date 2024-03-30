import {
  RouterLink,
  RouterModule,
  RouterOutlet,
  bootstrapApplication,
  provideRouter
} from "./chunk-AOGEK5Q2.js";
import {
  CommonModule,
  provideHttpClient,
  ɵsetClassDebugInfo,
  ɵɵStandaloneFeature,
  ɵɵdefineComponent,
  ɵɵelement,
  ɵɵelementEnd,
  ɵɵelementStart,
  ɵɵtext
} from "./chunk-4KJKHX6B.js";

// src/app/app.routes.ts
var routes = [
  { path: "initialGrid", loadComponent: () => import("./chunk-SBWSP32N.js").then((c) => c.InitialGridComponent) },
  { path: "battleGrid", loadComponent: () => import("./chunk-PWC3OTB2.js").then((c) => c.BattleGridComponent) },
  { path: "connectPlayer", loadComponent: () => import("./chunk-HQ6GVVU3.js").then((c) => c.ConnectPlayerComponent) },
  { path: "", redirectTo: "account", pathMatch: "full" }
];

// src/app/app.config.ts
var appConfig = {
  providers: [provideRouter(routes), provideHttpClient()]
};

// src/app/app.component.ts
var _AppComponent = class _AppComponent {
  constructor() {
    this.title = "battleship";
  }
};
_AppComponent.\u0275fac = function AppComponent_Factory(t) {
  return new (t || _AppComponent)();
};
_AppComponent.\u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _AppComponent, selectors: [["app-root"]], standalone: true, features: [\u0275\u0275StandaloneFeature], decls: 5, vars: 0, consts: [["routerLink", "connectPlayer"]], template: function AppComponent_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "h1");
    \u0275\u0275text(1, "Battleship Free-For-All");
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(2, "button", 0);
    \u0275\u0275text(3, "Inizia gioco");
    \u0275\u0275elementEnd();
    \u0275\u0275element(4, "router-outlet");
  }
}, dependencies: [CommonModule, RouterOutlet, RouterModule, RouterLink], encapsulation: 2 });
var AppComponent = _AppComponent;
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(AppComponent, { className: "AppComponent", filePath: "src\\app\\app.component.ts", lineNumber: 23 });
})();

// src/main.ts
bootstrapApplication(AppComponent, appConfig).catch((err) => console.error(err));
//# sourceMappingURL=main.js.map
