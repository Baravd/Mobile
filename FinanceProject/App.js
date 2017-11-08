import React, {Component} from "react";
import {View, Text, FlatList} from "react-native";
import {List, ListItem} from "react-native-elements";

let data = [{id: "1", name: "cheese",type:"Food"},
    {id: "2", name: "cola", type:"Food"},
    {id: "3", name: "electricity", type:"Utilities"}
]

export default class App extends React.Component {

    _keyExtractor = (item, index) => item.id;

    render() {
        return (
            <List>
                <FlatList
                    data={data}
                    keyExtractor={this._keyExtractor}
                    renderItem={({item}) => (
                        <ListItem
                            onPress ={this.showDetails(item)}
                            title={item.name}
                            subtitle={item.type}
                        />

                    )}
                />
            </List>
        );
    }

    showDetails(item) {
        
    }
}

