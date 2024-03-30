// websocket.service.ts
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private ws: WebSocket;

    constructor() {
        this.ws = new WebSocket("ws://localhost:7070/chat");
        this.ws.onclose = () => alert("WebSocket connection closed");
    }

  connect(): void {
    this.ws = new WebSocket("ws://localhost:7070/chat");
    this.ws.onclose = () => console.log('WebSocket connection closed');
  }

  sendMessage(message: string): void {
    if (this.ws.readyState === WebSocket.OPEN) {
      this.ws.send(message);
    }
  }
}
