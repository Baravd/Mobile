import React, {Component} from "react";
import {View, Text, FlatList, AsyncStorage} from "react-native";
import {List, ListItem} from "react-native-elements";
import Button from "react-native-elements/src/buttons/Button";


export default class ExpenseList extends React.Component {
    constructor() {
        super();
        this.state = {ds: global.ds.cloneWithRows([])};
        this.updateState()
    }

    _keyExtractor = (item, index) => item.id;

    updateState() {
        console.log("refresh list");
        AsyncStorage.getAllKeys().then((keys) => {
            expenses = [];
            for (keyIndex in keys) {
                AsyncStorage.getItem(keys[keyIndex]).then((value) => {
                    expenses.push(JSON.parse(value));
                    this.setState({ds: global.ds.cloneWithRows(expenses)});
                })
            }

        });
    }

    render() {
        const {navigate} = this.props.navigation;
        return (
            <View>


                <List>
                    <FlatList

                        dataSource={global.ds}
                        keyExtractor={this._keyExtractor}
                        renderItem={({item}) => (
                            <ListItem
                                onPress={() => {
                                    navigate('Details', {expense: item})

                                }}
                                title={item.name}
                                subtitle={item.type}
                            />
                        )}
                    />
                </List>


                <Button title="Add Expense"
                        onPress={() => this.props.navigation.navigate('AddExpenseView')}
                />


            </View>
        )
            ;
    }

    showDetails(item) {


    }
}
