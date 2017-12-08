import React, {Component} from "react";
import {TextInput, View, StyleSheet, Button, AsyncStorage} from "react-native";
import DatePicker from "react-native-datepicker";


export class AddExpenseView extends Component {
    constructor(props) {
        super(props);
        this.state = {id: '-1', name: "Name", type: "Type", date: new Date()};

    }

    render() {

        return (<View>
            <TextInput
                style={styles.row}
                editable={true}
                keyboardType='numeric'

                onChangeText={(text) => this.setState({id: text})}
                value={this.state.id}
                maxLength={10}

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
                title={"Save"}
                onPress = {()=>{

                    AsyncStorage.setItem(this.state.id, JSON.stringify({
                        id: this.state.id,
                        name: this.state.name,
                        type: this.state.type,
                        date: this.state.date
                    })).then(() => {
                        //this.props.navigation.state.params.updateState();
                        this.props.navigation.goBack();
                    });


                }}


            />

        </View>)
    }
}

const styles = StyleSheet.create({
    row: {
        marginBottom: 5,
        marginTop: 20,
        borderColor: '#E1B700'
    }
});
