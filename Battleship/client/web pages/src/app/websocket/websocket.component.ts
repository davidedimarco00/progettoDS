import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-websocket',
  standalone: true,
  imports: [],
  template: `
    <div>
      <input type="text" id="message" placeholder="Enter your message" (keypress)="onKeyPress($event)">
      <button (click)="sendMessage()">Send</button>
    </div>
    <div id="chat"></div>
    <ul id="userlist"></ul>
  `,
  styles: ``
})
export class WebsocketComponent implements OnInit {
  ws: WebSocket;

  constructor() {
    this.ws = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chat");
    this.ws.onmessage = (msg) => this.updateChat(msg);
    this.ws.onclose = () => alert("WebSocket connection closed");
  }

  ngOnInit(): void {
    this.ws = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chat");
    this.ws.onmessage = (msg) => this.updateChat(msg);
    this.ws.onclose = () => alert("WebSocket connection closed");
  }

  sendMessage(): void {
    const message = (document.getElementById("message") as HTMLInputElement).value;
    if (message !== "") {
      this.ws.send(message);
      (document.getElementById("message") as HTMLInputElement).value = "";
    }
  }

  onKeyPress(event: KeyboardEvent): void {
    if (event.key === 'Enter') {
      this.sendMessage();
    }
  }

  updateChat(msg: MessageEvent): void {
    const data = JSON.parse(msg.data);
    const chatElement = document.getElementById("chat");
    if (chatElement) {
      chatElement.insertAdjacentHTML("afterbegin", data.userMessage);
    }
  }
}
