import SockJS from "sockjs-client"
import Stomp from "stompjs"
import store from "../stores/store";
import { newMission, updateAll, notifyAboutMove } from "../actions/mission-actions";

const apiUrl = process.env.NODE_ENV === 'development' ? 'http://localhost:8080' : '';

const socket = new SockJS(apiUrl + '/subscribe');
export const stompClient = Stomp.over(socket);

export function connectToSocket() {

    stompClient.connect({}, (frame) => {
      store.dispatch(notifyAboutMove());

      stompClient.subscribe('/user/mission/all', (msg) => {
        store.dispatch(updateAll(JSON.parse(msg.body)));
      });

      stompClient.subscribe('/mission/new', (msg) => {
        store.dispatch(newMission(JSON.parse(msg.body)));
      });

      stompClient.subscribe('/mission/move', () => {
        store.dispatch(notifyAboutMove());
      });
    });

    stompClient.debug = () => {};
};