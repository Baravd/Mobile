import React, {Component} from "react";
import Stack from "./app/router"


global.data = [{id: "1", name: "cheese", type: "Food"},
    {id: "2", name: "cola", type: "Food"},
    {id: "3", name: "electricity", type: "Utilities"}
]
export default class App extends React.Component {
    static navigationOptions = {
        title: 'Home',
    };
    render(){
        return <Stack />
    }

}

