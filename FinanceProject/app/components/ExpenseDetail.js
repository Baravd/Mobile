import React, {Component} from "react";

import {Text} from "react-native-elements";
import {TextInput, View, StyleSheet, AsyncStorage} from "react-native";
import Button from "react-native-elements/src/buttons/Button";
import DatePicker from "react-native-datepicker";

export default class ExpenseDetail extends Component {
    render() {
        const currentItem = this.props.navigation.state.params.expense;
        this.state = {id: currentItem.id, nume: currentItem.nume, type: currentItem.type, date: currentItem.date};

        return <View>
            <TextInput
                style={styles.row}
                editable={true}

                onChangeText={(text) => this.setState({id: text})}
                value={this.state.id}

            />
            <TextInput
                style={styles.row}
                editable={true}

                onChangeText={(text) => this.setState({nume: text})}
                value={this.state.nume}

            />
            <TextInput
                style={styles.row}
                editable={true}

                onChangeText={(text) => this.setState({type: text})}
                value={this.state.type}

            />
            <DatePicker date={this.state.date}
                        mode="date"
                        placeholder="purchase date"
                        onDateChange={(date) => {
                            this.setState({date: date})
                        }}

            />
            <Button
                title={"Save Changes"}
                /*onPress={() => {
                    AsyncStorage.mergeItem(this.props.navigation.state.params.expense.id, JSON.stringify({
                            nume: this.state.nume,
                            type: this.state.type,
                            date: this.state.date
                        })
                    ).then(() => {
                        this.props.navigation.state.params.updateState();
                        this.props.navigation.goBack();
                    })
                }
                }*/

            />
            <Button
                title={"Delete"}
                /*onPress={() => {
                    AsyncStorage.removeItem(this.props.navigation.state.params.expense.id)
                        .then(() => {
                            this.props.navigation.state.params.updateState();
                            this.props.navigation.goBack();
                        })
                }}*/

            />

        </View>
    }


}
const styles = StyleSheet.create({
    row: {
        marginBottom: 5,
        marginTop: 20,
        borderColor: '#E1B700'
    }
});