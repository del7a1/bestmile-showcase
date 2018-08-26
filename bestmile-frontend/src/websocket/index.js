import SockJS from "sockjs-client"
import Stomp from "stompjs"
import store from "../stores";
import { newMission, mapUpdate } from "../actions";

const socket = new SockJS('http://localhost:8080/subscribe');
export const stompClient = Stomp.over(socket);

export function connectToSocket() {

    stompClient.connect({}, (frame) => {
      stompClient.subscribe('/user/mission/all', (msg) => {
        store.dispatch(mapUpdate(JSON.parse(msg.body)));
      });

      stompClient.subscribe('/mission/new', (msg) => {
        store.dispatch(newMission(JSON.parse(msg.body)));
      });

    });
};