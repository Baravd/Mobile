import React, {Component} from "react";
import Stack from "./app/router";
import {ListView} from 'react-native';


global.data = [{id: "1", name: "cheese", type: "Food", date: new Date()},
    {id: "2", name: "cola", type: "Food", date: new Date()},
    {id: "3", name: "electricity", type: "Utilities", date: new Date()}
]
global.ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});

export default class App extends React.Component {


    render() {
        return <Stack/>
    }

}

