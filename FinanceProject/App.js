import React, {Component} from "react";
import Stack from "./app/router";
import {ListView} from 'react-native';
import * as firebase from 'firebase'



global.data = [{id: "1", name: "cheese", type: "Food", date: new Date()},
    {id: "2", name: "cola", type: "Food", date: new Date()},
    {id: "3", name: "electricity", type: "Utilities", date: new Date()}
]
global.ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
const config = {
    apiKey: "AIzaSyCCjBM3tE8sGy029Owr2qnsUfxoW2yu8Es",
    authDomain: "financemanager-63f17.firebaseapp.com",
    databaseURL: "https://financemanager-63f17.firebaseio.com",
    projectId: "financemanager-63f17",
    storageBucket: "financemanager-63f17.appspot.com",
    messagingSenderId: "558623666276"
};
global.firebaseApp = firebase.initializeApp(config);

export default class App extends React.Component {


    render() {
        return <Stack/>
    }

}

