import React, {Component} from "react";
import {View, Text, FlatList} from "react-native";
import {List, ListItem} from "react-native-elements";



export default class ExpenseList extends React.Component {
    _keyExtractor = (item, index) => item.id;

    render() {
        const {navigate} = this.props.navigation;
        return (

            <List>
                <FlatList
                    data={global.data}
                    keyExtractor={this._keyExtractor}
                    renderItem={({item}) => (
                        <ListItem
                            onPress={()=>{
                                navigate('Details',{expense:item})

                            }}
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
