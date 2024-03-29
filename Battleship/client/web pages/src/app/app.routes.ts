import { Routes } from '@angular/router';

export const routes: Routes = [
{path: "initialGrid", loadComponent: () => import("./controllers/initial_grid.component").then((c) => c.InitialGridComponent)},
{path: "battleGrid", loadComponent: () => import("./features/battle_grid.component").then((c) => c.BattleGridComponent)},
  {path: "connectPlayer", loadComponent: () => import("./controllers/connect_player.component").then((c) => c.ConnectPlayerComponent)},
  {path: "lobbies", loadComponent: () => import("./controllers/lobbies.component").then((c) => c.LobbiesComponent)},
{path: "", redirectTo: "account", pathMatch: "full"}
];
