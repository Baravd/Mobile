import React, {Component} from "react";

import {Text} from "react-native-elements";
import {TextInput, View, StyleSheet} from "react-native";
import Button from "react-native-elements/src/buttons/Button";
import DatePicker from "react-native-datepicker";

export default class ExpenseDetail extends Component {
    render() {
        const currentItem = this.props.navigation.state.params.expense;
        this.state = {name: currentItem.name, type: currentItem.type, id: currentItem.id, date: currentItem.date};

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

                onChangeText={(text) => this.setState({name: text})}
                value={this.state.name}

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

            />
            <Button
                title={"Delete"}
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