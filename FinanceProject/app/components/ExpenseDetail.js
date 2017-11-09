import React, {Component} from "react";

import {Text} from "react-native-elements";
import {TextInput, View, StyleSheet} from "react-native";
import Button from "react-native-elements/src/buttons/Button";

export default class ExpenseDetail extends Component {
    render() {
        const currentItem = this.props.navigation.state.params.expense;
        this.state = {name: currentItem.name, type: currentItem.type};

        return <View>

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
            <Button
                title={"Save"}

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